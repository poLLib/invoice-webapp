package cz.pollib.entity;

import cz.pollib.constant.Countries;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
@Entity(name = "person")
@Getter
@Setter
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
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

    @Column(nullable = true)
    private String note;

    private boolean hidden = false;

    @OneToMany(mappedBy = "buyer")
    private List<InvoiceEntity> purchases;

    @OneToMany(mappedBy = "seller")
    private List<InvoiceEntity> sales;
}
