package cz.itnetwork.service;

import cz.itnetwork.dto.InvoiceDTO;
import cz.itnetwork.dto.PersonDTO;
import cz.itnetwork.dto.PersonStatisticsDTO;

import java.util.List;

public interface PersonService {

    /**
     * Creates a new person
     *
     * @param personDTO Person to create
     * @return Newly created PersonDTO
     */
    PersonDTO addPerson(PersonDTO personDTO);

    /**
     * <p>Sets hidden flag to true for the person with the matching [id]</p>
     * <p>In case a person with the passed [id] isn't found, the method <b>silently fails</b></p>
     *
     * @param id Person to delete
     */
    void removePerson(long id);

    /**
     * Fetches all non-hidden persons
     *
     * @return List of PersonDTO
     */
    List<PersonDTO> getAll();


    /**
     * Fetches the specific person by [id]
     * In case a person with the passed [id] isn't found, the method silently fails
     *
     * @param id Person to look up
     * @return Found PersonDTO
     */
    PersonDTO getPerson(Long id);

    /**
     * Edits person by [id]
     *
     * @param id
     * @param data Person to edit
     * @return Edited PersonDTO
     */
    PersonDTO editPerson(Long id, PersonDTO data);

    /**
     * Fetches all invoices by [identificationNumber] of the seller
     *
     * @param identificationNumber The seller
     * @return List of the InvoiceDTO
     */
    List<InvoiceDTO>getInvoicesBySeller(String identificationNumber);

    /**
     * Fetches all invoices by [identificationNumber] of the buyer
     *
     * @param identificationNumber The buyer
     * @return List of the InvoiceDTO
     */
    List<InvoiceDTO>getInvoicesByBuyer(String identificationNumber);

    /**
     * Fetches all values of PersonStatisticsDTO [Long personId, String personName, Long revenue]
     *
     * @return List of statistics of each person
     */
    List<PersonStatisticsDTO> getPersonStatistics();
}
