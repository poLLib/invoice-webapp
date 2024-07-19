package cz.pollib.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceStatisticsDTO {
    private BigDecimal allTimeSum;
    private Long invoicesCount;
    private BigDecimal currentYearSum;

}
