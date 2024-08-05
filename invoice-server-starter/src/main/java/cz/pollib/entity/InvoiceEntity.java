package cz.pollib.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * Entity class representing an invoice in the database.
 * <p>
 * Attributes:
 * - id: The unique identifier for the invoice.
 * - invoiceNumber: The number of the invoice.
 * - issued: The date when the invoice was issued.
 * - dueDate: The date when the invoice is due.
 * - product: The product or service described in the invoice.
 * - price: The price of the product or service.
 * - vat: The VAT percentage applied to the invoice.
 * - note: Any additional notes related to the invoice.
 * - buyer: The person or entity buying the product or service.
 * - seller: The person or entity selling the product or service.
 */
@Entity(name = "invoice")
@Getter
@Setter
public class InvoiceEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private int invoiceNumber;

    @Column(nullable = false)
    private LocalDate issued;

    @Column(nullable = false)
    private LocalDate dueDate;

    @Column(nullable = false)
    private String product;

    @Column(nullable = false)
    private Long price;

    @Column(nullable = false)
    private int vat;

    @Column
    private String note;

    @ManyToOne
    private PersonEntity buyer;

    @ManyToOne
    private PersonEntity seller;

}
