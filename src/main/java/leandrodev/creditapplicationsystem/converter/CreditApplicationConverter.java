package leandrodev.creditapplicationsystem.converter;

import leandrodev.creditapplicationsystem.entity.CreditApplication;
import leandrodev.creditapplicationsystem.request.CreditApplicationCreateRequest;
import leandrodev.creditapplicationsystem.response.ClientResponse;
import leandrodev.creditapplicationsystem.response.CreditApplicationResponse;
import leandrodev.creditapplicationsystem.response.CreditLimitResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CreditApplicationConverter {

    public CreditApplication requestToEntity(CreditApplicationCreateRequest request) {
        CreditApplication entity = new CreditApplication();
        BeanUtils.copyProperties(request, entity);
        return entity;
    }

    public CreditApplicationResponse entityToResponse(CreditApplication entity) {
        CreditApplicationResponse response = new CreditApplicationResponse();
        BeanUtils.copyProperties(entity, response);

        if(entity.getCredit() != null){
            CreditLimitResponse creditLimitResponse = new CreditLimitResponse();
            BeanUtils.copyProperties(entity.getCredit(), creditLimitResponse);
            response.setCredit(creditLimitResponse);
        }

        if(entity.getClient() != null){
            ClientResponse clientResponse = new ClientResponse();
            BeanUtils.copyProperties(entity.getClient(), clientResponse);
            response.setClient(clientResponse);
        }

        return response;
    }
}
