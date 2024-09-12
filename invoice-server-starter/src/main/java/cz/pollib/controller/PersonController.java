package cz.pollib.controller;

import cz.pollib.dto.InvoiceDTO;
import cz.pollib.dto.PersonDTO;
import cz.pollib.dto.PersonStatisticsDTO;
import cz.pollib.dto.mapper.InvoiceMapper;
import cz.pollib.dto.mapper.PersonMapper;
import cz.pollib.entity.InvoiceEntity;
import cz.pollib.entity.PersonEntity;
import cz.pollib.service.PersonOperations;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonOperations personOperations;

    private final PersonMapper personMapper;

    private final InvoiceMapper invoiceMapper;

    public PersonController(PersonOperations personOperations, PersonMapper personMapper, InvoiceMapper invoiceMapper) {
        this.personOperations = personOperations;
        this.personMapper = personMapper;
        this.invoiceMapper = invoiceMapper;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping("/person")
    public ResponseEntity<PersonDTO> addPerson(@RequestBody @Valid PersonDTO personDTO) {
        return new ResponseEntity<>(personMapper.toDTO(personOperations.addPerson(personDTO)), HttpStatus.CREATED);
    }

    @GetMapping("/persons")
    public List<PersonDTO> getPeoplePages(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        List<PersonEntity> people = personOperations.getAllPeoplePageable(page, size);
        return people.stream()
                .map(personMapper::toDTO)
                .toList();
    }

    @GetMapping("/persons/total")
    public Long getTotalPeople() {
        return personOperations.getVisiblePersonsCount();
    }

    @GetMapping("/person/{personId}")
    public PersonDTO getPerson(@PathVariable Long personId) {
        return personMapper.toDTO(personOperations.getPerson(personId));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping("/person/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long personId) {
        personOperations.removePerson(personId);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping("/person/{personId}")
    public PersonDTO editPerson(@PathVariable Long personId, @RequestBody @Valid PersonDTO data) {
        return personMapper.toDTO(personOperations.editPerson(personId, data));
    }

    @GetMapping("/identification/{identificationNumber}/sales")
    public List<InvoiceDTO> getSellerInvoices(@PathVariable String identificationNumber) {
        return listToDTO(personOperations.getInvoicesBySeller(identificationNumber));
    }

    @GetMapping("/identification/{identificationNumber}/purchases")
    public List<InvoiceDTO> getBuyersInvoices(@PathVariable String identificationNumber) {
        return listToDTO(personOperations.getInvoicesByBuyer(identificationNumber));
    }

    @GetMapping("/persons/statistics")
    public List<PersonStatisticsDTO> getPersonStatistics() {
        return personOperations.getPersonStatistics();
    }

    // region: Private methods

    /**
     * Converts the list of entities into the list of DTO's
     *
     * @param entities The list of InvoiceEntity
     * @return list of InvoiceDTO
     */
    private List<InvoiceDTO> listToDTO(List<InvoiceEntity> entities) {
        return entities.stream()
                .map(invoiceMapper::toDTO)
                .toList();
    }

}

//TODO: vytvoreni auth, dokumentace FE, BE a readme
//TODO: zapnout ochranu csfr v AppSecurityConfig
//TODO: poresit CORS

