package cz.pollib.service;

import cz.pollib.common.BaseControllerTest;
import cz.pollib.dto.InvoiceDTO;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import static cz.pollib.constant.Countries.CZECHIA;
import static cz.pollib.constant.Countries.SLOVAKIA;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class InvoiceControllerTest extends BaseControllerTest {
//
//    private Long invoiceId;
//
//    @Order(1)
//    @Test
//    @DisplayName("Should create a invoice")
//    void shouldCreatePerson() {
//        InvoiceDTO result = given()
//                .log().all()
//                .contentType(ContentType.JSON)
//                .accept(ContentType.JSON)
//                .body(new InvoiceDTO(
//                        null,
//                        "666555444",
//                        "2020-01-01",
//                        "2020-03-01",
//                        "product",
//                        "23000",
//                        "21",
//                        null,
//                        buyer,
//                        seller,
//                ))
//                .when()
//                .post("/invoice")
//                .then()
//                .log().all()
//                .assertThat().body(matchesJsonSchemaInClasspath("validationjsonschema/invoice-schema.json"))
//                .statusCode(201)
//                .extract()
//                .as(InvoiceDTO.class);
//
//        assertThat(result).isNotNull();
//        assertThat(result.getId()).isNotNull().isPositive();
//        assertThat(result.ge()).isEqualTo("John Doe");
//        assertThat(result.getIdentificationNumber()).isEqualTo("11111111");
//        assertThat(result.getTaxNumber()).isEqualTo("CZ65487");
//        assertThat(result.getAccountNumber()).isEqualTo("654654654");
//        assertThat(result.getBankCode()).isEqualTo("5464");
//        assertThat(result.getIban()).isEqualTo("CZ564896");
//        assertThat(result.getTelephone()).isEqualTo("+420705489657");
//        assertThat(result.getMail()).isEqualTo("john@doe.org");
//        assertThat(result.getStreet()).isEqualTo("Studena");
//        assertThat(result.getZip()).isEqualTo("10000");
//        assertThat(result.getCity()).isEqualTo("Praha");
//        assertThat(result.getCountry()).isEqualTo(CZECHIA);
//        assertThat(result.getNote()).isNull();
//
//        invoiceId = result.getId();
//    }
//
//    @Order(2)
//    @Test
//    @DisplayName("Should return invoice")
//    void shouldReturnPerson() {
//        InvoiceDTO result = given()
//                .log().all()
//                .accept(ContentType.JSON)
//                .pathParam("invoiceId", invoiceId)
//                .when()
//                .get("/invoice/{invoiceId}")
//                .then()
//                .log().all()
//                .assertThat().body(matchesJsonSchemaInClasspath("validationjsonschema/invoice-schema.json"))
//                .statusCode(200)
//                .extract()
//                .as(InvoiceDTO.class);
//
//        assertThat(result).isNotNull();
//        assertThat(result.getId()).isNotNull().isPositive();
//        assertThat(result.getName()).isEqualTo("John Doe");
//        assertThat(result.getIdentificationNumber()).isEqualTo("11111111");
//        assertThat(result.getTaxNumber()).isEqualTo("CZ65487");
//        assertThat(result.getAccountNumber()).isEqualTo("654654654");
//        assertThat(result.getBankCode()).isEqualTo("5464");
//        assertThat(result.getIban()).isEqualTo("CZ564896");
//        assertThat(result.getTelephone()).isEqualTo("+420705489657");
//        assertThat(result.getMail()).isEqualTo("john@doe.org");
//        assertThat(result.getStreet()).isEqualTo("Studena");
//        assertThat(result.getZip()).isEqualTo("10000");
//        assertThat(result.getCity()).isEqualTo("Praha");
//        assertThat(result.getCountry()).isEqualTo(CZECHIA);
//        assertThat(result.getNote()).isNull();
//    }
//
//    @Order(3)
//    @Test
//    @DisplayName("Should edit invoice")
//    void shouldEditPerson() {
//        InvoiceDTO result = given()
//                .log().all()
//                .contentType(ContentType.JSON)
//                .accept(ContentType.JSON)
//                .body(new InvoiceDTO(
//                        null,
//                        "Rumburak",
//                        null,
//                        null,
//                        "98745385",
//                        "6454",
//                        "SK98745385",
//                        "+421708489657",
//                        "rumbu@rak.sk",
//                        "Zlova",
//                        "20065",
//                        "Blava",
//                        SLOVAKIA,
//                        "neviditelny"
//                ))
//                .pathParam("invoiceId", invoiceId)
//                .when()
//                .put("/invoice/{invoiceId}")
//                .then()
//                .log().all()
//                .assertThat().body(matchesJsonSchemaInClasspath("validationjsonschema/invoice-schema.json"))
//                .statusCode(200)
//                .extract()
//                .as(InvoiceDTO.class);
//
//        assertThat(result).isNotNull();
//        assertThat(result.getId()).isEqualTo(invoiceId);
//        assertThat(result.getName()).isEqualTo("Rumburak");
//
//        assertThat(result.getIdentificationNumber()).isEqualTo("11111111");
//        assertThat(result.getTaxNumber()).isEqualTo("CZ65487");
//
//        assertThat(result.getAccountNumber()).isEqualTo("98745385");
//        assertThat(result.getBankCode()).isEqualTo("6454");
//        assertThat(result.getIban()).isEqualTo("SK98745385");
//        assertThat(result.getTelephone()).isEqualTo("+421708489657");
//        assertThat(result.getMail()).isEqualTo("rumbu@rak.sk");
//        assertThat(result.getStreet()).isEqualTo("Zlova");
//        assertThat(result.getZip()).isEqualTo("20065");
//        assertThat(result.getCity()).isEqualTo("Blava");
//        assertThat(result.getCountry()).isEqualTo(SLOVAKIA);
//        assertThat(result.getNote()).isEqualTo("neviditelny");
//    }
//
//    @Order(4)
//    @Test
//    @DisplayName("Should return persons")
//    void shouldReturnPersons() {
//        InvoiceDTO[] results = given()
//                .log().all()
//                .accept(ContentType.JSON)
//                .param("page", "0")
//                .param("size", "10")
//                .when()
//                .get("/persons")
//                .then()
//                .log().all()
//                .statusCode(200)
//                .extract()
//                .as(InvoiceDTO[].class);
//
//        assertThat(results).isNotEmpty();
//
//        assertThat(results)
//                .extracting(InvoiceDTO::getId)
//                .contains(invoiceId);
//    }
//
//    @Order(5)
//    @Test
//    @DisplayName("Should delete invoice")
//    void shouldDeletePerson() {
//        given()
//                .log().all()
//                .pathParam("invoiceId", invoiceId)
//                .when()
//                .delete("/invoice/{invoiceId}")
//                .then()
//                .log().all()
//                .statusCode(204);
//    }
//
//    @Order(6)
//    @Test
//    @DisplayName("Should not find deleted invoice to return")
//    void shouldNotFindDeletedPersonToReturn() {
//        given()
//                .log().all()
//                .accept(ContentType.JSON)
//                .pathParam("invoiceId", invoiceId)
//                .when()
//                .get("/invoice/{invoiceId}")
//                .then()
//                .log().all()
//                .statusCode(404);
//    }
}
