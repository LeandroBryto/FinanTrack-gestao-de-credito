package leandrodev.creditapplicationsystem.repository;

import leandrodev.creditapplicationsystem.entity.Credit;
import leandrodev.creditapplicationsystem.response.CreditResponse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<Credit, Long> {
    Credit findCreditByClientIdentificationNumber(String identificationNumber);
}
