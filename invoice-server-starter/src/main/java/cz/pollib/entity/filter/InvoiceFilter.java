package cz.pollib.entity.filter;

import lombok.Data;

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
@Data
public class InvoiceFilter {

    private Integer buyerId;
    private Integer sellerId;
    private String product;
    private Long minPrice;
    private Long maxPrice;
    private int limit = 10;
}
