package cz.itnetwork.service;

import cz.itnetwork.dto.PersonStatisticsDTO;

import java.util.List;

public interface PersonStatisticsService {

    /**
     * Fetches all values of PersonStatisticsDTO [Long personId, String personName, Long revenue]
     *
     * @return List of statistics of each person
     */
    List<PersonStatisticsDTO> getPersonStatistics();
}
