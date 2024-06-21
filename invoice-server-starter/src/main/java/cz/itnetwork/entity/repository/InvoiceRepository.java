package cz.itnetwork.entity.repository;

import cz.itnetwork.entity.InvoiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface InvoiceRepository extends JpaRepository<InvoiceEntity, Long> {
    @Query(value = "SELECT SUM(price) FROM invoice WHERE YEAR(due_date) = :#{#date.getYear()}", nativeQuery = true)
    Long sumCurrentYear(@Param("date") LocalDate date);
    //TODO: mel bych pripojit vice dotazu i na ostatni filtry?1} napriklad pouziti COUNT pro spocitani kolik bylo faktur jaky rok. k SUM castky vyuzit java expression nebo se dotazat pouze na price a pote secist?
}
