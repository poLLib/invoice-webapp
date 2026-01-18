package cz.pollib.service.model;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * Contains statistics for a person.
 * <p>
 * Attributes:
 * - personId: The unique identifier of the person.
 * - personName: The name of the person.
 * - revenue: The total revenue associated with the person.
 */
@Schema(name = "PersonStatisticsResponse")
public record PersonStatisticsResponse(
        long personId,
        String personName,
        Long revenue
) {
}