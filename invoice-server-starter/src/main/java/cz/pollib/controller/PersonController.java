package cz.pollib.controller;

import cz.pollib.service.PersonServices;
import cz.pollib.service.model.CreatePersonRequest;
import cz.pollib.service.model.PersonResponse;
import cz.pollib.service.model.PersonStatisticsResponse;
import cz.pollib.service.model.UpdatePersonRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
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
@Tag(name = "Person", description = "Person management")
public class PersonController {

    private final PersonServices personServices;

    public PersonController(PersonServices personServices) {
        this.personServices = personServices;

    }

    @Operation(
            operationId = "createPerson",
            tags = "Person",
            summary = "Creates new Person"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "201",
                            description = "Person was created",
                            content = {@Content(schema = @Schema(implementation = PersonResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    @PostMapping(
            value = "/person",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonResponse> createPerson(@RequestBody @Valid CreatePersonRequest request) {
        return new ResponseEntity<>(personServices.createPerson(request), CREATED);
    }

    @Operation(
            operationId = "getPerson",
            tags = "Person",
            summary = "Returns existing Person"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Returns Person",
                            content = {@Content(schema = @Schema(implementation = PersonResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Resource not found",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    @GetMapping(
            value = "/person/{personId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonResponse> getPerson(
            @Parameter(
                    description = "Unique identifier",
                    required = true
            )
            @PathVariable Long personId
    ) {
        return new ResponseEntity<>(personServices.getPerson(personId), OK);
    }

    @Operation(
            operationId = "updatePerson",
            tags = "Person",
            summary = "Updates existing Person"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Person was updated",
                            content = {@Content(schema = @Schema(implementation = PersonResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Resource not found",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    @PutMapping(
            value = "/person/{personId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<PersonResponse> updatePerson(
            @Parameter(
                    description = "Unique identifier",
                    required = true
            )
            @PathVariable Long personId,
            @RequestBody @Valid UpdatePersonRequest request
    ) {
        return new ResponseEntity<>(personServices.updatePerson(personId, request), OK);
    }

    @Operation(
            operationId = "deletePerson",
            tags = "Person",
            summary = "Deletes existing Person"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "204",
                            description = "Person was deleted"
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "Resource not found",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    @ResponseStatus(NO_CONTENT)
    @DeleteMapping(value = "/person/{personId}")
    public void deletePerson(
            @Parameter(
                    description = "Unique identifier",
                    required = true
            )
            @PathVariable Long personId
    ) {
        personServices.removePerson(personId);
    }

    @Operation(
            operationId = "searchPersons",
            tags = "Persons",
            summary = "Search Persons"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Returns list of Persons",
                            content = {@Content(array = @ArraySchema(schema = @Schema(implementation = PersonResponse.class)))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    @GetMapping(
            value = "/persons",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<PersonResponse>> getPersons(
            @Parameter(
                    description = "Page",
                    schema = @Schema(defaultValue = "0")
            )
            @RequestParam(defaultValue = "0") int page,
            @Parameter(
                    description = "Page size",
                    schema = @Schema(defaultValue = "10")
            )
            @RequestParam(defaultValue = "10") int size
    ) {
        return new ResponseEntity<>(personServices.getPersons(page, size), OK);
    }

    @Operation(
            operationId = "getCountPersons",
            tags = "Person",
            summary = "Returns count of all visible persons"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Returns count of all visible persons",
                            content = {@Content(schema = @Schema(implementation = Long.class))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    @GetMapping(
            value = "/persons/total",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<Long> getCountPersons() {
        return new ResponseEntity<>(personServices.getVisiblePersonsCount(), OK);
    }

    @Operation(
            operationId = "getPersonStatistics",
            tags = "Person",
            summary = "Returns statistics of Persons"
    )
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Returns statistics of Persons",
                            content = {@Content(schema = @Schema(implementation = PersonStatisticsResponse.class))}
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request",
                            content = {@Content(schema = @Schema(implementation = ErrorResponse.class))}
                    )
            }
    )
    @GetMapping(
            value = "/person/statistics",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<PersonStatisticsResponse>> getPersonStatistics() {
        return new ResponseEntity<>(personServices.getPersonStatistics(), OK);
    }
}


