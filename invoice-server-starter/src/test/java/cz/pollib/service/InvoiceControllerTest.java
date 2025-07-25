package cz.pollib.service;

import cz.pollib.common.BaseControllerTest;
import cz.pollib.service.model.InvoiceRequest;
import cz.pollib.service.model.InvoiceResponse;
import cz.pollib.service.model.CreatePersonRequest;
import cz.pollib.service.model.InvoiceStatisticsResponse;
import cz.pollib.service.model.PersonResponse;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import java.time.LocalDate;

import static cz.pollib.constant.Countries.*;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InvoiceControllerTest extends BaseControllerTest {

    private Long invoiceId;
    private Long sellerId;
    private Long buyerId;

    @Order(1)
    @Test
    @DisplayName("Should create seller and buyer for the invoice")
    void shouldCreatePersons() {
        sellerId = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(new CreatePersonRequest(
                                "Seller",
                                "11122223",
                                "CZ65666",
                                "65455654",
                                "5455",
                                "CZ564896",
                                "+420705489657",
                                "sell@id.org",
                                "Studena",
                                "10000",
                                "Praha",
                                CZECHIA,
                                null
                        )
                )
                .when()
                .post("/person")
                .then()
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("validationjsonschema/person-response.json"))
                .statusCode(201)
                .extract()
                .as(PersonResponse.class)
                .getId();

        buyerId = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(new CreatePersonRequest(
                                "Buyer",
                                "33322221",
                                "CZ65666",
                                "65455654",
                                "5455",
                                "CZ564896",
                                "+420705489657",
                                "sell@id.org",
                                "Studena",
                                "10000",
                                "Praha",
                                CZECHIA,
                                null
                        )
                )
                .when()
                .post("/person")
                .then()
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("validationjsonschema/person-response.json"))
                .statusCode(201)
                .extract()
                .as(PersonResponse.class)
                .getId();
    }

    @Order(2)
    @Test
    @DisplayName("Should create invoice")
    void shouldCreateInvoice() {
        InvoiceResponse result = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(new InvoiceRequest(
                                666555444,
                                LocalDate.parse("2020-01-01"),
                                LocalDate.parse("2020-03-01"),
                                "product",
                                23000L,
                                (byte) 21,
                                null,
                                buyerId,
                                sellerId
                        )
                )
                .when()
                .post("/invoice")
                .then()
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("validationjsonschema/invoice-response.json"))
                .statusCode(201)
                .extract()
                .as(InvoiceResponse.class);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getInvoiceNumber()).isEqualTo(666555444);
        assertThat(result.getIssued()).isEqualTo(LocalDate.parse("2020-01-01"));
        assertThat(result.getDueDate()).isEqualTo(LocalDate.parse("2020-03-01"));
        assertThat(result.getProduct()).isEqualTo("product");
        assertThat(result.getPrice()).isEqualTo(23000L);
        assertThat(result.getVat()).isEqualTo((byte) 21);
        assertThat(result.getNote()).isNull();

        assertThat(result.getSeller()).isNotNull();
        assertThat(result.getSeller().getId()).isEqualTo(sellerId);
        assertThat(result.getSeller().getIdentificationNumber()).isEqualTo("11122223");

        assertThat(result.getBuyer()).isNotNull();
        assertThat(result.getBuyer().getId()).isEqualTo(buyerId);
        assertThat(result.getBuyer().getIdentificationNumber()).isEqualTo("33322221");

        invoiceId = result.getId();
    }

    @Order(3)
    @Test
    @DisplayName("Should return invoice")
    void shouldReturnInvoice() {
        InvoiceResponse result = given()
                .log().all()
                .accept(ContentType.JSON)
                .pathParam("invoiceId", invoiceId)
                .when()
                .get("/invoice/{invoiceId}")
                .then()
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("validationjsonschema/invoice-response.json"))
                .statusCode(200)
                .extract()
                .as(InvoiceResponse.class);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getInvoiceNumber()).isEqualTo(666555444);
        assertThat(result.getIssued()).isEqualTo(LocalDate.parse("2020-01-01"));
        assertThat(result.getDueDate()).isEqualTo(LocalDate.parse("2020-03-01"));
        assertThat(result.getProduct()).isEqualTo("product");
        assertThat(result.getPrice()).isEqualTo(23000L);
        assertThat(result.getVat()).isEqualTo(Byte.parseByte("21"));
        assertThat(result.getNote()).isNull();

        assertThat(result.getSeller()).isNotNull();
        assertThat(result.getSeller().getId()).isEqualTo(sellerId);
        assertThat(result.getSeller().getIdentificationNumber()).isEqualTo("11122223");

        assertThat(result.getBuyer()).isNotNull();
        assertThat(result.getBuyer().getId()).isEqualTo(buyerId);
        assertThat(result.getBuyer().getIdentificationNumber()).isEqualTo("33322221");
    }

    @Order(4)
    @Test
    @DisplayName("Should update invoice")
    void shouldUpdateInvoice() {
        InvoiceResponse result = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(new InvoiceRequest(
                                68453215,
                                LocalDate.parse("2021-01-01"),
                                LocalDate.parse("2021-01-01"),
                                "item",
                                100L,
                                (byte) 16,
                                "note",
                                sellerId,
                                buyerId
                        )
                )
                .pathParam("invoiceId", invoiceId)
                .when()
                .put("/invoice/{invoiceId}")
                .then()
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("validationjsonschema/invoice-response.json"))
                .statusCode(200)
                .extract()
                .as(InvoiceResponse.class);

        assertThat(result.getId()).isNotNull();
        assertThat(result.getInvoiceNumber()).isEqualTo(68453215);
        assertThat(result.getIssued()).isEqualTo(LocalDate.parse("2021-01-01"));
        assertThat(result.getDueDate()).isEqualTo(LocalDate.parse("2021-01-01"));
        assertThat(result.getProduct()).isEqualTo("item");
        assertThat(result.getPrice()).isEqualTo(100L);
        assertThat(result.getVat()).isEqualTo((byte) 16);
        assertThat(result.getNote()).isEqualTo("note");

        assertThat(result.getBuyer()).isNotNull();
        assertThat(result.getBuyer().getId()).isEqualTo(sellerId);
        assertThat(result.getBuyer().getIdentificationNumber()).isEqualTo("11122223");

        assertThat(result.getSeller()).isNotNull();
        assertThat(result.getSeller().getId()).isEqualTo(buyerId);
        assertThat(result.getSeller().getIdentificationNumber()).isEqualTo("33322221");
    }

    @Order(5)
    @Test
    @DisplayName("Should return invoices")
    void shouldReturnInvoices() {
        InvoiceResponse[] results = given()
                .log().all()
                .accept(ContentType.JSON)
                .param("page", "0")
                .param("size", "10")
                .when()
                .get("/invoices")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getObject("invoices", InvoiceResponse[].class);

        assertThat(results).isNotEmpty();

        assertThat(results)
                .extracting(InvoiceResponse::getId)
                .contains(invoiceId);
    }

    @Order(6)
    @Test
    @DisplayName("Should delete invoice")
    void shouldDeleteInvoice() {
        given()
                .log().all()
                .pathParam("invoiceId", invoiceId)
                .when()
                .delete("/invoice/{invoiceId}")
                .then()
                .log().all()
                .statusCode(204);
    }

    @Order(7)
    @Test
    @DisplayName("Should not return deleted invoice")
    void shouldNotReturnDeletedInvoice() {
        given()
                .log().all()
                .accept(ContentType.JSON)
                .pathParam("invoiceId", invoiceId)
                .when()
                .get("/invoice/{invoiceId}")
                .then()
                .log().all()
                .statusCode(404);
    }

    @Order(8)
    @Test
    @DisplayName("Should return invoice statistics")
    void shouldReturnInvoiceStatistics() {
        given()
                .log().all()
                .accept(ContentType.JSON)
                .when()
                .get("/invoices/statistics")
                .then()
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("validationjsonschema/invoice-statistics-response.json"))
                .statusCode(200)
                .extract()
                .as(InvoiceStatisticsResponse.class);
    }
}
