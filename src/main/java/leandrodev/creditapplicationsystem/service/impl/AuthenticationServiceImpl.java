package leandrodev.creditapplicationsystem.service.impl;

import leandrodev.creditapplicationsystem.entity.User;
import leandrodev.creditapplicationsystem.enums.Role;
import leandrodev.creditapplicationsystem.repository.UserRepository;
import leandrodev.creditapplicationsystem.request.UserRequest;
import leandrodev.creditapplicationsystem.response.UserResponse;
import leandrodev.creditapplicationsystem.service.AuthenticationService;
import leandrodev.creditapplicationsystem.service.JwtService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@Slf4j
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private PasswordEncoder encoder = new BCryptPasswordEncoder();

    private final AuthenticationManager authenticationManager;

    @Override
    public UserResponse save(UserRequest request) {
        User user = User.builder()
                .username(request.getUsername())
                .password(encoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        userRepository.save(user);

        var token = jwtService.generateToken(user);

        return UserResponse.builder()
                .token(token)
                .build();
    }

    @Override
    public UserResponse auth(UserRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        User user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        String token = jwtService.generateToken(user);

        return UserResponse.builder()
                .token(token)
                .build();
    }

}
