package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

    // region: JQuery methods to get values of InvoiceStatisticsDTO [Long currentYearSum, Long allTimeSum, Long invoicesCount]

    @Query(value = "SELECT SUM(price) FROM invoice", nativeQuery = true)
    Long getSumPriceAllTime();

    @Query(value = "SELECT COUNT(*) FROM invoice", nativeQuery = true)
    Long getCountInvoices();

    @Query(value = "SELECT SUM(price) FROM invoice WHERE YEAR(due_date) = YEAR(CURDATE())", nativeQuery = true)
    Long getSumPriceCurrentYear();
}
