package cz.pollib.service;

import cz.pollib.entity.PersonEntity;
import cz.pollib.entity.repository.PersonRepository;
import cz.pollib.service.common.PersonEntityProvider;
import cz.pollib.service.common.PersonInvoiceCacheEvictor;
import cz.pollib.service.mapper.PersonMapper;
import cz.pollib.service.model.CreatePersonRequest;
import cz.pollib.service.model.PersonResponse;
import cz.pollib.service.model.PersonStatisticsResponse;
import cz.pollib.service.model.UpdatePersonRequest;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonServicesImpl implements PersonServices {

    private static final Logger logger = LoggerFactory.getLogger(PersonServicesImpl.class);
    private final PersonRepository personRepository;
    private final PersonEntityProvider personEntityProvider;
    private final PersonMapper personMapper;
    private final PersonInvoiceCacheEvictor personInvoiceCacheEvictor;

    public PersonServicesImpl(
            PersonMapper personMapper,
            PersonRepository personRepository,
            PersonEntityProvider personEntityProvider,
            PersonInvoiceCacheEvictor personInvoiceCacheEvictor
                             ) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;
        this.personEntityProvider = personEntityProvider;
        this.personInvoiceCacheEvictor = personInvoiceCacheEvictor;
    }

    @Override
    @CacheEvict(
            value = {"get-persons", "get-person-statistics"},
            allEntries = true
    )
    @Transactional
    public PersonResponse createPerson(CreatePersonRequest request) {
        PersonEntity entity = personMapper.toEntity(request);
        entity = personRepository.saveAndFlush(entity);
        logger.info(
                "Person was created id:{}",
                entity.getId()
                   );

        return personMapper.toModel(entity);
    }

    @Override
    @Cacheable(
            value = "get-persons",
            key = "'page=' + #page + '-size=' + #size"
    )
    @Transactional(readOnly = true)
    public List<PersonResponse> getPersons(
            int page,
            int size
                                          ) {
        List<PersonEntity> persons = new ArrayList<>(personRepository.findByHidden(
                false,
                PageRequest.of(
                        page,
                        size
                              )
                                                                                  ));
        logger.info(
                "Found {} persons",
                persons.size()
                   );

        return persons
                .stream()
                .map(personMapper::toModel)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Long getVisiblePersonsCount() {
        return personRepository.countAllByHidden(false);
    }

    @Override
    @Transactional(readOnly = true)
    public PersonResponse getPerson(Long id) {
        PersonEntity person = personEntityProvider.getEntity(id);
        if (person.isHidden()) {
            throw new EntityNotFoundException();
        }
        return personMapper.toModel(person);
    }

    @Override
    @CacheEvict(
            value = {"get-persons", "search-invoices", "get-person-statistics"},
            allEntries = true
    )
    @Transactional
    public void removePerson(long personId) {
        try {
            PersonEntity person = personEntityProvider.getEntity(personId);
            person.setHidden(true);

            personInvoiceCacheEvictor.evictPersonInvoiceCache(person.getIdentificationNumber());

            personRepository.saveAndFlush(person);
            logger.info(
                    "Person was deleted id:{}",
                    personId
                       );

        } catch (EntityNotFoundException ignored) {
        }
    }

    @Override
    @CacheEvict(
            value = {"get-persons", "get-person-statistics"},
            allEntries = true
    )
    @Transactional
    public PersonResponse updatePerson(
            Long id,
            UpdatePersonRequest request
                                      ) {
        PersonEntity fetchedPerson = personEntityProvider.getEntity(id);
        PersonEntity updatedPerson = personMapper.merge(
                fetchedPerson,
                request
                                                       );

        personRepository.saveAndFlush(updatedPerson);
        logger.info(
                "Person updated id:{}",
                id
                   );

        return personMapper.toModel(updatedPerson);
    }

    @Override
    @Cacheable(value = "get-person-statistics")
    @Transactional(readOnly = true)
    public List<PersonStatisticsResponse> getPersonStatistics() {
        return personRepository.findByHidden(false)
                               .stream()
                               .map(person ->
                                            new PersonStatisticsResponse(
                                                    person.getId(),
                                                    person.getName(),
                                                    personRepository.sumAllPrice(person.getId())
                                            ))
                               .toList();
    }
}