package cz.pollib.dto;

import java.util.List;

/**
 * Represents a paginated response containing invoices.
 * <p>
 * Attributes:
 * - invoices: The list of invoices for the current page.
 * - totalElements: The total number of invoices available.
 */
public class InvoicePageDTO {
    private List<InvoiceDTO> invoices;
    private Long totalElements;

    public InvoicePageDTO() {
    }

    public InvoicePageDTO(List<InvoiceDTO> invoices, Long totalElements) {
        this.invoices = invoices;
        this.totalElements = totalElements;
    }

    // GETTERs and SETTERs block


    public List<InvoiceDTO> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<InvoiceDTO> invoices) {
        this.invoices = invoices;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }
}
