package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.filter.InvoiceFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface InvoiceService {

    /**
     * Creates an invoice and attach it to Person
     *
     * @param data Invoice to create
     * @return Created InvoiceDTO
     */
    InvoiceDTO createInvoice(InvoiceDTO data);

    /**
     * Edit invoice by [id] if exists
     *
     * @param id   Invoice to be edited
     * @param data New source of data for update invoice
     * @return Edited InvoiceDTO
     */
    InvoiceDTO editInvoice(Long id, InvoiceDTO data);

    /**
     * Look up for a specific invoice by [id]
     *
     * @param id Person we look for
     * @return Fetched InvoiceDTO
     */
    InvoiceDTO detailInvoice(Long id);

    /**
     * <p>Sets hidden flag to true for the person with the matching [id]</p>
     * <p>In case a person with the passed [id] isn't found, the method <b>silently fails</b></p>
     *
     * @param id Invoice to delete
     */
    void deleteInvoice(Long id);

    /**
     * Fetches all invoices in database and filter them according to user's parameters
     *
     * @return List of InvoiceDTO
     */
    List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter);
    /*List<InvoiceDTO> getAllInvoices(InvoiceFilter invoiceFilter)*/

    /**
     * Counts the invoices, profit of the invoices current year and in total
     *
     * @return InvoiceStatisticsDTO with the counted values
     */
    InvoiceStatisticsDTO getInvoiceStatistics();
}
