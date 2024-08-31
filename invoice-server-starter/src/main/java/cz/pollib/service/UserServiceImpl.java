package cz.pollib.service;

import cz.pollib.dto.UserDTO;
import cz.pollib.entity.UserEntity;
import cz.pollib.entity.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserDTO createAccount(UserDTO model) {

        UserEntity newUser = new UserEntity();
        newUser.setEmail(model.getEmail());
        newUser.setPassword(passwordEncoder.encode(model.getPassword()));

        newUser = userRepository.save(newUser);

        UserDTO dto = new UserDTO();
        dto.setUserId(newUser.getUserId());
        dto.setEmail(newUser.getEmail());
        return dto;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Uživatel " + username + " nebyl nalezen."));
    }
}
