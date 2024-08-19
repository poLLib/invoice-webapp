package cz.pollib.controller;

import cz.pollib.dto.InvoiceDTO;
import cz.pollib.dto.PersonDTO;
import cz.pollib.dto.PersonStatisticsDTO;
import cz.pollib.service.PersonService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PersonController {

    @Autowired
    private PersonService personService;

    @PostMapping("/persons")
    public ResponseEntity<PersonDTO> addPerson(@RequestBody @Valid PersonDTO personDTO) {
        return new ResponseEntity<>(personService.addPerson(personDTO), HttpStatus.CREATED);
    }

    @GetMapping("/persons")
    public List<PersonDTO> getPeoplePages(@RequestParam(defaultValue = "0") int page,
                                          @RequestParam(defaultValue = "10") int size) {
        return personService.getAllPeoplePageable(page, size);
    }

    @GetMapping("/persons/total")
    public Long getTotalPeople() {
        return personService.getVisiblePersonsCount();
    }

    @GetMapping("/persons/{personId}")
    public PersonDTO getPerson(@PathVariable Long personId) {
        return personService.getPerson(personId);
    }

    @DeleteMapping("/persons/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long personId) {
        personService.removePerson(personId);
    }

    @PutMapping("/persons/{personId}")
    public PersonDTO editPerson(@PathVariable Long personId, @RequestBody @Valid PersonDTO data) {
        return personService.editPerson(personId, data);
    }

    @GetMapping("/identification/{identificationNumber}/sales")
    public List<InvoiceDTO> getSellerInvoices(@PathVariable String identificationNumber) {
        return personService.getInvoicesBySeller(identificationNumber);
    }

    @GetMapping("/identification/{identificationNumber}/purchases")
    public List<InvoiceDTO> getBuyersInvoices(@PathVariable String identificationNumber) {
        return personService.getInvoicesByBuyer(identificationNumber);
    }

    @GetMapping("/persons/statistics")
    public List<PersonStatisticsDTO> getPersonStatistics() {
        return personService.getPersonStatistics();
    }
}

//TODO: vytvoreni auth, dokumentace FE, BE a readme
