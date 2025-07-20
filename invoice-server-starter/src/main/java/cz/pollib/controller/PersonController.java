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

    public PersonController(PersonOperations personOperations, PersonMapper personMapper) {
        this.personOperations = personOperations;
        this.personMapper = personMapper;

    }

    @PostMapping("/person")
    public ResponseEntity<PersonDTO> createPerson(@RequestBody @Valid PersonDTO request) {
        return new ResponseEntity<>(personMapper.toDTO(personOperations.createPerson(request)), HttpStatus.CREATED);
    }

    @GetMapping("/person/{personId}") // TODO: 20.07.2025 nesmi nalezat hidden true. i ostatni metody!!!
    public PersonDTO getPerson(@PathVariable Long personId) {
        return personMapper.toDTO(personOperations.getPerson(personId));
    }

    @PutMapping("/person/{personId}")
    public PersonDTO editPerson(@PathVariable Long personId, @RequestBody @Valid PersonDTO request) {
        return personMapper.toDTO(personOperations.editPerson(personId, request));
    }

    @DeleteMapping("/person/{personId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletePerson(@PathVariable Long personId) {
        personOperations.removePerson(personId);
    }

    @GetMapping("/persons")
    public List<PersonDTO> getPersons(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size) {
        List<Person> persons = personOperations.getPersons(page, size);
        return persons.stream()
                .map(personMapper::toDTO)
                .toList();
    }

    @GetMapping("/persons/total")
    public Long getAllPersons() {
        return personOperations.getVisiblePersonsCount();
    }

    @GetMapping("/persons/statistics")
    public List<PersonStatisticsDTO> getPersonStatistics() {
        return personOperations.getPersonStatistics();
    }
}


