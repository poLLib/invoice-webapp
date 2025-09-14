package cz.pollib.service.model;

import cz.pollib.constant.Countries;

/**
 * Response for person details
 * <p>
 * Attributes:
 * - id: The unique identifier for the person.
 * - name: The name of the person.
 * - identificationNumber: The identification number of the person
 * - taxNumber: The tax number
 * - accountNumber: The account number
 * - bankCode: The bank code
 * - iban: The IBAN
 * - telephone: The telephone number
 * - mail: The email address
 * - street: The street address
 * - zip: The postal code
 * - city: The city
 * - country: The country
 * - note: Any additional notes
 */
public record PersonResponse(
        long id,
        String name,
        String identificationNumber,
        String taxNumber,
        String accountNumber,
        String bankCode,
        String iban,
        String telephone,
        String mail,
        String street,
        String zip,
        String city,
        Countries country,
        String note
) {
}