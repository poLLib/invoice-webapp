package cz.pollib.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import cz.pollib.dto.validation.ValidInvoiceDates;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


/**
 * Data Transfer Object for an invoice details and their validation constrains.
 *
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
@Data
@NoArgsConstructor
@AllArgsConstructor
@ValidInvoiceDates
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

    @Positive(message = "Zadejte cenu v absolutním čísle")
    private Long price;

    @Positive(message = "Zadejte DPH v absolutním čísle")
    @Max(value = 100, message = "Číslo nemůže být větší než 100")
    private int vat;

    private String note;

    @NotNull(message = "Zvolte odběratele")
    private PersonDTO buyer;

    @NotNull(message = "Zvolte dodavatele")
    private PersonDTO seller;
}
