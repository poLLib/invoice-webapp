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
