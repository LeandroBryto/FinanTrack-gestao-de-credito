package leandrodev.creditapplicationsystem.repository;

import leandrodev.creditapplicationsystem.entity.CreditApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditApplicationRepository extends JpaRepository<CreditApplication, Long> {
}
