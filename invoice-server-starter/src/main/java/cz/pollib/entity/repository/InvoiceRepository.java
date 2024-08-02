package cz.pollib.entity.repository;

import cz.pollib.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository interface for managing {@link InvoiceEntity} entities.
 */
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {

    /**
     * Count all invoices
     *
     * @return The count of all invoices
     */
    @Query(value = "SELECT COUNT(*) FROM invoice i", nativeQuery = true)
    Long countInvoices();

    /**
     * Retrieves statistics for invoices.
     *
     * @return An array containing:
     * - allTimeSum: The total sum of all invoices.
     * - invoicesCount: The total number of invoices.
     * - currentYearSum: The sum of invoices for the current year.
     */
    @Query(value = """
            SELECT
                IFNULL((SELECT SUM(IFNULL(price,0)) FROM invoice), 0) AS allTimeSum,
                IFNULL((SELECT COUNT(*) FROM invoice), 0) AS invoicesCount,
                IFNULL((SELECT SUM(IFNULL(price,0)) FROM invoice WHERE YEAR(due_date) = YEAR(CURDATE())), 0) AS currentYearSum
            """, nativeQuery = true)
    Object getStats();
}
