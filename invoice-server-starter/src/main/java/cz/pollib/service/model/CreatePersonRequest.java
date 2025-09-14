package cz.pollib.service.model;

import cz.pollib.constant.Countries;
import cz.pollib.service.validation.Unique;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

/**
 * Request for creating person values and their validation constraints.
 * <p>
 * Attributes:
 * - name: The name of the person.
 * - identificationNumber: The identification number of the person. The number must be 8 digits and unique.
 * - taxNumber: The tax number, starting with two uppercase letters.
 * - accountNumber: The account number.
 * - bankCode: The bank code.
 * - iban: The IBAN.
 * - telephone: The telephone number. Starts with +421 or +420 and 9 digits following.
 * - mail: The email address. Must be in email format.
 * - street: The street address.
 * - zip: The postal code. Must be 5 digits.
 * - city: The city.
 * - country: The country.
 * - note: Any additional notes.
 */
@Schema(name = "CreatePersonRequest")
public record CreatePersonRequest(

        @NotBlank(message = "Name cannot be blank")
        String name,

        @Unique(entity = "person", field = "identificationNumber", message = "Identification number must be unique")
        @Positive(message = "Identification number must be positive number")
        @Pattern(regexp = "^[0-9]{8}$", message = "Identification number must be 8 digits")
        String identificationNumber,

        @Pattern(regexp = "^[A-Z]{2}\\d+$", message = "Tax number must start with two uppercase letters")
        String taxNumber,

        @Positive(message = "Account number must be positive number")
        String accountNumber,

        @Positive(message = "Bank code must be positive number")
        String bankCode,

        @NotBlank(message = "IBAN cannot be blank")
        String iban,

        @NotBlank(message = "Phone number cannot be blank")
        @Pattern(regexp = "^\\+(420|421)[0-9]{9}$")
        String telephone,

        @Email(message = "Incorrect email format")
        String mail,

        @NotBlank(message = "Street cannot be blank")
        String street,

        @NotBlank(message = "ZIP cannot be blank")
        @Pattern(regexp = "^[0-9]{5}$", message = "ZIP must be 5 digits")
        String zip,

        @NotBlank(message = "City cannot be blank")
        String city,

        Countries country,

        String note
) {
}