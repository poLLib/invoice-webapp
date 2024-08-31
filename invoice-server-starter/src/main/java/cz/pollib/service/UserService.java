package cz.pollib.service;

import cz.pollib.dto.UserDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    
    UserDTO createAccount(UserDTO user);

}
