package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;

public interface InvoiceService {
    /**
     * Creates an invoice and attach it to Person
     *
     * @param data Invoice to create
     * @return Created invoice
     */
    InvoiceDTO createInvoice(InvoiceDTO data);

    /**
     * Edit invoice by [id] if exists
     *
     * @param id
     * @param data New source of data for update invoice
     * @return Edited Person
     */
    InvoiceDTO editInvoice(Long id, InvoiceDTO data);

    /**
     * Look up for a specific Person by [id]
     *
     * @param id
     * @return Fetched Person
     */
    InvoiceDTO detailInvoice(Long id);

    /**
     * <p>Sets hidden flag to true for the person with the matching [id]</p>
     * <p>In case a person with the passed [id] isn't found, the method <b>silently fails</b></p>
     *
     * @param id Invoice to delete
     */
    void deleteInvoice(Long id);
}
