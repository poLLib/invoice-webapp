package cz.pollib.service;

import cz.pollib.common.BaseControllerTest;
import cz.pollib.dto.InvoiceDTO;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static io.restassured.RestAssured.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;
import static org.hamcrest.Matchers.*;

class PersonInvoiceControllerTest extends BaseControllerTest {

    @Test
    @DisplayName("Should return invoices of seller")
    void shouldReturnSellersInvoices() {
        InvoiceDTO[] invoices = given()
                .log().all()
                .accept(ContentType.JSON)
                .pathParam("identificationNumber", "987654321")
                .when()
                .get("/identification/{identificationNumber}/sales")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(InvoiceDTO[].class);

        assertThat(invoices).isNotNull().hasSize(1);

        InvoiceDTO invoice = invoices[0];

        assertThat(invoice.getId()).isNotNull();
        assertThat(invoice.getInvoiceNumber()).isEqualTo(123456);
        assertThat(invoice.getIssued()).isEqualTo(LocalDate.of(2019, 10, 1));
        assertThat(invoice.getDueDate()).isEqualTo(LocalDate.of(2020, 1, 1));
        assertThat(invoice.getProduct()).isEqualTo("product");
        assertThat(invoice.getPrice()).isEqualTo(100L);
        assertThat(invoice.getVat()).isEqualTo(21);
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
        given()
                .log().all()
                .accept(ContentType.JSON)
                .pathParam("identificationNumber", "123456789")
                .when()
                .get("/identification/{identificationNumber}/purchases")
                .then()
                .log().all()
                .statusCode(200)
                .body("[0].dueDate", equalTo("2020-01-01"))
                .body("[0].invoiceNumber", equalTo(123456))
                .body("[0].issued", equalTo("2019-10-01"))
                .body("[0].price", equalTo(100))
                .body("[0].product", equalTo("product"))
                .body("[0].vat", equalTo(21));
    }
}
