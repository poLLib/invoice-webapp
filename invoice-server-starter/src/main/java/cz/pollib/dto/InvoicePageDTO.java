package cz.pollib.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Represents a paginated response containing invoices.
 * <p>
 * Attributes:
 * - invoices: The list of invoices for the current page.
 * - totalElements: The total number of invoices available.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InvoicePageDTO {
    private List<InvoiceDTO> invoices;
    private Long totalElements;
}
