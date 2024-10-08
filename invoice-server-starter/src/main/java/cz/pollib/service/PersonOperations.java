package cz.pollib.service;

import cz.pollib.dto.PersonDTO;
import cz.pollib.dto.PersonStatisticsDTO;
import cz.pollib.entity.Invoice;
import cz.pollib.entity.Person;

import java.util.List;

/**
 * Service interface for managing person-related operations.
 */
public interface PersonOperations {

    /**
     * Creates a new person
     *
     * @param personDTO Person to create
     * @return Newly created Person
     */
    Person addPerson(PersonDTO personDTO);

    /**
     * Fetches all non-hidden persons and page them in lists
     *
     * @param page Current page
     * @param size Number of pages
     * @return List of Person of current page
     */
    List<Person> getAllPeoplePageable(int page, int size);

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
    Person getPerson(Long id);

    /**
     * <p>Sets hidden flag to true for the person with the matching [id]</p>
     * <p>In case a person with the passed [id] isn't found, the method <b>silently fails</b></p>
     *
     * @param id Person to delete
     */
    void removePerson(long id);

    /**
     * Edits person by [id], according to low of accountancy the old data must be stored, therefore the person gets hidden
     * also [identificationNumber, taxNumber] cannot be changed, so updated person sets the values from the previous ones
     *
     * @param id   Person to edit
     * @param data Updated data of person to edit
     * @return Edited Person
     */
    Person editPerson(Long id, PersonDTO data);

    /**
     * Fetches all invoices by [identificationNumber] of the seller
     *
     * @param identificationNumber The seller
     * @return List of the Invoice
     */
    List<Invoice> getInvoicesBySeller(String identificationNumber);

    /**
     * Fetches all invoices by [identificationNumber] of the buyer
     *
     * @param identificationNumber The buyer
     * @return List of the Invoice
     */
    List<Invoice> getInvoicesByBuyer(String identificationNumber);

    /**
     * Fetches all values of PersonStatisticsDTO [Long personId, String personName, Long revenue]
     *
     * @return List of statistics of each person
     */
    List<PersonStatisticsDTO> getPersonStatistics();

}
