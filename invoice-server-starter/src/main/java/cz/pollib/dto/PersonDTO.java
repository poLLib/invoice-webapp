package cz.pollib.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import cz.pollib.constant.Countries;
import cz.pollib.dto.validation.IdentificationNumberNotDuplicate;
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
public class PersonDTO {

    @JsonProperty("_id")
    private Long id;

    @NotBlank(message = "Type name")
    private String name;

    @IdentificationNumberNotDuplicate
    @Positive(message = "Identification number must be positive number")
    @Pattern(regexp = "^[0-9]{8}$", message = "Identification number must be 8 digits")
    private String identificationNumber;

    @Pattern(regexp = "^[A-Z]{2}\\d+$", message = "Tax number must start with two uppercase letters")
    private String taxNumber;

    @Positive(message = "Account number must be positive number")
    private String accountNumber;

    @Positive(message = "Bank code must be positive number")
    private String bankCode;

    @NotBlank(message = "Type IBAN")
    private String iban;

    @NotBlank(message = "Type phone number")
    @Pattern(regexp = "^\\+(420|421)[0-9]{9}$")
    private String telephone;

    @NotBlank(message = "Type email")
    @Email(message = "Incorrect format")
    private String mail;

    @NotBlank(message = "Type street")
    private String street;

    @NotBlank(message = "Typer ZIP")
    @Pattern(regexp = "^[0-9]{5}$", message = "ZIP must be 5 digits")
    private String zip;

    @NotBlank(message = "Type city")
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

    public @NotBlank(message = "Type name")
    String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdentificationNumber() {
        return identificationNumber;
    }

    public void setIdentificationNumber(String identificationNumber) {
        this.identificationNumber = identificationNumber;
    }

    public String getTaxNumber() {
        return taxNumber;
    }

    public void setTaxNumber(String taxNumber) {
        this.taxNumber = taxNumber;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip;
    }

    public String getCity() {
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
