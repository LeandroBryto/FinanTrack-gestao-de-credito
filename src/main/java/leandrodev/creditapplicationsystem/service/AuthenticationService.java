package leandrodev.creditapplicationsystem.service;

import leandrodev.creditapplicationsystem.request.UserRequest;
import leandrodev.creditapplicationsystem.response.UserResponse;

public interface AuthenticationService {
    UserResponse save(UserRequest request);
    UserResponse auth(UserRequest request);
}
