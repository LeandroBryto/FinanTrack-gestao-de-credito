package leandrodev.creditapplicationsystem.service;

import leandrodev.creditapplicationsystem.entity.Credit;
import leandrodev.creditapplicationsystem.response.CreditResponse;

public interface CreditService {
    CreditResponse findById(Long id);
    CreditResponse findCreditByClientIdentificationNumber(String identificationNumber);
    Credit save(Credit credit);
    void deleteById(Long id);
    Credit evaluateCredit(Integer creditScore, Double income, Double collateral);
}
