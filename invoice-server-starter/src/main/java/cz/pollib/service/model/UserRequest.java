package cz.pollib.service.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(name = "UserRequest")
public record UserRequest(
        @Email(message = "Must be in email format")
        @NotBlank(message = "Email cannot be blank")
        String email,

        @Size(
                min = 6,
                message = "Password must be at least 6 characters"
        )
        @NotBlank(message = "Password cannot be blank")
        String password
) {
}