package cz.itnetwork.dto;

import lombok.Data;

@Data
public class InvoiceStatisticsDTO {
    private Long currentYearSum;
    private Long allTimeSum;
    private Long invoicesCount;

}
