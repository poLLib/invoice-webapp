package cz.pollib.service.model;

import io.swagger.v3.oas.annotations.media.Schema;

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
@Schema(name = "InvoiceResponse")
public record InvoiceResponse(
        long id,
        int invoiceNumber,
        LocalDate issued,
        LocalDate dueDate,
        String product,
        long price,
        int vat,
        String note,
        PersonResponse buyer,
        PersonResponse seller
) {
}