package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceStatisticsDTO;

public interface InvoiceStatisticsService {

    /**
     * Counts the invoices, profit of the invoices current year and in total
     *
     * @return InvoiceStatisticsDTO with the counted values
     */
    InvoiceStatisticsDTO getInvoiceStatistics();
}
