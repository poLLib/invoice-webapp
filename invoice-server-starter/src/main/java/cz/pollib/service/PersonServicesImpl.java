package cz.pollib.service;

import cz.pollib.entity.PersonEntity;
import cz.pollib.entity.repository.PersonRepository;
import cz.pollib.service.common.PersonEntityProvider;
import cz.pollib.service.mapper.PersonMapper;
import cz.pollib.service.model.CreatePersonRequest;
import cz.pollib.service.model.PersonResponse;
import cz.pollib.service.model.PersonStatisticsResponse;
import cz.pollib.service.model.UpdatePersonRequest;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServicesImpl implements PersonServices {

    private final PersonRepository personRepository;
    private final PersonEntityProvider personEntityProvider;

    private final PersonMapper personMapper;

    private static final Logger logger = LoggerFactory.getLogger(PersonServicesImpl.class);

    public PersonServicesImpl(PersonMapper personMapper, PersonRepository personRepository, PersonEntityProvider personEntityProvider) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;
        this.personEntityProvider = personEntityProvider;
    }

    public PersonResponse createPerson(CreatePersonRequest request) {
        PersonEntity entity = personMapper.toEntity(request);
        entity = personRepository.saveAndFlush(entity);
        logger.info("Person was created id:{}", entity.getId());

        return personMapper.toModel(entity);
    }

    @Override
    public List<PersonResponse> getPersons(int page, int size) {
        List<PersonEntity> persons = new ArrayList<>(personRepository.findByHidden(false, PageRequest.of(page, size)));
        logger.info("Found {} persons", persons.size());

        return persons
                .stream()
                .map(personMapper::toModel)
                .toList();
    }

    @Override
    public Long getVisiblePersonsCount() {
        return personRepository.countAllVisiblePeople();
    }

    @Override
    public PersonResponse getPerson(Long id) {
        PersonEntity person = personEntityProvider.getEntity(id);
        if (person.isHidden()) {
            throw new EntityNotFoundException();
        }
        return personMapper.toModel(person);
    }

    @Override
    public void removePerson(long personId) {
        try {
            PersonEntity person = personEntityProvider.getEntity(personId);
            person.setHidden(true);

            personRepository.saveAndFlush(person);
            logger.info("Person was deleted id:{}", personId);

        } catch (EntityNotFoundException ignored) {
        }
    }

    @Override
    public PersonResponse updatePerson(Long id, UpdatePersonRequest request) {
        PersonEntity fetchedPerson = personEntityProvider.getEntity(id);
        PersonEntity updatedPerson = personMapper.merge(fetchedPerson, request);

        personRepository.saveAndFlush(updatedPerson);
        logger.info("Person updated id:{}", id);

        return personMapper.toModel(updatedPerson);
    }

    @Override
    public List<PersonStatisticsResponse> getPersonStatistics() {
        List<PersonStatisticsResponse> list = new ArrayList<>();

        for (PersonEntity person : personRepository.findByHidden(false)) {
            PersonStatisticsResponse personStatisticsResponse = new PersonStatisticsResponse();

            personStatisticsResponse.setPersonId(person.getId());
            personStatisticsResponse.setPersonName(person.getName());
            personStatisticsResponse.setRevenue(personRepository.sumAllPrice(person.getId()));

            list.add(personStatisticsResponse);
        }
        return list;
    }
}
