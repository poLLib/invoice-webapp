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
     * Retrieves a sum of price for invoices at all time.
     *
     * @return The total sum of all invoices.
     */
    @Query(value = """
            SELECT SUM(i.price)
            FROM Invoice i
            """)
    Long sumAllPrice();

    /**
     * Retrieves a count of price for invoices at all time.
     *
     * @return The total count of all invoices.
     */
    @Query(value = """
            SELECT COUNT(i.id)
            FROM Invoice i
            """)
    Long countAll();

    /**
     * Retrieves a sum of price of invoices for the current year.
     *
     * @return The total sum of the invoices
     */
    @Query(value = """
            SELECT SUM(i.price)
            FROM Invoice i
                WHERE YEAR(i.issued) = YEAR(CURRENT_DATE)
            """)
    Long sumPriceOfCurrentYear();

    /**
     * Checks if an invoice exists by its invoice number.
     *
     * @param invoiceNumber The invoice number to check.
     * @return True if exists.
     */
    boolean existsByInvoiceNumber(int invoiceNumber);
}
