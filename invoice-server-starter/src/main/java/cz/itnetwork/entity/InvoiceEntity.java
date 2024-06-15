package cz.itnetwork.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity(name = "invoice")
@Getter
@Setter
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
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
