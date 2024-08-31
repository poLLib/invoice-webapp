package cz.pollib.controller;

import cz.pollib.dto.UserDTO;
import cz.pollib.entity.UserEntity;
import cz.pollib.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.rmi.ServerException;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user")
    public UserDTO addUser(@RequestBody @Valid UserDTO userDTO) {
        return userService.createAccount(userDTO);
    }

    @PostMapping("/auth")
    public UserDTO login(@RequestBody @Valid UserDTO userDTO, HttpServletRequest request) throws ServletException {
        request.login(userDTO.getEmail(), userDTO.getPassword());

        UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDTO model = new UserDTO();
        model.setEmail(user.getEmail());
        model.setUserId(user.getUserId());
        model.setAdmin(user.isAdmin());
        return model;
    }

    @DeleteMapping("/auth")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "Uživatel odhlášen";
    }

    @GetMapping("/auth")
    public UserDTO getCurrentUser() throws ServletException {
        try {
            UserEntity user = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            UserDTO model = new UserDTO();
            model.setEmail(user.getEmail());
            model.setUserId(user.getUserId());
            model.setAdmin(user.isAdmin());
            return model;
        } catch (ClassCastException e) {
            throw new ServletException();
        }
    }

    /**
     * Handles the user's authentication exception if:
     * - User logins when already login.
     * - Logout fails
     */
    @ExceptionHandler(ServerException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public void handleServletException() {
    }

}
