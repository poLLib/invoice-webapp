package cz.pollib.entity.repository;

import cz.pollib.service.model.InvoiceStatisticsResponse;
import cz.pollib.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * Repository interface for managing {@link InvoiceEntity} entities.
 */
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {

    /**
     * Retrieves statistics for invoices.
     *
     * @return An array containing:
     * - allTimeSum: The total sum of all invoices.
     * - invoicesCount: The total number of invoices.
     * - currentYearSum: The sum of invoices for the current year.
     */
    @Query(value = """
            SELECT NEW cz.pollib.service.model.InvoiceStatisticsResponse(
                        SUM(currentYearSum.price),
                        SUM(allTimeSum.price),
                        COUNT(*)
                        )
            FROM Invoice allTimeSum
                LEFT OUTER JOIN FETCH Invoice currentYearSum ON allTimeSum.id = currentYearSum.id AND YEAR(currentYearSum.issued) = YEAR(CURRENT_DATE)
            """)
    InvoiceStatisticsResponse getStats();
    
    /**
     * Checks if an invoice exists by its invoice number.
     *
     * @param invoiceNumber The invoice number to check.
     * @return True if exists.
     */
    boolean existsByInvoiceNumber(int invoiceNumber);
}
