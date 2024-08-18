package cz.pollib.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.pollib.constant.Countries;
import cz.pollib.dto.validation.IdentificationNumberNotDuplicateValidAnnotation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object (DTO) for person details and their and validation constraints.
 * <p>
 * Attributes:
 * - id: The unique identifier for the person.
 * - name: The name of the person.
 * - identificationNumber: The identification number of the person.
 * - taxNumber: The tax number, starting with two uppercase letters.
 * - accountNumber: The account number.
 * - bankCode: The bank code.
 * - iban: The IBAN.
 * - telephone: The telephone number.
 * - mail: The email address.
 * - street: The street address.
 * - zip: The postal code.
 * - city: The city.
 * - country: The country.
 * - note: Any additional notes.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    @JsonProperty("_id")
    private Long id;

    @NotBlank(message = "Zadejte jméno")
    private String name;

    @IdentificationNumberNotDuplicateValidAnnotation
    @Positive(message = "Zadejte IČO v absolutním čísle")
    private String identificationNumber;

    @Pattern(regexp = "^[A-Z]{2}\\d+$", message = "DIČ musí začínat dvěma velkými písmeny reprezentující zemi")
    private String taxNumber;

    @Positive(message = "Zadejte číslo účtu v absolutním čísle")
    private String accountNumber;

    @Positive(message = "Zadejte kód banky v absolutním čísle")
    private String bankCode;

    @NotBlank(message = "Zadejte IBAN")
    private String iban;

    @NotBlank(message = "Zadejte telefoní číslo")
    private String telephone;

    @NotBlank(message = "Zadejte email")
    @Email(message = "Zadejte email ve správném tvaru")
    private String mail;

    @NotBlank(message = "Zadejte ulici a č.p")
    private String street;

    @NotBlank(message = "Zadejte PSČ")
    private String zip;

    @NotBlank(message = "Zadejte obec")
    private String city;

    private Countries country;

    private String note;
}
