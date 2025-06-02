package leandrodev.creditapplicationsystem.service.impl;


import leandrodev.creditapplicationsystem.converter.ClientConverter;
import leandrodev.creditapplicationsystem.entity.Client;
import leandrodev.creditapplicationsystem.exception.ResourceNotFoundException;
import leandrodev.creditapplicationsystem.repository.ClientRepository;
import leandrodev.creditapplicationsystem.request.ClientCreateRequest;
import leandrodev.creditapplicationsystem.request.ClientUpdateRequest;
import leandrodev.creditapplicationsystem.response.ClientResponse;
import leandrodev.creditapplicationsystem.service.ClientService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;

    private final ClientConverter converter;

    @Override
    public ClientResponse findById(Long id) {
        Client client = clientRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id : " + id));
        return converter.entityToResponse(client);
    }

    @Override
    public List<ClientResponse> findAll() {
        return clientRepository
                .findAll()
                .stream()
                .map(converter::entityToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public ClientResponse save(ClientCreateRequest request) {

        Client client = converter.requestToEntity(request);

        Client savedClient = clientRepository.save(client);

        return converter.entityToResponse(savedClient);

    }

    @Override
    public ClientResponse updateById(Long id, ClientUpdateRequest request) {

        Client client = clientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id : " + id));

        client.setBirthday(request.getBirthday());
        client.setName(request.getName());
        client.setIncome(request.getIncome());
        client.setIdentificationNumber(request.getIdentificationNumber());
        client.setPhone(request.getPhone());

        return converter.entityToResponse(clientRepository.save(client));
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
