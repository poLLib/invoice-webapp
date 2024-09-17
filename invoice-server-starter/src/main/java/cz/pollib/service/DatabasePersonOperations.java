package cz.pollib.service;

import cz.pollib.dto.PersonDTO;
import cz.pollib.dto.PersonStatisticsDTO;
import cz.pollib.dto.mapper.InvoiceMapper;
import cz.pollib.dto.mapper.PersonMapper;
import cz.pollib.entity.Invoice;
import cz.pollib.entity.Person;
import cz.pollib.entity.repository.InvoiceRepository;
import cz.pollib.entity.repository.PersonRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatabasePersonOperations implements PersonOperations {

    private final PersonMapper personMapper;

    private final PersonRepository personRepository;

    private final InvoiceRepository invoiceRepository;

    private final InvoiceMapper invoiceMapper;

    public DatabasePersonOperations(PersonMapper personMapper, PersonRepository personRepository, InvoiceRepository invoiceRepository, InvoiceMapper invoiceMapper) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;
        this.invoiceRepository = invoiceRepository;
        this.invoiceMapper = invoiceMapper;
    }

    public Person addPerson(PersonDTO personDTO) {
        Person entity = personMapper.toEntity(personDTO);
        entity = personRepository.saveAndFlush(entity);
        return entity;
    }

    @Override
    public List<Person> getAllPeoplePageable(int page, int size) {
        return new ArrayList<>(personRepository.findByHidden(false, PageRequest.of(page, size)));
    }

    @Override
    public Long getVisiblePersonsCount() {
        return personRepository.countAllVisiblePeople();
    }

    @Override
    public Person getPerson(Long id) {
        return fetchPersonById(id);
    }

    @Override
    public void removePerson(long personId) {
        try {
            Person person = fetchPersonById(personId);
            person.setHidden(true);

            personRepository.saveAndFlush(person);
        } catch (NotFoundException ignored) {
            // The contract in the interface states, that no exception is thrown, if the entity is not found.
        }
    }

    @Override
    public Person editPerson(Long id, PersonDTO data) {
        Person fetchedPerson = fetchPersonById(id);
        PersonDTO newPerson = new PersonDTO();
        newPerson.setIdentificationNumber(fetchedPerson.getIdentificationNumber());
        newPerson.setTaxNumber(fetchedPerson.getTaxNumber());

        removePerson(id);
        data.setId(null);
        return addPerson(data);
    }

    @Override
    public List<Invoice> getInvoicesBySeller(String identificationNumber) {
        return invoiceRepository.findAll().stream()
                .filter(i -> i.getSeller().getIdentificationNumber().equals(identificationNumber))
                .toList();
    }

    @Override
    public List<Invoice> getInvoicesByBuyer(String identificationNumber) {
        List<Invoice> list = new ArrayList<>();
        for (Invoice i : invoiceRepository.findAll()) {
            if (i.getBuyer().getIdentificationNumber().equals(identificationNumber))
                list.add(i);
        }
        return list;
    }

    @Override
    public List<PersonStatisticsDTO> getPersonStatistics() {
        List<PersonStatisticsDTO> list = new ArrayList<>();

        for (Person person : personRepository.findByHidden(false)) {
            PersonStatisticsDTO personStatisticsDTO = new PersonStatisticsDTO();

            personStatisticsDTO.setPersonId(person.getId());
            personStatisticsDTO.setPersonName(person.getName());
            personStatisticsDTO.setRevenue(personRepository.getTotalIncome(person.getId()));

            list.add(personStatisticsDTO);
        }

        return list;
    }

    // region: Private methods

    /**
     * Attempts to fetch a person.
     * In case a person with the passed [id] doesn't exist a [{@link org.webjars.NotFoundException}] is thrown.
     *
     * @param id Person to fetch
     * @return Fetched entity
     * @throws org.webjars.NotFoundException In case a person with the passed [id] isn't found
     */
    private Person fetchPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with id " + id + " wasn't found in the database."));
    }

}
