package cz.pollib.controller;

import cz.pollib.dto.InvoiceDTO;
import cz.pollib.dto.PersonDTO;
import cz.pollib.dto.PersonStatisticsDTO;
import cz.pollib.dto.mapper.InvoiceMapper;
import cz.pollib.dto.mapper.PersonMapper;
import cz.pollib.entity.Invoice;
import cz.pollib.entity.Person;
import cz.pollib.service.PersonOperations;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
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

    @PostMapping("/person")
    public ResponseEntity<PersonDTO> addPerson(@RequestBody @Valid PersonDTO personDTO) {
        return new ResponseEntity<>(personMapper.toDTO(personOperations.addPerson(personDTO)), HttpStatus.CREATED);
    }

    @GetMapping("/persons")
    public List<PersonDTO> getPeoplePages(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        List<Person> people = personOperations.getAllPeoplePageable(page, size);
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

    @DeleteMapping("/person/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long personId) {
        personOperations.removePerson(personId);
    }

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
     * @param entities The list of Invoice
     * @return list of InvoiceDTO
     */
    private List<InvoiceDTO> listToDTO(List<Invoice> entities) {
        return entities.stream()
                .map(invoiceMapper::toDTO)
                .toList();
    }

}


