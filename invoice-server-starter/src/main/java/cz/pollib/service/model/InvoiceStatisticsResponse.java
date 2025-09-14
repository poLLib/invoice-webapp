package cz.pollib.service.model;

/**
 * Data Transfer Object for invoice statistics.
 * <p>
 * Attributes:
 * - allTimeSum: The total sum of all invoices ever issued.
 * - invoicesCount: The total number of invoices.
 * - currentYearSum: The sum of invoices issued in the current year.
 */
public record InvoiceStatisticsResponse(
        Long currentYearSum,
        Long allTimeSum,
        Long invoicesCount
) {
}