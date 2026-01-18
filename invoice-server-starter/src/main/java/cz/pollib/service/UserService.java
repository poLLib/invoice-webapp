package cz.pollib.service;

import cz.pollib.service.model.AuthResponse;
import cz.pollib.service.model.UserRequest;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    AuthResponse login(UserRequest request);

    AuthResponse registerUser(UserRequest request);
}