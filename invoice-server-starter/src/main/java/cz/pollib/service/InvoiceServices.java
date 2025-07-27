package cz.pollib.service;

import cz.pollib.service.model.InvoicePageResponse;
import cz.pollib.service.model.InvoiceRequest;
import cz.pollib.service.model.InvoiceResponse;
import cz.pollib.service.model.InvoiceStatisticsResponse;
import cz.pollib.entity.filter.InvoiceFilter;


/**
 * Service interface for managing invoices.
 */
public interface InvoiceServices {

    /**
     * Creates an invoice and attach it to a Person.
     * Catches duplication of invoiceNumber in a database (DataIntegrityViolationException) and @throw DuplicateEntityException.
     *
     * @param request Invoice to create
     * @return Created Invoice
     */
    InvoiceResponse createInvoice(InvoiceRequest request);

    /**
     * Fetches all invoices in database and filter them according to user's parameters and make them pageable.
     * The second parameter of the class Pageable which determinate size of page is taken from param [limit] InvoiceFilter.
     *
     * @param invoiceFilter Parameters for filtration [buyerId], [sellerId], [product], [minPrice], [maxPrice], [limit (default value = 10)];
     * @param page Current page
     * @return List of InvoiceDTO and count of invoice elements after filtration
     */
    InvoicePageResponse searchInvoices(InvoiceFilter invoiceFilter, int page);

    /**
     * Look up for a specific invoice by [id]
     *
     * @param id Person we look for
     * @return Fetched Invoice
     */
    InvoiceResponse getInvoice(Long id);

    /**
     * <p>Sets hidden flag to true for the person with the matching [id]</p>
     * <p>In case a person with the passed [id] isn't found, the method <b>silently fails</b></p>
     *
     * @param id Invoice to delete
     */
    void deleteInvoice(Long id);

    /**
     * Edit invoice by [id] if exists
     *
     * @param id   Invoice to be edited
     * @param request New source of data for update invoice
     * @return Edited Invoice
     */
    InvoiceResponse updateInvoice(Long id, InvoiceRequest request);

    /**
     * Counts the invoices, profit of the invoices in current year and in total
     *
     * @return InvoiceStatisticsDTO with the counted values
     */
    InvoiceStatisticsResponse getInvoiceStatistics();
}
