/*  _____ _______         _                      _
 * |_   _|__   __|       | |                    | |
 *   | |    | |_ __   ___| |___      _____  _ __| | __  ___ ____
 *   | |    | | '_ \ / _ \ __\ \ /\ / / _ \| '__| |/ / / __|_  /
 *  _| |_   | | | | |  __/ |_ \ V  V / (_) | |  |   < | (__ / /
 * |_____|  |_|_| |_|\___|\__| \_/\_/ \___/|_|  |_|\_(_)___/___|
 *                                _
 *              ___ ___ ___ _____|_|_ _ _____
 *             | . |  _| -_|     | | | |     |  LICENCE
 *             |  _|_| |___|_|_|_|_|___|_|_|_|
 *             |_|
 *
 *   PROGRAMOVÁNÍ  <>  DESIGN  <>  PRÁCE/PODNIKÁNÍ  <>  HW A SW
 *
 * Tento zdrojový kód je součástí výukových seriálů na
 * IT sociální síti WWW.ITNETWORK.CZ
 *
 * Kód spadá pod licenci prémiového obsahu a vznikl díky podpoře
 * našich členů. Je určen pouze pro osobní užití a nesmí být šířen.
 * Více informací na http://www.itnetwork.cz/licence
 */
package cz.pollib.service;

import cz.pollib.dto.InvoiceDTO;
import cz.pollib.dto.PersonDTO;
import cz.pollib.dto.PersonStatisticsDTO;
import cz.pollib.dto.mapper.InvoiceMapper;
import cz.pollib.dto.mapper.PersonMapper;
import cz.pollib.entity.InvoiceEntity;
import cz.pollib.entity.PersonEntity;
import cz.pollib.entity.repository.InvoiceRepository;
import cz.pollib.entity.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.webjars.NotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private InvoiceMapper invoiceMapper;

    public PersonDTO addPerson(PersonDTO personDTO) {
        PersonEntity entity = personMapper.toEntity(personDTO);
        entity = personRepository.saveAndFlush(entity);
        return personMapper.toDTO(entity);
    }

    @Override
    public List<PersonDTO> getAllPeoplePageable(int page, int size) {
        return personRepository.findByHidden(false, PageRequest.of(page, size))
                .stream()
                .map(i -> personMapper.toDTO(i))
                .collect(Collectors.toList());
    }

    @Override
    public Long getVisiblePersonsCount() {
        return personRepository.countAllVisiblePeople();
    }

    @Override
    public PersonDTO getPerson(Long id) {
        PersonEntity person = fetchPersonById(id);
        return personMapper.toDTO(person);
    }

    @Override
    public void removePerson(long personId) {
        try {
            PersonEntity person = fetchPersonById(personId);
            person.setHidden(true);

            personRepository.saveAndFlush(person);
        } catch (NotFoundException ignored) {
            // The contract in the interface states, that no exception is thrown, if the entity is not found.
        }
    }

    @Override
    public PersonDTO editPerson(Long id, PersonDTO data) {
        PersonEntity fetchedPerson = fetchPersonById(id);
        PersonDTO newPerson = new PersonDTO();
        newPerson.setIdentificationNumber(fetchedPerson.getIdentificationNumber());
        newPerson.setTaxNumber(fetchedPerson.getTaxNumber());

        removePerson(id);
        data.setId(null);
        return addPerson(data);
    }

    @Override
    public List<InvoiceDTO> getInvoicesBySeller(String identificationNumber) {
        return invoiceRepository.findAll().stream()
                .filter(i -> i.getSeller().getIdentificationNumber().equals(identificationNumber))
                .map(invoiceMapper::toDTO)
                .toList();
    }

    @Override
    public List<InvoiceDTO> getInvoicesByBuyer(String identificationNumber) {
        List<InvoiceDTO> list = new ArrayList<>();
        for (InvoiceEntity i : invoiceRepository.findAll()) {
            if (i.getBuyer().getIdentificationNumber().equals(identificationNumber))
                list.add(invoiceMapper.toDTO(i));
        }
        return list;
    }

    @Override
    public List<PersonStatisticsDTO> getPersonStatistics() {
        List<PersonStatisticsDTO> list = new ArrayList<>();

        for (PersonEntity person : personRepository.findByHidden(false)) {
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
    private PersonEntity fetchPersonById(Long id) {
        return personRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Person with id " + id + " wasn't found in the database."));
    }
    // endregion
}
