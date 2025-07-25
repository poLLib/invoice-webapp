package cz.pollib.controller;

import cz.pollib.service.PersonServices;
import cz.pollib.service.model.CreatePersonRequest;
import cz.pollib.service.model.PersonResponse;
import cz.pollib.service.model.PersonStatisticsResponse;
import cz.pollib.service.model.UpdatePersonRequest;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PersonController {

    private final PersonServices personServices;

    public PersonController(PersonServices personServices) {
        this.personServices = personServices;

    }

    @PostMapping("/person")
    public ResponseEntity<PersonResponse> createPerson(@RequestBody @Valid CreatePersonRequest request) {
        return new ResponseEntity<>(personServices.createPerson(request), CREATED);
    }

    @GetMapping("/person/{personId}")
    public ResponseEntity<PersonResponse> getPerson(@PathVariable Long personId) {
        return new ResponseEntity<>(personServices.getPerson(personId), OK);
    }

    @PutMapping("/person/{personId}")
    public ResponseEntity<PersonResponse> updatePerson(@PathVariable Long personId, @RequestBody @Valid UpdatePersonRequest request) {
        return new ResponseEntity<>(personServices.updatePerson(personId, request), OK);
    }

    @DeleteMapping("/person/{personId}")
    @ResponseStatus(NO_CONTENT)
    public void deletePerson(@PathVariable Long personId) {
        personServices.removePerson(personId);
    }

    @GetMapping("/persons")
    public ResponseEntity<List<PersonResponse>> getPersons(@RequestParam(defaultValue = "0") int page,
                                                           @RequestParam(defaultValue = "10") int size) {
        return new ResponseEntity<>(personServices.getPersons(page, size), OK);
    }

    @GetMapping("/persons/total")
    public ResponseEntity<Long> getAllPersons() {
        return new ResponseEntity<>(personServices.getVisiblePersonsCount(), OK);
    }

    @GetMapping("/person/statistics")
    public ResponseEntity<List<PersonStatisticsResponse>> getPersonStatistics() {
        return new ResponseEntity<>(personServices.getPersonStatistics(), OK);
    }
}


