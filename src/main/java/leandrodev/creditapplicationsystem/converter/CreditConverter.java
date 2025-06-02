package leandrodev.creditapplicationsystem.converter;


import leandrodev.creditapplicationsystem.entity.Credit;
import leandrodev.creditapplicationsystem.response.ClientResponse;
import leandrodev.creditapplicationsystem.response.CreditResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class CreditConverter {

    public CreditResponse entityToResponse(Credit entity) {

        CreditResponse response = new CreditResponse();

        BeanUtils.copyProperties(entity, response);

        if (entity.getClient() != null) {
            ClientResponse clientResponse = new ClientResponse();
            BeanUtils.copyProperties(entity.getClient(), clientResponse);
            response.setClient(clientResponse);
        }

        return response;
    }
}
