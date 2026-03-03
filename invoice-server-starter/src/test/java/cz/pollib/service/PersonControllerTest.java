package cz.pollib.service;

import cz.pollib.common.BaseControllerTest;
import cz.pollib.service.model.CreatePersonRequest;
import cz.pollib.service.model.PersonResponse;
import cz.pollib.service.model.PersonStatisticsResponse;
import cz.pollib.service.model.UpdatePersonRequest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestMethodOrder;

import static cz.pollib.constant.Countries.*;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.assertj.core.api.AssertionsForClassTypes.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerTest extends BaseControllerTest {

    private Long personId;

    @Order(1)
    @Test
    @DisplayName("Should create a person")
    void shouldCreatePerson() {
        PersonResponse result = given()
                .log()
                .ifValidationFails()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(new CreatePersonRequest(
                              "John Doe",
                              "11111111",
                              "CZ65487",
                              "654654654",
                              "5464",
                              "CZ564896",
                              "+420705489657",
                              "john@doe.org",
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
                .ifValidationFails()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("validationjsonschema/person-response.json"))
                .statusCode(201)
                .extract()
                .as(PersonResponse.class);

        assertThat(result).isNotNull();
        assertThat(result.id()).isNotNull()
                               .isPositive();
        assertThat(result.name()).isEqualTo("John Doe");
        assertThat(result.identificationNumber()).isEqualTo("11111111");
        assertThat(result.taxNumber()).isEqualTo("CZ65487");
        assertThat(result.accountNumber()).isEqualTo("654654654");
        assertThat(result.bankCode()).isEqualTo("5464");
        assertThat(result.iban()).isEqualTo("CZ564896");
        assertThat(result.telephone()).isEqualTo("+420705489657");
        assertThat(result.mail()).isEqualTo("john@doe.org");
        assertThat(result.street()).isEqualTo("Studena");
        assertThat(result.zip()).isEqualTo("10000");
        assertThat(result.city()).isEqualTo("Praha");
        assertThat(result.country()).isEqualTo(CZECHIA);
        assertThat(result.note()).isNull();

        personId = result.id();
    }

    @Order(2)
    @Test
    @DisplayName("Should return person")
    void shouldReturnPerson() {
        PersonResponse result = given()
                .log()
                .ifValidationFails()
                .accept(ContentType.JSON)
                .pathParam(
                        "personId",
                        personId
                          )
                .when()
                .get("/person/{personId}")
                .then()
                .log()
                .ifValidationFails()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("validationjsonschema/person-response.json"))
                .statusCode(200)
                .extract()
                .as(PersonResponse.class);

        assertThat(result).isNotNull();
        assertThat(result.id()).isNotNull()
                               .isPositive();
        assertThat(result.name()).isEqualTo("John Doe");
        assertThat(result.identificationNumber()).isEqualTo("11111111");
        assertThat(result.taxNumber()).isEqualTo("CZ65487");
        assertThat(result.accountNumber()).isEqualTo("654654654");
        assertThat(result.bankCode()).isEqualTo("5464");
        assertThat(result.iban()).isEqualTo("CZ564896");
        assertThat(result.telephone()).isEqualTo("+420705489657");
        assertThat(result.mail()).isEqualTo("john@doe.org");
        assertThat(result.street()).isEqualTo("Studena");
        assertThat(result.zip()).isEqualTo("10000");
        assertThat(result.city()).isEqualTo("Praha");
        assertThat(result.country()).isEqualTo(CZECHIA);
        assertThat(result.note()).isNull();
    }

    @Order(3)
    @Test
    @DisplayName("Should update person")
    void shouldUpdatePerson() {
        PersonResponse result = given()
                .log()
                .ifValidationFails()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(new UpdatePersonRequest(
                              "Rumburak",
                              null,
                              "98745385",
                              "6454",
                              "SK98745385",
                              "+421708489657",
                              "rumbu@rak.sk",
                              "Zlova",
                              "20065",
                              "Blava",
                              SLOVAKIA,
                              "neviditelny"
                      )
                     )
                .pathParam(
                        "personId",
                        personId
                          )
                .when()
                .put("/person/{personId}")
                .then()
                .log()
                .ifValidationFails()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("validationjsonschema/person-response.json"))
                .statusCode(200)
                .extract()
                .as(PersonResponse.class);

        assertThat(result).isNotNull();
        assertThat(result.id()).isEqualTo(personId);
        assertThat(result.name()).isEqualTo("Rumburak");

        assertThat(result.identificationNumber()).isEqualTo("11111111");
        assertThat(result.taxNumber()).isNull();

        assertThat(result.accountNumber()).isEqualTo("98745385");
        assertThat(result.bankCode()).isEqualTo("6454");
        assertThat(result.iban()).isEqualTo("SK98745385");
        assertThat(result.telephone()).isEqualTo("+421708489657");
        assertThat(result.mail()).isEqualTo("rumbu@rak.sk");
        assertThat(result.street()).isEqualTo("Zlova");
        assertThat(result.zip()).isEqualTo("20065");
        assertThat(result.city()).isEqualTo("Blava");
        assertThat(result.country()).isEqualTo(SLOVAKIA);
        assertThat(result.note()).isEqualTo("neviditelny");
    }

    @Order(4)
    @Test
    @DisplayName("Should return persons")
    void shouldReturnPersons() {
        PersonResponse[] results = given()
                .log()
                .ifValidationFails()
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
                .get("/persons")
                .then()
                .log()
                .ifValidationFails()
                .statusCode(200)
                .extract()
                .as(PersonResponse[].class);

        assertThat(results).isNotEmpty();

        assertThat(results)
                .extracting(PersonResponse::id)
                .contains(personId);
    }

    @Order(5)
    @Test
    @DisplayName("Should delete person")
    void shouldDeletePerson() {
        given()
                .log()
                .ifValidationFails()
                .pathParam(
                        "personId",
                        personId
                          )
                .when()
                .delete("/person/{personId}")
                .then()
                .log()
                .ifValidationFails()
                .statusCode(204);
    }

    @Order(6)
    @Test
    @DisplayName("Should not find deleted person to return")
    void shouldNotFindDeletedPersonToReturn() {
        given()
                .log()
                .ifValidationFails()
                .accept(ContentType.JSON)
                .pathParam(
                        "personId",
                        personId
                          )
                .when()
                .get("/person/{personId}")
                .then()
                .log()
                .ifValidationFails()
                .statusCode(404);
    }

    @Order(7)
    @Test
    @DisplayName("Should return person statistics")
    void shouldReturnPersonStatistics() {
        given()
                .log()
                .ifValidationFails()
                .accept(ContentType.JSON)
                .when()
                .get("/person/statistics")
                .then()
                .log()
                .ifValidationFails()
                .assertThat()
                .body(matchesJsonSchemaInClasspath("validationjsonschema/person-statistics-response.json"))
                .statusCode(200)
                .extract()
                .as(PersonStatisticsResponse[].class);
    }
}
