package cz.pollib.service.model;

/**
 * Contains statistics for a person.
 * <p>
 * Attributes:
 * - personId: The unique identifier of the person.
 * - personName: The name of the person.
 * - revenue: The total revenue associated with the person.
 */
public record PersonStatisticsResponse(
        long personId,
        String personName,
        Long revenue
) {
}