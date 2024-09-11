package cz.pollib.service;

import cz.pollib.dto.InvoiceDTO;
import cz.pollib.dto.InvoicePageDTO;
import cz.pollib.dto.InvoiceStatisticsDTO;
import cz.pollib.entity.filter.InvoiceFilter;


/**
 * Service interface for managing invoices.
 */
public interface InvoiceOperations {

    /**
     * Creates an invoice and attach it to Person.
     * Catches duplication of invoiceNumber in database (DataIntegrityViolationException) and @throw DuplicateEntityException.
     *
     * @param data Invoice to create
     * @return Created InvoiceDTO
     */
    InvoiceDTO createInvoice(InvoiceDTO data);

    /**
     * Fetches all invoices in database and filter them according to user's parameters and make them pageable.
     * The second parameter of the class Pageable which determinate size of page is taken from param [limit] InvoiceFilter.
     *
     * @param invoiceFilter Parameters for filtration [buyerId], [sellerId], [product], [minPrice], [maxPrice], [limit (default value = 10)];
     * @param page Current page
     * @return List of InvoiceDTO and count of invoice elements after filtration
     */
    InvoicePageDTO getAllInvoicesPageable(InvoiceFilter invoiceFilter, int page);

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
     * Edit invoice by [id] if exists
     *
     * @param id   Invoice to be edited
     * @param data New source of data for update invoice
     * @return Edited InvoiceDTO
     */
    InvoiceDTO editInvoice(Long id, InvoiceDTO data);

    /**
     * Counts the invoices, profit of the invoices in current year and in total
     *
     * @return InvoiceStatisticsDTO with the counted values
     */
    InvoiceStatisticsDTO getInvoiceStatistics();
}
