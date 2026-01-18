package cz.pollib.service.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

/**
 * Represents a paginated response containing invoices.
 * <p>
 * Attributes:
 * - invoices: The list of invoices for the current page.
 * - totalElements: The total number of invoices available.
 */
@Schema(name = "InvoicePageResponse")
public record InvoicePageResponse(
        List<InvoiceResponse> invoices,
        long totalElements
) {
}