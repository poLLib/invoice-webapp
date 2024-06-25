package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceStatisticsServiceImpl implements InvoiceStatisticsService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public InvoiceStatisticsDTO getInvoiceStatistics() {
        InvoiceStatisticsDTO invoiceStatisticsDTO = new InvoiceStatisticsDTO();

        invoiceStatisticsDTO.setCurrentYearSum(invoiceRepository.getSumPriceCurrentYear());
        invoiceStatisticsDTO.setAllTimeSum(invoiceRepository.getSumPriceAllTime());
        invoiceStatisticsDTO.setInvoicesCount(invoiceRepository.getCountInvoices());

        return invoiceStatisticsDTO;
    }
}
