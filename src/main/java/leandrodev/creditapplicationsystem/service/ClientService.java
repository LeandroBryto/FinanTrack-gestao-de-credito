package leandrodev.creditapplicationsystem.service;

import leandrodev.creditapplicationsystem.request.ClientCreateRequest;
import leandrodev.creditapplicationsystem.request.ClientUpdateRequest;
import leandrodev.creditapplicationsystem.response.ClientResponse;


import java.util.List;

public interface ClientService {

    ClientResponse findById(Long id);

    List<ClientResponse> findAll();

    ClientResponse save(ClientCreateRequest request);

    ClientResponse updateById(Long id, ClientUpdateRequest request);

    void deleteById(Long id);

}
