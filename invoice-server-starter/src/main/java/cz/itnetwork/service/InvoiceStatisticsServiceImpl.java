package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceStatisticsDTO;
import cz.itnetwork.entity.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class InvoiceStatisticsServiceImpl implements InvoiceStatisticsService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public InvoiceStatisticsDTO getInvoiceStatistics() {
        InvoiceStatisticsDTO invoiceStatisticsDTO = new InvoiceStatisticsDTO();
        Object values = invoiceRepository.getStats();
        Object[] stat = (Object[]) values;

        if (stat[0] == null) {
            stat[0] = 0;
        } else {
            invoiceStatisticsDTO.setAllTimeSum((BigDecimal) stat[0]);
        }
        invoiceStatisticsDTO.setInvoicesCount((Long) stat[1]);
        invoiceStatisticsDTO.setCurrentYearSum((BigDecimal) stat[2]);

        return invoiceStatisticsDTO;
    }
}
