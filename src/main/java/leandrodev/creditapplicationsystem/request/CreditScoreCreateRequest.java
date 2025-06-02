package leandrodev.creditapplicationsystem.request;

import leandrodev.creditapplicationsystem.entity.Client;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CreditScoreCreateRequest {
    private Integer creditScore;
}
