package cz.pollib.service;

import cz.pollib.common.BaseControllerTest;
import cz.pollib.service.model.CreateInvoiceRequest;
import cz.pollib.service.model.CreatePersonRequest;
import cz.pollib.service.model.InvoiceResponse;
import cz.pollib.service.model.InvoiceStatisticsResponse;
import cz.pollib.service.model.PersonResponse;
import cz.pollib.service.model.UpdateInvoiceRequest;
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
                .log()
                .all()
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
                .log()
                .all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("validationjsonschema/person-response.json"))
                .statusCode(201)
                .extract()
                .as(PersonResponse.class)
                .id();

        buyerId = given()
                .log()
                .all()
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
                .log()
                .all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("validationjsonschema/person-response.json"))
                .statusCode(201)
                .extract()
                .as(PersonResponse.class)
                .id();
    }

    @Order(2)
    @Test
    @DisplayName("Should create invoice")
    void shouldCreateInvoice() {
        InvoiceResponse result = given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(new CreateInvoiceRequest(
                              666555444,
                              LocalDate.parse("2020-01-01"),
                              LocalDate.parse("2020-03-01"),
                              "product",
                              23000L,
                              21,
                              null,
                              buyerId,
                              sellerId
                      )
                     )
                .when()
                .post("/invoice")
                .then()
                .log()
                .all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("validationjsonschema/invoice-response.json"))
                .statusCode(201)
                .extract()
                .as(InvoiceResponse.class);

        assertThat(result.id()).isNotNull();
        assertThat(result.invoiceNumber()).isEqualTo(666555444);
        assertThat(result.issued()).isEqualTo(LocalDate.parse("2020-01-01"));
        assertThat(result.dueDate()).isEqualTo(LocalDate.parse("2020-03-01"));
        assertThat(result.product()).isEqualTo("product");
        assertThat(result.price()).isEqualTo(23000L);
        assertThat(result.vat()).isEqualTo(21);
        assertThat(result.note()).isNull();

        assertThat(result.seller()).isNotNull();
        assertThat(result.seller()
                         .id()).isEqualTo(sellerId);
        assertThat(result.seller()
                         .identificationNumber()).isEqualTo("11122223");

        assertThat(result.buyer()).isNotNull();
        assertThat(result.buyer()
                         .id()).isEqualTo(buyerId);
        assertThat(result.buyer()
                         .identificationNumber()).isEqualTo("33322221");

        invoiceId = result.id();
    }

    @Order(3)
    @Test
    @DisplayName("Should return invoice")
    void shouldReturnInvoice() {
        InvoiceResponse result = given()
                .log()
                .all()
                .accept(ContentType.JSON)
                .pathParam(
                        "invoiceId",
                        invoiceId
                          )
                .when()
                .get("/invoice/{invoiceId}")
                .then()
                .log()
                .all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("validationjsonschema/invoice-response.json"))
                .statusCode(200)
                .extract()
                .as(InvoiceResponse.class);

        assertThat(result.id()).isNotNull();
        assertThat(result.invoiceNumber()).isEqualTo(666555444);
        assertThat(result.issued()).isEqualTo(LocalDate.parse("2020-01-01"));
        assertThat(result.dueDate()).isEqualTo(LocalDate.parse("2020-03-01"));
        assertThat(result.product()).isEqualTo("product");
        assertThat(result.price()).isEqualTo(23000L);
        assertThat(result.vat()).isEqualTo(Byte.parseByte("21"));
        assertThat(result.note()).isNull();

        assertThat(result.seller()).isNotNull();
        assertThat(result.seller()
                         .id()).isEqualTo(sellerId);
        assertThat(result.seller()
                         .identificationNumber()).isEqualTo("11122223");

        assertThat(result.buyer()).isNotNull();
        assertThat(result.buyer()
                         .id()).isEqualTo(buyerId);
        assertThat(result.buyer()
                         .identificationNumber()).isEqualTo("33322221");
    }

    @Order(4)
    @Test
    @DisplayName("Should update invoice")
    void shouldUpdateInvoice() {
        InvoiceResponse result = given()
                .log()
                .all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(new UpdateInvoiceRequest(
                              LocalDate.parse("2021-01-01"),
                              LocalDate.parse("2021-01-01"),
                              "item",
                              100L,
                              16,
                              "note",
                              sellerId,
                              buyerId
                      )
                     )
                .pathParam(
                        "invoiceId",
                        invoiceId
                          )
                .when()
                .put("/invoice/{invoiceId}")
                .then()
                .log()
                .all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("validationjsonschema/invoice-response.json"))
                .statusCode(200)
                .extract()
                .as(InvoiceResponse.class);

        assertThat(result.id()).isNotNull();
        assertThat(result.invoiceNumber()).isEqualTo(666555444);
        assertThat(result.issued()).isEqualTo(LocalDate.parse("2021-01-01"));
        assertThat(result.dueDate()).isEqualTo(LocalDate.parse("2021-01-01"));
        assertThat(result.product()).isEqualTo("item");
        assertThat(result.price()).isEqualTo(100L);
        assertThat(result.vat()).isEqualTo(16);
        assertThat(result.note()).isEqualTo("note");

        assertThat(result.buyer()).isNotNull();
        assertThat(result.buyer()
                         .id()).isEqualTo(sellerId);
        assertThat(result.buyer()
                         .identificationNumber()).isEqualTo("11122223");

        assertThat(result.seller()).isNotNull();
        assertThat(result.seller()
                         .id()).isEqualTo(buyerId);
        assertThat(result.seller()
                         .identificationNumber()).isEqualTo("33322221");
    }

    @Order(5)
    @Test
    @DisplayName("Should return invoices")
    void shouldReturnInvoices() {
        InvoiceResponse[] results = given()
                .log()
                .all()
                .accept(ContentType.JSON)
                .param(
                        "page",
                        "0"
                      )
                .param(
                        "size",
                        "10"
                      )
                .when()
                .get("/invoices")
                .then()
                .log()
                .all()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getObject(
                        "invoices",
                        InvoiceResponse[].class
                          );

        assertThat(results).isNotEmpty();

        assertThat(results)
                .extracting(InvoiceResponse::id)
                .contains(invoiceId);
    }

    @Order(6)
    @Test
    @DisplayName("Should delete invoice")
    void shouldDeleteInvoice() {
        given()
                .log()
                .all()
                .pathParam(
                        "invoiceId",
                        invoiceId
                          )
                .when()
                .delete("/invoice/{invoiceId}")
                .then()
                .log()
                .all()
                .statusCode(204);
    }

    @Order(7)
    @Test
    @DisplayName("Should not return deleted invoice")
    void shouldNotReturnDeletedInvoice() {
        given()
                .log()
                .all()
                .accept(ContentType.JSON)
                .pathParam(
                        "invoiceId",
                        invoiceId
                          )
                .when()
                .get("/invoice/{invoiceId}")
                .then()
                .log()
                .all()
                .statusCode(404);
    }

    @Order(8)
    @Test
    @DisplayName("Should return invoice statistics")
    void shouldReturnInvoiceStatistics() {
        given()
                .log()
                .all()
                .accept(ContentType.JSON)
                .when()
                .get("/invoices/statistics")
                .then()
                .log()
                .all()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("validationjsonschema/invoice-statistics-response.json"))
                .statusCode(200)
                .extract()
                .as(InvoiceStatisticsResponse.class);
    }
}
