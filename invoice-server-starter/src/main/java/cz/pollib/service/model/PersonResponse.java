package cz.pollib.service.model;

import cz.pollib.constant.Countries;

/**
 * Response for person details
 * <p>
 * Attributes:
 * - id: The unique identifier for the person.
 * - name: The name of the person.
 * - identificationNumber: The identification number of the person
 * - taxNumber: The tax number
 * - accountNumber: The account number
 * - bankCode: The bank code
 * - iban: The IBAN
 * - telephone: The telephone number
 * - mail: The email address
 * - street: The street address
 * - zip: The postal code
 * - city: The city
 * - country: The country
 * - note: Any additional notes
 */
public class PersonResponse {

    private Long id;

    private String name;

    private String identificationNumber;

    private String taxNumber;

    private String accountNumber;

    private String bankCode;

    private String iban;

    private String telephone;

    private String mail;

    private String street;

    private String zip;

    private String city;

    private Countries country;

    private String note;

    public PersonResponse(Long id, String name, String identificationNumber, String taxNumber, String accountNumber, String bankCode, String iban, String telephone, String mail, String street, String zip, String city, Countries country, String note) {
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

    public String getName() {
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
