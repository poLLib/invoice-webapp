package cz.pollib.entity.filter;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;

/**
 * Represents the criteria for filtering invoices.
 * <p>
 * Attributes:
 * - buyerId: The ID of the buyer to filter invoices by.
 * - sellerId: The ID of the seller to filter invoices by.
 * - product: The product name to filter invoices by.
 * - minPrice: The minimum price to filter invoices by.
 * - maxPrice: The maximum price to filter invoices by.
 * - limit: The maximum number of invoices to return (default is 10).
 */
public record InvoiceFilter(
        Integer buyerId,
        Integer sellerId,
        String product,
        Long minPrice,
        Long maxPrice,
        @Schema(defaultValue = "10") @Min(1) Integer limit
) {
    public InvoiceFilter {
        if (limit == null) limit = 10;
    }
}