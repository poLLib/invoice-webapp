package cz.pollib.dto;

import lombok.Data;

/**
 * Contains statistics for a person.
 * <p>
 * Attributes:
 * - personId: The unique identifier of the person.
 * - personName: The name of the person.
 * - revenue: The total revenue associated with the person.
 */
@Data
public class PersonStatisticsDTO {

    private Long personId;
    private String personName;
    private Long revenue;
}
