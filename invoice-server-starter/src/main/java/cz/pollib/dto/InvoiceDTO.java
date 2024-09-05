package cz.pollib.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.pollib.dto.validation.InvoiceDatesValidAnnotation;
import cz.pollib.dto.validation.PersonNotNullValidAnnotation;
import cz.pollib.dto.validation.SellerBuyerNotSameValidAnnotation;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;


/**
 * Data Transfer Object for an invoice details and their validation constrains.
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
@InvoiceDatesValidAnnotation
@SellerBuyerNotSameValidAnnotation
public class InvoiceDTO {

    @JsonProperty("_id")
    private Long id;

    @Positive(message = "Zadejte číslo faktury v absolutním čísle")
    private int invoiceNumber;

    @NotNull(message = "Zadejte datum vystavení faktury")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issued;

    @NotNull(message = "Zadejte datum splatnosti faktury")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @NotBlank(message = "Vyplňte pole položka")
    private String product;

    @NotNull(message = "Hodnota musí být větší než 0")
    private Long price;

    @Positive(message = "Zadejte DPH v absolutním čísle")
    @Max(value = 100, message = "Hodnota nemůže být větší než 100")
    private int vat;

    private String note;

    @PersonNotNullValidAnnotation
    private PersonDTO buyer;

    @PersonNotNullValidAnnotation
    private PersonDTO seller;

    public InvoiceDTO() {
    }

    public InvoiceDTO(Long id, int invoiceNumber, LocalDate issued, LocalDate dueDate, String product, Long price, int vat, String note, PersonDTO buyer, PersonDTO seller) {
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

    // GETTERs and SETTERs block

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Positive(message = "Zadejte číslo faktury v absolutním čísle")
    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public @NotNull(message = "Zadejte datum vystavení faktury") LocalDate getIssued() {
        return issued;
    }

    public void setIssued(LocalDate issued) {
        this.issued = issued;
    }

    public @NotNull(message = "Zadejte datum splatnosti faktury") LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public @NotBlank(message = "Vyplňte pole položka") String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public @NotNull(message = "Hodnota musí být větší než 0") Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    @Positive(message = "Zadejte DPH v absolutním čísle")
    @Max(value = 100, message = "Hodnota nemůže být větší než 100")
    public int getVat() {
        return vat;
    }

    public void setVat(int vat) {
        this.vat = vat;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public PersonDTO getBuyer() {
        return buyer;
    }

    public void setBuyer(PersonDTO buyer) {
        this.buyer = buyer;
    }

    public PersonDTO getSeller() {
        return seller;
    }

    public void setSeller(PersonDTO seller) {
        this.seller = seller;
    }
}
