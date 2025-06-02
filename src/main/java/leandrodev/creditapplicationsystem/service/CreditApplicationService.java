package leandrodev.creditapplicationsystem.service;

import leandrodev.creditapplicationsystem.request.CreditApplicationCreateRequest;
import leandrodev.creditapplicationsystem.response.CreditApplicationResponse;

import java.util.List;

public interface CreditApplicationService {
    CreditApplicationResponse findById(Long id);

    List<CreditApplicationResponse> findAll();

    CreditApplicationResponse save(CreditApplicationCreateRequest request);

    void deleteById(Long id);
}
