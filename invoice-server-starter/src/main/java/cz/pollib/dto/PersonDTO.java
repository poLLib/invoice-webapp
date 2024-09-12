package cz.pollib.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.pollib.constant.Countries;
import cz.pollib.dto.validation.IdentificationNumberNotDuplicateValidAnnotation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

/**
 * Data Transfer Object (DTO) for person details and their and validation constraints.
 * <p>
 * Attributes:
 * - id: The unique identifier for the person.
 * - name: The name of the person.
 * - identificationNumber: The identification number of the person. The number must be unique in database.
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
public class PersonDTO {

    @JsonProperty("_id")
    private Long id;

    @NotBlank(message = "Zadejte jméno")
    private String name;

    @UniqueInvoiceIdentificationNumber
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

    public PersonDTO() {
    }

    public PersonDTO(Long id, String name, String identificationNumber, String taxNumber, String accountNumber, String bankCode, String iban, String telephone, String mail, String street, String zip, String city, Countries country, String note) {
        this.id = id;
        this.name = name;
        this.identificationNumber = identificationNumber;
        this.taxNumber = taxNumber;
        this.accountNumber = accountNumber;
        this.bankCode = bankCode;
        this.iban = iban;
        this.telephone = telephone;
        this.mail = mail;
        this.street = street;
        this.zip = zip;
        this.city = city;
        this.country = country;
        this.note = note;
    }

    // GETTERs and SETTERs block

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public @NotBlank(message = "Zadejte jméno") String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public @Positive(message = "Zadejte IČO v absolutním čísle") String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public @Pattern(regexp = "^[A-Z]{2}\\d+$", message = "DIČ musí začínat dvěma velkými písmeny reprezentující zemi") String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public @Positive(message = "Zadejte číslo účtu v absolutním čísle") String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public @Positive(message = "Zadejte kód banky v absolutním čísle") String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public @NotBlank(message = "Zadejte IBAN") String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public @NotBlank(message = "Zadejte telefoní číslo") String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public @NotBlank(message = "Zadejte email") @Email(message = "Zadejte email ve správném tvaru") String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public @NotBlank(message = "Zadejte ulici a č.p") String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public @NotBlank(message = "Zadejte PSČ") String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public @NotBlank(message = "Zadejte obec") String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Countries getCountry() {
        return country;
    }

    public void setCountry(Countries country) {
        this.country = country;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
