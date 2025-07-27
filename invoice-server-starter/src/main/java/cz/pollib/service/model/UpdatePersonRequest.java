package cz.pollib.service.model;

import cz.pollib.constant.Countries;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

/**
 * Request for updating person values and their validation constraints.
 * <p>
 * Attributes:
 * - name: The name of the person.
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
public class UpdatePersonRequest {

    @NotBlank(message = "Name cannot be blank")
    private String name;

    @Pattern(regexp = "^[A-Z]{2}\\d+$", message = "Tax number must start with two uppercase letters")
    private String taxNumber;

    @Positive(message = "Account number must be positive number")
    private String accountNumber;

    @Positive(message = "Bank code must be positive number")
    private String bankCode;

    @NotBlank(message = "IBAN cannot be blank")
    private String iban;

    @NotBlank(message = "Phone number cannot be blank")
    @Pattern(regexp = "^\\+(420|421)[0-9]{9}$")
    private String telephone;

    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Incorrect email format")
    private String mail;

    @NotBlank(message = "Street cannot be blank")
    private String street;

    @NotBlank(message = "ZIP cannot be blank")
    @Pattern(regexp = "^[0-9]{5}$", message = "ZIP must be 5 digits")
    private String zip;

    @NotBlank(message = "City cannot be blank")
    private String city;

    private Countries country;

    private String note;

    public UpdatePersonRequest() {

    }

    public UpdatePersonRequest(String name, String taxNumber, String accountNumber, String bankCode, String iban, String telephone, String mail, String street, String zip, String city, Countries country, String note) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
