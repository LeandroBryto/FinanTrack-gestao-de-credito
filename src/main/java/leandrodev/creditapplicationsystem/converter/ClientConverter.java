package leandrodev.creditapplicationsystem.converter;

import leandrodev.creditapplicationsystem.entity.Client;
import leandrodev.creditapplicationsystem.request.ClientCreateRequest;
import leandrodev.creditapplicationsystem.request.ClientRequest;
import leandrodev.creditapplicationsystem.response.ClientResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ClientConverter {

    public Client requestToEntity(ClientCreateRequest request) {
        Client entity = new Client();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }

//    public Client requestToEntity(ClientRequest request) {
//        Client entity = new Client();
//        BeanUtils.copyProperties(request, entity);
//        return entity;
//    }

    public ClientResponse entityToResponse(Client entity) {
        ClientResponse response = new ClientResponse();
        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public Client responseToEntity(ClientResponse response) {
        Client entity = new Client();
        BeanUtils.copyProperties(response, entity);
        return entity;
    }

}
