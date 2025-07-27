package cz.pollib.service.model;

import java.util.List;

/**
 * Represents a paginated response containing invoices.
 * <p>
 * Attributes:
 * - invoices: The list of invoices for the current page.
 * - totalElements: The total number of invoices available.
 */
public class InvoicePageResponse {
    private List<InvoiceResponse> invoices;
    private Long totalElements;

    public InvoicePageResponse() {
    }

    public InvoicePageResponse(List<InvoiceResponse> invoices, Long totalElements) {
        this.invoices = invoices;
        this.totalElements = totalElements;
    }

    // GETTERs and SETTERs block


    public List<InvoiceResponse> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<InvoiceResponse> invoices) {
        this.invoices = invoices;
    }

    public Long getTotalElements() {
        return totalElements;
    }

    public void setTotalElements(Long totalElements) {
        this.totalElements = totalElements;
    }
}
