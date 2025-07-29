package cz.pollib.service.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import cz.pollib.service.validation.InvoiceDateValidatable;
import cz.pollib.service.validation.IssuedMustBeBeforeDueDate;
import cz.pollib.service.validation.SellerAndBuyerNotSame;
import cz.pollib.service.validation.SellerAndBuyerValidatable;
import cz.pollib.service.validation.Unique;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

/**
 * Request to create an invoice values and their validation constraints.
 * <p>
 * Attributes:
 * - invoiceNumber: The number of the invoice.
 * - issued: The date when the invoice was issued. The issued date must be before dueDate.
 * - dueDate: The date when the invoice is due. The dueDate must be after issued date.
 * - product: The product or service described in the invoice.
 * - price: The price of the product or service.
 * - vat: The VAT percentage applied to the invoice.
 * - note: Any additional notes related to the invoice.
 * - buyerId: ID of the person buying the product or service. Must be different from a seller.
 * - sellerId: ID of The person selling the product or service. Must be different from a buyer.
 */
@IssuedMustBeBeforeDueDate
@SellerAndBuyerNotSame
@Schema(name = "CreateOrUpdateInvoiceRequest")
public class CreateInvoiceRequest implements InvoiceDateValidatable, SellerAndBuyerValidatable {

    @Positive(message = "Invoice number must be positive number")
    @Unique(entity = "invoice", field = "invoiceNumber", message = "Invoice number must be unique")
    private int invoiceNumber;

    @NotNull(message = "Issued date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate issued;

    @NotNull(message = "Due date cannot be null")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dueDate;

    @NotBlank(message = "Product cannot be blank")
    private String product;

    @NotNull(message = "Price cannot be null")
    @Min(value = 1, message = "Price has minimal value 1")
    private Long price;

    @Positive(message = "VAT must be positive number")
    @Max(value = 100, message = "VAT must be less than 100")
    private byte vat;

    private String note;

    @NotNull(message = "Buyer ID cannot be null")
    private Long buyerId;

    @NotNull(message = "Seller ID cannot be null")
    private Long sellerId;

    public CreateInvoiceRequest() {
    }

    public CreateInvoiceRequest(int invoiceNumber, LocalDate issued, LocalDate dueDate, String product, Long price, byte vat, String note, Long buyer, Long seller) {
        this.invoiceNumber = invoiceNumber;
        this.issued = issued;
        this.dueDate = dueDate;
        this.product = product;
        this.price = price;
        this.vat = vat;
        this.note = note;
        this.buyerId = buyer;
        this.sellerId = seller;
    }

    // GETTERs and SETTERs block

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    @Override
    public LocalDate getIssued() {
        return issued;
    }

    public void setIssued(LocalDate issued) {
        this.issued = issued;
    }

    @Override
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

    @Override
    public Long getBuyerId() {
        return buyerId;
    }

    public void setBuyerId(Long buyerId) {
        this.buyerId = buyerId;
    }

    @Override
    public Long getSellerId() {
        return sellerId;
    }

    public void setSellerId(Long sellerId) {
        this.sellerId = sellerId;
    }
}
