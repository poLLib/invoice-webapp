package cz.pollib.entity.repository;

import cz.pollib.entity.PersonEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PersonRepository extends JpaRepository<PersonEntity, Long> {


    /**
     * Look up for hidden entities and make them Pageable
     *
     * @param hidden   Entity which hidden in UI, but it is kept in database according to the law of accountancy
     * @param pageable Defines pages of the List
     * @return List of pages of PersonEntities
     */
    List<PersonEntity> findByHidden(boolean hidden, Pageable pageable);

    /**
     * Look up for hidden entities without pagination
     *
     * @param hidden Entity which is hidden in UI, but it is kept in the database according to the law of accountancy
     * @return List of PersonEntities
     */
    List<PersonEntity> findByHidden(boolean hidden);

    /**
     * Count all visible entities
     *
     * @return The count of all visible people
     */
    @Query(value = "SELECT COUNT(*) FROM person p WHERE p.hidden = false", nativeQuery = true)
    Long countAllVisiblePeople();

    /**
     * Method to sum the income of all time for a person by their id
     *
     * @param id The id of the person
     * @return The total income of the person
     */
    @Query(value = "SELECT SUM(i.price) FROM invoice i JOIN person p ON i.seller_id = p.id WHERE p.id = :id", nativeQuery = true)
    Long getTotalIncome(@Param("id") Long id);

    /**
     * Find a person by their identification number
     *
     * @param identificationNumber The identification number of the person
     * @return The PersonEntity matching the given identification number
     */
    PersonEntity findByIdentificationNumber(String identificationNumber);
}
