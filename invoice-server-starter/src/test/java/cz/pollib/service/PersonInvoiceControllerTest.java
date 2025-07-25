package cz.pollib.service;

import cz.pollib.common.BaseControllerTest;
import cz.pollib.service.model.InvoiceResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

public class PersonInvoiceControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("Should return invoices of seller")
    void shouldReturnSellersInvoices() {
        InvoiceResponse[] results = given()
                .log().all()
                .accept(ContentType.JSON)
                .pathParam("identificationNumber", "987654321")
                .when()
                .get("/identification/{identificationNumber}/sales")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(InvoiceResponse[].class);

        assertThat(results).isNotNull().hasSize(1);

        InvoiceResponse invoice = results[0];

        assertThat(invoice.getId()).isNotNull();
        assertThat(invoice.getInvoiceNumber()).isEqualTo(123456);
        assertThat(invoice.getIssued()).isEqualTo(LocalDate.of(2019, 10, 1));
        assertThat(invoice.getDueDate()).isEqualTo(LocalDate.of(2020, 1, 1));
        assertThat(invoice.getProduct()).isEqualTo("product");
        assertThat(invoice.getPrice()).isEqualTo(100L);
        assertThat(invoice.getVat()).isEqualTo((byte) 21);
        assertThat(invoice.getNote()).isNull();

        assertThat(invoice.getSeller()).isNotNull();
        assertThat(invoice.getSeller().getIdentificationNumber()).isEqualTo("987654321");
        assertThat(invoice.getSeller().getName()).isEqualTo("pompo");

        assertThat(invoice.getBuyer()).isNotNull();
        assertThat(invoice.getBuyer().getIdentificationNumber()).isEqualTo("123456789");
        assertThat(invoice.getBuyer().getName()).isEqualTo("arabela");
    }

    @Test
    @DisplayName("Should return invoices of buyer")
    void shouldReturnBuyersInvoices() {
        InvoiceResponse[] results = given()
                .log().all()
                .accept(ContentType.JSON)
                .pathParam("identificationNumber", "123456789")
                .when()
                .get("/identification/{identificationNumber}/purchases")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(InvoiceResponse[].class);

        assertThat(results).isNotNull().hasSize(1);

        InvoiceResponse invoice = results[0];

        assertThat(invoice.getId()).isNotNull();
        assertThat(invoice.getInvoiceNumber()).isEqualTo(123456);
        assertThat(invoice.getIssued()).isEqualTo(LocalDate.of(2019, 10, 1));
        assertThat(invoice.getDueDate()).isEqualTo(LocalDate.of(2020, 1, 1));
        assertThat(invoice.getProduct()).isEqualTo("product");
        assertThat(invoice.getPrice()).isEqualTo(100L);
        assertThat(invoice.getVat()).isEqualTo((byte) 21);
        assertThat(invoice.getNote()).isNull();

        assertThat(invoice.getSeller()).isNotNull();
        assertThat(invoice.getSeller().getIdentificationNumber()).isEqualTo("987654321");
        assertThat(invoice.getSeller().getName()).isEqualTo("pompo");

        assertThat(invoice.getBuyer()).isNotNull();
        assertThat(invoice.getBuyer().getIdentificationNumber()).isEqualTo("123456789");
        assertThat(invoice.getBuyer().getName()).isEqualTo("arabela");
    }
}
