package leandrodev.creditapplicationsystem.service;

import leandrodev.creditapplicationsystem.response.CreditScoreResponse;

public interface CreditScoreService {

    CreditScoreResponse findCreditScoreByClientIdentificationNumber(String identificationNumber);

    CreditScoreResponse setCreditScoreById(Long id, Integer creditScore);
}
