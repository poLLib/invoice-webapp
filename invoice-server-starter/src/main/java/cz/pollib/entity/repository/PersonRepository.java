package cz.pollib.entity.repository;

import cz.pollib.entity.PersonEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for managing {@link PersonEntity} entities.
 */
public interface PersonRepository extends JpaRepository<PersonEntity, Long> {


    /**
     * Finds hidden entities and returns them in a paginated format.
     *
     * @param hidden   Indicates whether the entities are hidden.
     * @param pageable Pagination information.
     * @return A list of hidden {@link PersonEntity} entities.
     */
    List<PersonEntity> findByHidden(boolean hidden, Pageable pageable);

    /**
     * Finds hidden entities without pagination.
     *
     * @param hidden Indicates whether the entities are hidden.
     * @return A list of hidden {@link PersonEntity} entities.
     */
    List<PersonEntity> findByHidden(boolean hidden);

    /**
     * Counts all non-hidden people.
     *
     * @return The total number of non-hidden people.
     */
    @Query(value = "SELECT COUNT(*) FROM person p WHERE p.hidden = false", nativeQuery = true)
    Long countAllVisiblePeople();

    /**
     * Calculates the total income for a person by their ID.
     *
     * @param id The ID of the person.
     * @return The total income for the person.
     */
    @Query(value = "SELECT SUM(i.price) FROM invoice i JOIN person p ON i.seller_id = p.id WHERE p.id = :id", nativeQuery = true)
    Long getTotalIncome(@Param("id") Long id);

    /**
     * Finds a person by their identification number.
     *
     * @param identificationNumber The identification number of the person.
     * @return The {@link PersonEntity} with the given identification number.
     */
    PersonEntity findByIdentificationNumber(String identificationNumber);
}
