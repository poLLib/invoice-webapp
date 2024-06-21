package cz.itnetwork.service;

import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;
import cz.itnetwork.entity.PersonEntity;
import cz.itnetwork.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonStatisticsServiceImpl implements PersonStatisticsService {

    @Autowired
    private PersonRepository personRepository;

    @Override
    public List<PersonStatisticsDTO> getPersonStatistics() {
        List<PersonStatisticsDTO> list = new ArrayList<>();

        for(PersonEntity person : personRepository.findAll()) {
            PersonStatisticsDTO personStatisticsDTO = new PersonStatisticsDTO();

            personStatisticsDTO.setPersonId(person.getId());
            personStatisticsDTO.setPersonName(person.getName());
            personStatisticsDTO.setRevenue(personRepository.getPersonStatistics(person.getId()));

            list.add(personStatisticsDTO);
        }

        return list;
    }
}
