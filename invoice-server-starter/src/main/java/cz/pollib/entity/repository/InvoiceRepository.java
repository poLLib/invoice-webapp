package cz.pollib.entity.repository;

import cz.pollib.entity.InvoiceEntity;
import cz.pollib.service.model.InvoiceStatisticsResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Repository interface for managing {@link InvoiceEntity} entities.
 */
public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {

    /**
     * Retrieves a sum of price for invoices at all time.
     *
     * @return The total sum of all invoices.
     */
    @Query("""
    SELECT new cz.pollib.service.model.InvoiceStatisticsResponse(
        SUM(CASE WHEN YEAR(i.issued) = YEAR(CURRENT_DATE) THEN i.price ELSE 0 END),
        SUM(i.price),
        COUNT(i.id)
    )
    FROM Invoice i
""")
    InvoiceStatisticsResponse getStatistics();


    /**
     * Checks if an invoice exists by its invoice number.
     *
     * @param invoiceNumber The invoice number to check.
     * @return True if exists.
     */
    boolean existsByInvoiceNumber(int invoiceNumber);

    /**
     * Retrieves a list of invoices of the seller.
     *
     * @param identificationNumber The identification number of the seller.
     * @return invoices of the seller.
     */
    List<InvoiceEntity> findBySellerIdentificationNumber(String identificationNumber);

    /**
     * Retrieves a list of invoices of the buyer.
     *
     * @param identificationNumber The identification number of the buyer.
     * @return invoices of the buyer.
     */
    List<InvoiceEntity> findByBuyerIdentificationNumber(String identificationNumber);
}
