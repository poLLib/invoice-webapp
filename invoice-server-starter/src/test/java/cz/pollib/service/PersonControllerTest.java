package cz.pollib.service;

import cz.pollib.common.BaseIntegrationTest;
import cz.pollib.constant.Countries;
import cz.pollib.dto.PersonDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.transaction.annotation.Transactional;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
class PersonControllerTest extends BaseIntegrationTest {
    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api";
    }

    @Test
    @DisplayName("Should create a person")
    void shouldCreatePerson() {
        PersonDTO newPerson = new PersonDTO(
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
                Countries.CZECHIA,
                null
        );

        Long personId = given()
                .contentType(ContentType.JSON)
                .body(newPerson)
                .when()
                .post("/person")
                .then()
                .statusCode(201)
                .body("name", equalTo("John Doe"))
                .body("identificationNumber", equalTo("11111111"))
                .body("id", notNullValue())
                .extract()
                .path("id");

        given()
                .when()
                .get("/person/{id}", personId)
                .then()
                .statusCode(200)
                .body("name", equalTo("John Doe"))
                .body("identificationNumber", equalTo("11111111"))
                .body("country", equalTo("CZECHIA"));
    }
}
