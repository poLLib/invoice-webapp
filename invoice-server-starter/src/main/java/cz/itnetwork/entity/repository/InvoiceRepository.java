package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {



    /*    @Query(value = "SELECT SUM(price) FROM invoice WHERE YEAR(due_date) = :#{#date.getYear()}", nativeQuery = true)
        Long getSumCurrentYear(@Param("date") LocalDate date);*/

    //TODO:
    // 1) mel bych spojit dotazy v jeden? jak provest, aby dotaz dokazal ukladat vypoctene hodnoty do danych atributu statistiky?
    // 2) je v tomto pripade lepsi se dotazovat primo nad databazi, nez dosazovani jpql?
    // 3) podle uml je vypsani statistik samostatna metoda, nepatri k entitam person ani invoice. je spravne, kdyz vyuziju jejich repos? a controller kdyz maji url smerovaci pres stejne adresy?

    // region: JQuery methods to get values of InvoiceStatisticsDTO [Long currentYearSum, Long allTimeSum, Long invoicesCount]

    @Query(value = "SELECT SUM(price) FROM invoice WHERE YEAR(due_date) = YEAR(CURDATE())", nativeQuery = true)
    Long getSumCurrentYear();

    @Query(value = "SELECT SUM(price) FROM invoice", nativeQuery = true)
    Long getSumAllTime();

    @Query(value = "SELECT COUNT(*) FROM invoice", nativeQuery = true)
    Long getCountInvoices();
}
