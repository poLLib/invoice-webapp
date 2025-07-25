package cz.pollib.entity;

import cz.pollib.constant.Countries;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

/**
 * Entity class representing a person in the database.
 * <p>
 * Attributes:
 * - id: The unique identifier for the person.
 * - name: The name of the person.
 * - identificationNumber: The identification number of the person.
 * - taxNumber: The tax number.
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
 * - hidden: Indicates if the person is hidden (default is false).
 * - purchases: The list of invoices where the person is the buyer.
 * - sales: The list of invoices where the person is the seller.
 */
@Entity(name = "Person")
@Table(name = "person")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String identificationNumber;

    private String taxNumber;

    @Column(nullable = false)
    private String accountNumber;

    @Column(nullable = false)
    private String bankCode;

    private String iban;

    @Column(nullable = false)
    private String telephone;

    @Column(nullable = false)
    private String mail;

    @Column(nullable = false)
    private String street;

    @Column(nullable = false)
    private String zip;

    @Column(nullable = false)
    private String city;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private Countries country;

    @Column
    private String note;

    private boolean hidden = false;

    @OneToMany(mappedBy = "buyer", cascade = CascadeType.REMOVE)
    private List<InvoiceEntity> purchases = new ArrayList<>();

    @OneToMany(mappedBy = "seller", cascade = CascadeType.REMOVE)
    private List<InvoiceEntity> sales = new ArrayList<>();

    public PersonEntity(String name, String identificationNumber, String taxNumber, String accountNumber, String bankCode, String iban, String telephone, String mail, String street, String zip, String city, Countries country, String note) {
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

    public PersonEntity() {

    }

    // GETTERs and SETTERs block

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isHidden() {
        return hidden;
    }
}
