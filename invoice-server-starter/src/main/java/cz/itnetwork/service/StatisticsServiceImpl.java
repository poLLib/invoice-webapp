package cz.itnetwork.service;

import cz.itnetwork.dto.StatisticsDTO;
import cz.itnetwork.entity.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public StatisticsDTO getStatistics() {
        StatisticsDTO statisticsDTO = new StatisticsDTO();

        statisticsDTO.setCurrentYearSum(invoiceRepository.getSumCurrentYear());
        statisticsDTO.setAllTimeSum(invoiceRepository.getSumAllTime());
        statisticsDTO.setInvoicesCount(invoiceRepository.getCountInvoices());

        return statisticsDTO;
    }
}
