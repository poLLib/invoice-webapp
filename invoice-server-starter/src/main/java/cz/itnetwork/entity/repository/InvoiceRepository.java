package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {

/*    @Query(value = "SELECT SUM(price) FROM invoice WHERE YEAR(due_date) = :#{#date.getYear()}", nativeQuery = true)
    Long getSumCurrentYear(@Param("date") LocalDate date);*/

    @Query(value = "SELECT SUM(price) FROM invoice WHERE YEAR(due_date) = YEAR(CURDATE())", nativeQuery = true)
    Long getSumCurrentYear();

    @Query(value = "SELECT SUM(price) FROM invoice", nativeQuery = true)
    Long getSumAllTime();

    @Query(value = "SELECT COUNT(*) FROM invoice", nativeQuery = true)
    Long getCountInvoices();


//TODO:
// 1) mel bych spojit dotazy v jeden?
// 2) je v tomto pripade lepsi se dotazovat primo nad databazi, nez dosazovani jpql?
}
