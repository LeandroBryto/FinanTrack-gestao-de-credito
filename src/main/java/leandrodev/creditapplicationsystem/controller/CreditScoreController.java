package leandrodev.creditapplicationsystem.controller;

import leandrodev.creditapplicationsystem.response.CreditScoreResponse;
import leandrodev.creditapplicationsystem.service.CreditScoreService;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/credit-score")
public class CreditScoreController {

    private final CreditScoreService creditScoreService;



    @GetMapping("/{identificationNumber}")
    public ResponseEntity<CreditScoreResponse> getCreditScoreByIdentificationNumber(@PathVariable @Length(max = 11) String identificationNumber) {
        return ResponseEntity.ok(creditScoreService.findCreditScoreByClientIdentificationNumber(identificationNumber));
    }


    @PostMapping("/{id}")
    public ResponseEntity<CreditScoreResponse> setCreditScoreByClientId(@PathVariable Long id, @RequestBody Integer creditScore) {
        return ResponseEntity.ok(creditScoreService.setCreditScoreById(id, creditScore));
    }

}
