package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long>, JpaSpecificationExecutor<InvoiceEntity> {



    // region: JQuery methods to get values of InvoiceStatisticsDTO [Long currentYearSum, Long allTimeSum, Long invoicesCount]

    @Query(value = "SELECT SUM(price) FROM invoice", nativeQuery = true)
    Long getSumPriceAllTime();

    @Query(value = "SELECT COUNT(*) FROM invoice", nativeQuery = true)
    Long getCountInvoices();

    @Query(value = "SELECT SUM(price) FROM invoice WHERE YEAR(due_date) = YEAR(CURDATE())", nativeQuery = true)
    Long getSumPriceCurrentYear();

    @Query(value = """
            SELECT
                IFNULL((SELECT SUM(IFNULL(price,0)) FROM invoice), 0) AS allTimeSum,
                IFNULL((SELECT COUNT(*) FROM invoice), 0) AS invoicesCount,
                IFNULL((SELECT SUM(IFNULL(price,0)) FROM invoice WHERE YEAR(due_date) = YEAR(CURDATE())), 0) AS currentYearSum
            """, nativeQuery = true)
    Object getStats();
}
