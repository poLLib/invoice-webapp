package cz.pollib.controller;

import cz.pollib.dto.UserDTO;
import cz.pollib.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping({"/user"})
    public UserDTO addUser (@RequestBody @Valid UserDTO userDTO) {
        return userService.createAccount(userDTO);
    }
}
