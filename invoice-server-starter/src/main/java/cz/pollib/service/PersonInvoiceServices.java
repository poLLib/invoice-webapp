package cz.pollib.service;

import cz.pollib.service.model.InvoiceResponse;

import java.util.List;

/**
 * Service interface for managing person/invoice-related operations.
 */
public interface PersonInvoiceServices {

    /**
     * Fetches all invoices by [identificationNumber] of the seller
     *
     * @param identificationNumber The seller
     * @return List of the Invoice
     */
    List<InvoiceResponse> getInvoicesBySeller(String identificationNumber);

    /**
     * Fetches all invoices by [identificationNumber] of the buyer
     *
     * @param identificationNumber The buyer
     * @return List of the Invoice
     */
    List<InvoiceResponse> getInvoicesByBuyer(String identificationNumber);
}
