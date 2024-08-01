package cz.pollib.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.pollib.constant.Countries;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonDTO {

    @JsonProperty("_id")
    private Long id;

    @NotBlank(message = "Zadejte jméno")
    private String name;

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
