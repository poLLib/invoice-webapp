package cz.pollib.service;

import cz.pollib.common.BaseIntegrationTest;
import cz.pollib.dto.PersonDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static cz.pollib.constant.Countries.*;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class PersonControllerTest extends BaseIntegrationTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api";
    }

    static Long personId;

    @Order(1)
    @Test
    @DisplayName("Should create a person")
    void shouldCreatePerson() {
        PersonDTO createdPerson = given()
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
                .assertThat().body(matchesJsonSchemaInClasspath("validationjsonschema/person-schema.json"))
                .log().all()
                .statusCode(201)
                .body("name", equalTo("John Doe"))
                .body("identificationNumber", equalTo("11111111"))
                .body("taxNumber", equalTo("CZ65487"))
                .body("accountNumber", equalTo("654654654"))
                .body("bankCode", equalTo("5464"))
                .body("iban", equalTo("CZ564896"))
                .body("telephone", equalTo("+420705489657"))
                .body("mail", equalTo("john@doe.org"))
                .body("street", equalTo("Studena"))
                .body("zip", equalTo("10000"))
                .body("city", equalTo("Praha"))
                .body("country", equalTo("CZECHIA"))
                .body("note", equalTo(null))
                .extract()
                .as(PersonDTO.class);

        personId = createdPerson.getId();
    }

    @Order(2)
    @Test
    @DisplayName("Should return person")
    void shouldReturnPerson() {
        given()
                .log().all()
                .accept(ContentType.JSON)
                .pathParam("personId", personId)
                .when()
                .get("/person/{personId}")
                .then()
                .log().all()
                .statusCode(200)
                .body("_id", equalTo(personId.intValue()))
                .body("name", equalTo("John Doe"))
                .body("identificationNumber", equalTo("11111111"))
                .body("taxNumber", equalTo("CZ65487"))
                .body("accountNumber", equalTo("654654654"))
                .body("bankCode", equalTo("5464"))
                .body("iban", equalTo("CZ564896"))
                .body("telephone", equalTo("+420705489657"))
                .body("mail", equalTo("john@doe.org"))
                .body("street", equalTo("Studena"))
                .body("zip", equalTo("10000"))
                .body("city", equalTo("Praha"))
                .body("country", equalTo("CZECHIA"))
                .body("note", equalTo(null));
    }

    @Order(3)
    @Test
    @DisplayName("Should edit person")
    void shouldEditPerson() {
        given()
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
                .assertThat().body(matchesJsonSchemaInClasspath("validationjsonschema/person-schema.json"))
                .log().all()
                .statusCode(200)
                .body("name", equalTo("Rumburak"))
                .body("identificationNumber", equalTo("11111111"))
                .body("taxNumber", equalTo("CZ65487"))
                .body("accountNumber", equalTo("98745385"))
                .body("bankCode", equalTo("6454"))
                .body("iban", equalTo("SK98745385"))
                .body("telephone", equalTo("+421708489657"))
                .body("mail", equalTo("rumbu@rak.sk"))
                .body("street", equalTo("Zlova"))
                .body("zip", equalTo("20065"))
                .body("city", equalTo("Blava"))
                .body("country", equalTo("SLOVAKIA"))
                .body("note", equalTo("neviditelny"))
                .extract()
                .as(PersonDTO.class);
    }

    @Order(4)
    @Test
    @DisplayName("Should return persons")
    void shouldReturnPersons() {
        given()
                .log().all()
                .accept(ContentType.JSON)
                .param("page", "0")
                .param("size", "10")
                .when()
                .get("/persons")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", equalTo(1))
                .body("[0].name", equalTo("Rumburak"))
                .body("[0].identificationNumber", equalTo("11111111"))
                .body("[0].taxNumber", equalTo("CZ65487"))
                .body("[0].accountNumber", equalTo("98745385"))
                .body("[0].bankCode", equalTo("6454"))
                .body("[0].iban", equalTo("SK98745385"))
                .body("[0].telephone", equalTo("+421708489657"))
                .body("[0].mail", equalTo("rumbu@rak.sk"))
                .body("[0].street", equalTo("Zlova"))
                .body("[0].zip", equalTo("20065"))
                .body("[0].city", equalTo("Blava"))
                .body("[0].country", equalTo("SLOVAKIA"))
                .body("[0].note", equalTo("neviditelny"));
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
