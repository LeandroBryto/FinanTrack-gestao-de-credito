package leandrodev.creditapplicationsystem.service.impl;

import leandrodev.creditapplicationsystem.converter.ClientConverter;
import leandrodev.creditapplicationsystem.converter.CreditApplicationConverter;
import leandrodev.creditapplicationsystem.entity.Client;
import leandrodev.creditapplicationsystem.entity.Credit;
import leandrodev.creditapplicationsystem.entity.CreditApplication;
import leandrodev.creditapplicationsystem.entity.CreditNotification;
import leandrodev.creditapplicationsystem.enums.CreditApplicationStatusEnum;
import leandrodev.creditapplicationsystem.exception.ResourceNotFoundException;
import leandrodev.creditapplicationsystem.repository.CreditApplicationRepository;
import leandrodev.creditapplicationsystem.request.CreditApplicationCreateRequest;
import leandrodev.creditapplicationsystem.response.CreditApplicationResponse;
import leandrodev.creditapplicationsystem.service.ClientService;
import leandrodev.creditapplicationsystem.service.CreditApplicationService;
import leandrodev.creditapplicationsystem.service.CreditScoreService;
import leandrodev.creditapplicationsystem.service.CreditService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static leandrodev.creditapplicationsystem.enums.CreditApplicationStatusEnum.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditApplicationServiceImpl implements CreditApplicationService {

    private final KafkaTemplate<String, Object> kafkaTemplate;
    private final CreditApplicationRepository creditApplicationRepository;

    private final CreditService creditService;
    private final CreditScoreService creditScoreService;
    private final ClientService clientService;

    private final CreditApplicationConverter creditApplicationConverter;
    private final ClientConverter clientConverter;

    @Override
    public CreditApplicationResponse findById(Long id) {
        CreditApplication creditApplication = creditApplicationRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Credit Application not found with id: " + id));

        return creditApplicationConverter.entityToResponse(creditApplication);
    }

    @Override
    public List<CreditApplicationResponse> findAll() {
        return creditApplicationRepository.findAll()
                .stream()
                .map(creditApplicationConverter::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public CreditApplicationResponse save(CreditApplicationCreateRequest request) {

        Client client = clientConverter.responseToEntity(clientService.findById(request.getClient().getId()));

        Integer clientCreditScore = creditScoreService
                .findCreditScoreByClientIdentificationNumber(client.getIdentificationNumber())
                .getCreditScore();

        CreditApplication creditApplication = CreditApplication.builder()
                .client(client)
                .status(evaluateCreditStatusByCreditScore(clientCreditScore))
                .collateral(request.getCollateral())
                .build();

        if (creditApplication.getStatus() == APPROVED) {
            Credit credit = creditService.evaluateCredit(clientCreditScore, client.getIncome(), request.getCollateral());
            credit.setClient(client);
            credit.setCreditApplication(creditApplication);
            creditApplication.setCredit(credit);
            creditService.save(credit);

            kafkaTemplate.send("sms-notifications", new CreditNotification(client.getPhone(), creditApplication.getCredit().getCreditLimit()));
        }

        CreditApplication savedCreditApplication = creditApplicationRepository.save(creditApplication);

        return creditApplicationConverter.entityToResponse(savedCreditApplication);
    }


    private CreditApplicationStatusEnum evaluateCreditStatusByCreditScore(Integer clientCreditScore) {
        return clientCreditScore < 500 ? REJECTED : APPROVED;
    }

    @Override
    public void deleteById(Long id) {
        creditApplicationRepository.deleteById(id);
    }

}
