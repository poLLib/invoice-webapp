package cz.pollib.service.model;

import cz.pollib.entity.PersonEntity;

import java.time.LocalDate;

/**
 * Response for an invoice details
 * <p>
 * Attributes:
 * - id: The unique identifier for the invoice.
 * - invoiceNumber: The number of the invoice.
 * - issued: The date when the invoice was issued
 * - dueDate: The date when the invoice is due
 * - product: The product or service described in the invoice.
 * - price: The price of the product or service
 * - vat: The VAT percentage applied to the invoice
 * - note: Any additional notes related to the invoice
 * - buyer: The person or entity buying the product or service
 * - seller: The person or entity selling the product or service
 */
public class InvoiceResponse {

    private Long id;

    private int invoiceNumber;

    private LocalDate issued;

    private LocalDate dueDate;

    private String product;

    private Long price;

    private byte vat;

    private String note;

    private PersonResponse buyer;

    private PersonResponse seller;

    public InvoiceResponse() {
    }

    public InvoiceResponse(Long id, int invoiceNumber, LocalDate issued, LocalDate dueDate, String product, Long price, byte vat, String note, PersonResponse buyer, PersonResponse seller) {
        this.id = id;
        this.invoiceNumber = invoiceNumber;
        this.issued = issued;
        this.dueDate = dueDate;
        this.product = product;
        this.price = price;
        this.vat = vat;
        this.note = note;
        this.buyer = buyer;
        this.seller = seller;
    }

    public InvoiceResponse(Long id, int invoiceNumber, LocalDate issued, LocalDate dueDate, String product, Long price, byte vat, String note, PersonEntity buyer, PersonEntity seller) {
    }

    // GETTERs and SETTERs block

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public LocalDate getIssued() {
        return issued;
    }

    public void setIssued(LocalDate issued) {
        this.issued = issued;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public byte getVat() {
        return vat;
    }

    public void setVat(byte vat) {
        this.vat = vat;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public PersonResponse getBuyer() {
        return buyer;
    }

    public void setBuyer(PersonResponse buyer) {
        this.buyer = buyer;
    }

    public PersonResponse getSeller() {
        return seller;
    }

    public void setSeller(PersonResponse seller) {
        this.seller = seller;
    }
}
