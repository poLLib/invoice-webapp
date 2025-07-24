package cz.pollib.service;

import cz.pollib.common.BaseControllerTest;
import cz.pollib.dto.PersonDTO;
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
class PersonControllerTest extends BaseControllerTest {

    private Long personId;

    @Order(1)
    @Test
    @DisplayName("Should create a person")
    void shouldCreatePerson() {
        PersonDTO result = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(new PersonDTO(
                        null,
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
                ))
                .when()
                .post("/person")
                .then()
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("validationjsonschema/person-schema.json"))
                .statusCode(201)
                .extract()
                .as(PersonDTO.class);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull().isPositive();
        assertThat(result.getName()).isEqualTo("John Doe");
        assertThat(result.getIdentificationNumber()).isEqualTo("11111111");
        assertThat(result.getTaxNumber()).isEqualTo("CZ65487");
        assertThat(result.getAccountNumber()).isEqualTo("654654654");
        assertThat(result.getBankCode()).isEqualTo("5464");
        assertThat(result.getIban()).isEqualTo("CZ564896");
        assertThat(result.getTelephone()).isEqualTo("+420705489657");
        assertThat(result.getMail()).isEqualTo("john@doe.org");
        assertThat(result.getStreet()).isEqualTo("Studena");
        assertThat(result.getZip()).isEqualTo("10000");
        assertThat(result.getCity()).isEqualTo("Praha");
        assertThat(result.getCountry()).isEqualTo(CZECHIA);
        assertThat(result.getNote()).isNull();

        personId = result.getId();
    }

    @Order(2)
    @Test
    @DisplayName("Should return person")
    void shouldReturnPerson() {
        PersonDTO result = given()
                .log().all()
                .accept(ContentType.JSON)
                .pathParam("personId", personId)
                .when()
                .get("/person/{personId}")
                .then()
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("validationjsonschema/person-schema.json"))
                .statusCode(200)
                .extract()
                .as(PersonDTO.class);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isNotNull().isPositive();
        assertThat(result.getName()).isEqualTo("John Doe");
        assertThat(result.getIdentificationNumber()).isEqualTo("11111111");
        assertThat(result.getTaxNumber()).isEqualTo("CZ65487");
        assertThat(result.getAccountNumber()).isEqualTo("654654654");
        assertThat(result.getBankCode()).isEqualTo("5464");
        assertThat(result.getIban()).isEqualTo("CZ564896");
        assertThat(result.getTelephone()).isEqualTo("+420705489657");
        assertThat(result.getMail()).isEqualTo("john@doe.org");
        assertThat(result.getStreet()).isEqualTo("Studena");
        assertThat(result.getZip()).isEqualTo("10000");
        assertThat(result.getCity()).isEqualTo("Praha");
        assertThat(result.getCountry()).isEqualTo(CZECHIA);
        assertThat(result.getNote()).isNull();
    }

    @Order(3)
    @Test
    @DisplayName("Should edit person")
    void shouldEditPerson() {
        PersonDTO result = given()
                .log().all()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(new PersonDTO(
                        null,
                        "Rumburak",
                        null,
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
                ))
                .pathParam("personId", personId)
                .when()
                .put("/person/{personId}")
                .then()
                .log().all()
                .assertThat().body(matchesJsonSchemaInClasspath("validationjsonschema/person-schema.json"))
                .statusCode(200)
                .extract()
                .as(PersonDTO.class);

        assertThat(result).isNotNull();
        assertThat(result.getId()).isEqualTo(personId);
        assertThat(result.getName()).isEqualTo("Rumburak");

        assertThat(result.getIdentificationNumber()).isEqualTo("11111111");
        assertThat(result.getTaxNumber()).isEqualTo("CZ65487");

        assertThat(result.getAccountNumber()).isEqualTo("98745385");
        assertThat(result.getBankCode()).isEqualTo("6454");
        assertThat(result.getIban()).isEqualTo("SK98745385");
        assertThat(result.getTelephone()).isEqualTo("+421708489657");
        assertThat(result.getMail()).isEqualTo("rumbu@rak.sk");
        assertThat(result.getStreet()).isEqualTo("Zlova");
        assertThat(result.getZip()).isEqualTo("20065");
        assertThat(result.getCity()).isEqualTo("Blava");
        assertThat(result.getCountry()).isEqualTo(SLOVAKIA);
        assertThat(result.getNote()).isEqualTo("neviditelny");
    }

    @Order(4)
    @Test
    @DisplayName("Should return persons")
    void shouldReturnPersons() {
        PersonDTO[] results = given()
                .log().all()
                .accept(ContentType.JSON)
                .param("page", "0")
                .param("size", "10")
                .when()
                .get("/persons")
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .as(PersonDTO[].class);

        assertThat(results).isNotEmpty();

        assertThat(results)
                .extracting(PersonDTO::getId)
                .contains(personId);
    }

    @Order(5)
    @Test
    @DisplayName("Should delete person")
    void shouldDeletePerson() {
        given()
                .log().all()
                .pathParam("personId", personId)
                .when()
                .delete("/person/{personId}")
                .then()
                .log().all()
                .statusCode(204);
    }

    @Order(6)
    @Test
    @DisplayName("Should not find deleted person to return")
    void shouldNotFindDeletedPersonToReturn() {
        given()
                .log().all()
                .accept(ContentType.JSON)
                .pathParam("personId", personId)
                .when()
                .get("/person/{personId}")
                .then()
                .log().all()
                .statusCode(404);
    }
}
