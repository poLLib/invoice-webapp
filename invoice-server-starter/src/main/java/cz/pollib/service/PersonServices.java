package cz.pollib.service;

import cz.pollib.service.model.CreatePersonRequest;
import cz.pollib.service.model.PersonResponse;
import cz.pollib.service.model.PersonStatisticsResponse;
import cz.pollib.service.model.UpdatePersonRequest;

import java.util.List;

/**
 * Service interface for managing person-related operations.
 */
public interface PersonServices {

    /**
     * Creates a new person
     *
     * @param request Person to create
     * @return Newly created Person
     */
    PersonResponse createPerson(CreatePersonRequest request);

    /**
     * Fetches all non-hidden persons and page them in lists
     *
     * @param page Current page
     * @param size Number of pages
     * @return List of Person of current page
     */
    List<PersonResponse> getPersons(
            int page,
            int size
                                   );

    /**
     * Counts all visible persons
     *
     * @return Sum of persons
     */
    Long getVisiblePersonsCount();

    /**
     * Fetches the specific person by [id]
     * In case a person with the passed [id] isn't found, the method silently fails
     *
     * @param id Person to look up
     * @return Found Person
     */
    PersonResponse getPerson(Long id);

    /**
     * <p>Sets a hidden flag to true for the person with the matching [id]</p>
     * <p>In case a person with the passed [id] isn't found, the method <b>silently fails</b></p>
     *
     * @param id Person to delete
     */
    void removePerson(long id);

    /**
     * Edits person by [id], according to low of accountancy the old data must be stored, therefore the person gets hidden
     * also [identificationNumber, taxNumber] cannot be changed, so updated person sets the values from the previous ones
     *
     * @param id      Person to edit
     * @param request Updated data of person to edit
     * @return Edited Person
     */
    PersonResponse updatePerson(
            Long id,
            UpdatePersonRequest request
                               );

    /**
     * Fetches all values of {@link PersonStatisticsResponse} [Long personId, String personName, Long revenue]
     *
     * @return List of statistics of each person
     */
    List<PersonStatisticsResponse> getPersonStatistics();

}
