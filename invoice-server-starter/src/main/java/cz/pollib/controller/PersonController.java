package cz.pollib.controller;

import cz.pollib.dto.InvoiceDTO;
import cz.pollib.dto.PersonDTO;
import cz.pollib.dto.PersonStatisticsDTO;
import cz.pollib.entity.PersonEntity;
import cz.pollib.service.PersonOperations;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    private final PersonOperations personOperations;

    public PersonController(PersonOperations personOperations) {
        this.personOperations = personOperations;
    }

    @PostMapping("/person")
    public ResponseEntity<PersonEntity> addPerson(@RequestBody @Valid PersonDTO personDTO) {
        return new ResponseEntity<>(personOperations.addPerson(personDTO), HttpStatus.CREATED);
    }

    @GetMapping("/persons")
    public List<PersonEntity> getPeoplePages(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        return personOperations.getAllPeoplePageable(page, size);
    }

    @GetMapping("/persons/total")
    public Long getTotalPeople() {
        return personOperations.getVisiblePersonsCount();
    }

    @GetMapping("/person/{personId}")
    public PersonEntity getPerson(@PathVariable Long personId) {
        return personOperations.getPerson(personId);
    }

    @DeleteMapping("/person/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long personId) {
        personOperations.removePerson(personId);
    }

    @PutMapping("/person/{personId}")
    public PersonEntity editPerson(@PathVariable Long personId, @RequestBody @Valid PersonDTO data) {
        return personOperations.editPerson(personId, data);
    }

    @GetMapping("/identification/{identificationNumber}/sales")
    public List<InvoiceDTO> getSellerInvoices(@PathVariable String identificationNumber) {
        return personOperations.getInvoicesBySeller(identificationNumber);
    }

    @GetMapping("/identification/{identificationNumber}/purchases")
    public List<InvoiceDTO> getBuyersInvoices(@PathVariable String identificationNumber) {
        return personOperations.getInvoicesByBuyer(identificationNumber);
    }

    @GetMapping("/persons/statistics")
    public List<PersonStatisticsDTO> getPersonStatistics() {
        return personOperations.getPersonStatistics();
    }
}
