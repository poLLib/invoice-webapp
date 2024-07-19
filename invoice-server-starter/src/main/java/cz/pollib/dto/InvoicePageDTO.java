package cz.pollib.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoicePageDTO {
    private List<InvoiceDTO> invoices;
    private Long totalElements;
}
