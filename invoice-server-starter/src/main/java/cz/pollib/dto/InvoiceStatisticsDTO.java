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
public class InvoiceStatisticsDTO {

    private Long currentYearSum;
    private Long allTimeSum;
    private Long invoicesCount;

    public InvoiceStatisticsDTO() {
    }

    public InvoiceStatisticsDTO(Long currentYearSum, Long allTimeSum, Long invoicesCount) {
        this.currentYearSum = currentYearSum;
        this.allTimeSum = allTimeSum;
        this.invoicesCount = invoicesCount;
    }

    // GETTERs and SETTERs block


    public Long getCurrentYearSum() {
        return currentYearSum;
    }

    public void setCurrentYearSum(Long currentYearSum) {
        this.currentYearSum = currentYearSum;
    }

    public Long getAllTimeSum() {
        return allTimeSum;
    }

    public void setAllTimeSum(Long allTimeSum) {
        this.allTimeSum = allTimeSum;
    }

    public Long getInvoicesCount() {
        return invoicesCount;
    }

    public void setInvoicesCount(Long invoicesCount) {
        this.invoicesCount = invoicesCount;
    }
}
