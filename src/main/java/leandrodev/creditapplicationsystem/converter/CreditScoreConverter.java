package leandrodev.creditapplicationsystem.converter;

import leandrodev.creditapplicationsystem.entity.CreditScore;
import leandrodev.creditapplicationsystem.request.CreditScoreCreateRequest;
import leandrodev.creditapplicationsystem.request.CreditScoreRequest;
import leandrodev.creditapplicationsystem.response.ClientResponse;
import leandrodev.creditapplicationsystem.response.CreditScoreResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CreditScoreConverter {

    public CreditScoreResponse entityToResponse(CreditScore entity) {
        CreditScoreResponse response = new CreditScoreResponse();

        if (entity.getClient() != null) {
            ClientResponse clientResponse = new ClientResponse();
            BeanUtils.copyProperties(entity.getClient(), clientResponse);
            response.setClient(clientResponse);
        }

        BeanUtils.copyProperties(entity, response);
        return response;
    }

    public CreditScore requestToEntity(CreditScoreRequest request) {
        CreditScore entity = new CreditScore();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }

    public CreditScore requestToEntity(CreditScoreCreateRequest request) {
        CreditScore entity = new CreditScore();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }

    public CreditScore responseToEntity(CreditScoreResponse response) {
        CreditScore entity = new CreditScore();
        BeanUtils.copyProperties(response, entity);
        return entity;
    }
}
