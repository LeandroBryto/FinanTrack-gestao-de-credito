package leandrodev.creditapplicationsystem.controller;


import leandrodev.creditapplicationsystem.request.UserRequest;
import leandrodev.creditapplicationsystem.response.UserResponse;
import leandrodev.creditapplicationsystem.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/save")
    public ResponseEntity<UserResponse> save(@RequestBody UserRequest request) {
        return ResponseEntity.ok(authenticationService.save(request));
    }

    @PostMapping
    public ResponseEntity<UserResponse> authentication(@RequestBody UserRequest userRequest){
        return ResponseEntity.ok(authenticationService.auth(userRequest));
    }

}
