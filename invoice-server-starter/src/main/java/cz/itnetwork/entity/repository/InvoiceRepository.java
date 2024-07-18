package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {

    /**
     * Count all invoices
     *
     * @return The count of all invoices
     */
    @Query(value = "SELECT COUNT(*) FROM invoice i", nativeQuery = true)
    Long countInvoices();

    /**
     * JQuery to get values for InvoiceStatisticsDTO [BigDecimal currentYearSum, BigDecimal allTimeSum, Long invoicesCount]
     *
     * @return Object contains array of three indexes
     */
    @Query(value = """
            SELECT
                IFNULL((SELECT SUM(IFNULL(price,0)) FROM invoice), 0) AS allTimeSum,
                IFNULL((SELECT COUNT(*) FROM invoice), 0) AS invoicesCount,
                IFNULL((SELECT SUM(IFNULL(price,0)) FROM invoice WHERE YEAR(due_date) = YEAR(CURDATE())), 0) AS currentYearSum
            """, nativeQuery = true)
    Object getStats();
}

/**
 * custom JPQL query and constructor within
 */
/*@Query("SELECT new cz.itnetwork.dto.InvoiceStatisticsDTO(" +
        "COALESCE(SUM(i.price), 0), " +                                // Total sum of all invoices
        "COUNT(i), " +                                                 // Count of all invoices
        "COALESCE(SUM(CASE WHEN FUNCTION('YEAR', i.dueDate) = FUNCTION('YEAR', CURRENT_DATE) THEN i.price ELSE 0 END), 0)) " + // Sum for the current year
        "FROM invoice i")
InvoiceStatisticsDTO getStats();*/
