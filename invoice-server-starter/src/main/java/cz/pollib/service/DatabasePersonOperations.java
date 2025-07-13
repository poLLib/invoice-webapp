package cz.pollib.service;

import cz.pollib.dto.PersonDTO;
import cz.pollib.dto.PersonStatisticsDTO;
import cz.pollib.dto.mapper.PersonMapper;
import cz.pollib.entity.Invoice;
import cz.pollib.entity.Person;
import cz.pollib.entity.repository.InvoiceRepository;
import cz.pollib.entity.repository.PersonRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DatabasePersonOperations implements PersonOperations {

    private final PersonMapper personMapper;

    private final PersonRepository personRepository;

    private final InvoiceRepository invoiceRepository;

    public DatabasePersonOperations(PersonMapper personMapper, PersonRepository personRepository, InvoiceRepository invoiceRepository) {
        this.personMapper = personMapper;
        this.personRepository = personRepository;
        this.invoiceRepository = invoiceRepository;
    }

    public Person createPerson(PersonDTO request) {
        Person entity = personMapper.toEntity(request);
        entity = personRepository.saveAndFlush(entity);
        return entity;
    }

    @Override
    public List<Person> getPersons(int page, int size) {
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
        } catch (EntityNotFoundException ignored) {
        }
    }

    @Override
    public Person editPerson(Long id, PersonDTO request) {
        Person existingPerson = updatePerson(id, request);

        return personRepository.save(existingPerson);
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
     * In case a person with the passed [id] doesn't exist a [{@link EntityNotFoundException}] is thrown.
     *
     * @param id Person to fetch
     * @return Fetched entity
     * @throws EntityNotFoundException In case a person with the passed [id] isn't found
     */
    private Person fetchPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Person with id " + id + " wasn't found in the database."));
    }

    /**
     * Updates a person.
     *
     * @param id Existing person
     * @param updatedPerson New data of the person to be updated
     * @return Updated entity
     */

    private Person updatePerson(Long id, PersonDTO updatedPerson) {
        Person existingPerson = fetchPersonById(id);

        existingPerson.setName(updatedPerson.getName());
        existingPerson.setAccountNumber(updatedPerson.getAccountNumber());
        existingPerson.setBankCode(updatedPerson.getBankCode());
        existingPerson.setIban(updatedPerson.getIban());
        existingPerson.setTelephone(updatedPerson.getTelephone());
        existingPerson.setMail(updatedPerson.getMail());
        existingPerson.setStreet(updatedPerson.getStreet());
        existingPerson.setZip(updatedPerson.getZip());
        existingPerson.setCity(updatedPerson.getCity());
        existingPerson.setCountry(updatedPerson.getCountry());
        existingPerson.setNote(updatedPerson.getNote());

        return existingPerson;
    }
}
