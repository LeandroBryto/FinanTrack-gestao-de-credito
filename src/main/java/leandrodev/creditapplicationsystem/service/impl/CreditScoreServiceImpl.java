package leandrodev.creditapplicationsystem.service.impl;

import leandrodev.creditapplicationsystem.converter.ClientConverter;
import leandrodev.creditapplicationsystem.converter.CreditScoreConverter;
import leandrodev.creditapplicationsystem.entity.Client;
import leandrodev.creditapplicationsystem.entity.CreditScore;
import leandrodev.creditapplicationsystem.repository.CreditScoreRepository;
import leandrodev.creditapplicationsystem.response.CreditScoreResponse;
import leandrodev.creditapplicationsystem.service.ClientService;
import leandrodev.creditapplicationsystem.service.CreditScoreService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CreditScoreServiceImpl implements CreditScoreService {

    private final CreditScoreRepository creditScoreRepository;

    private final ClientService clientService;
    private final ClientConverter clientConverter;
    private final CreditScoreConverter creditScoreConverter;


    @Cacheable(key = "#identificationNumber", value = "CreditScoreResponse")
    @Override
    public CreditScoreResponse findCreditScoreByClientIdentificationNumber(String identificationNumber) {
        return creditScoreConverter.entityToResponse(creditScoreRepository.findByClientIdentificationNumber(identificationNumber));
    }

    @Cacheable(key = "#id", value = "CreditScoreResponse")
    @Override
    public CreditScoreResponse setCreditScoreById(Long id, Integer score) {
        Client client = clientConverter.responseToEntity(clientService.findById(id));

        CreditScore creditScore = new CreditScore(client, score);

        return creditScoreConverter.entityToResponse(creditScoreRepository.save(creditScore));
    }
}
