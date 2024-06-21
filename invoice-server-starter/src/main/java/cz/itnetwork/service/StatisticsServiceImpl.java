package cz.itnetwork.service;

import cz.itnetwork.dto.StatisticsDTO;
import cz.itnetwork.entity.InvoiceEntity;
import cz.itnetwork.entity.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Override
    public StatisticsDTO getStatistics() {

        LocalDate date = LocalDate.now();
        Long sumCurrentYear = invoiceRepository.sumCurrentYear(date);

        StatisticsDTO statisticsDTO = new StatisticsDTO();
        statisticsDTO.setCurrentYearSum(sumCurrentYear);

        return statisticsDTO;



 /*       Long currentYearSum = 0L;
        Long allTimeSum = 0L;
        int invoicesCount = 0;
        LocalDate time = LocalDate.now();

        for (InvoiceEntity i : invoiceRepository.findAll()) {
            if (i.getDueDate().equals(time.getYear()))
                currentYearSum = +i.getPrice();

            allTimeSum =+ i.getPrice();
            invoicesCount ++;
        }*/
    }
}
