package cz.itnetwork.service;

import cz.itnetwork.dto.StatisticsDTO;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

public interface StatisticsService {
    StatisticsDTO getStatistics();

}
