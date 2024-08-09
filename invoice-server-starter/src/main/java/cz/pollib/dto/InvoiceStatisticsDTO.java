package cz.pollib.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

/**
 * Data Transfer Object for invoice statistics.
 * <p>
 * Attributes:
 * - allTimeSum: The total sum of all invoices ever issued.
 * - invoicesCount: The total number of invoices.
 * - currentYearSum: The sum of invoices issued in the current year.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceStatisticsDTO {

    private Long currentYearSum;
    private Long allTimeSum;
    private Long invoicesCount;
}
