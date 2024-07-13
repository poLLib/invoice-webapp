package cz.itnetwork.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class InvoiceStatisticsDTO {
    private BigDecimal allTimeSum;
    private Long invoicesCount;
    private BigDecimal currentYearSum;

}
