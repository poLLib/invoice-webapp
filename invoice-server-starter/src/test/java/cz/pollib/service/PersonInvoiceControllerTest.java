package cz.pollib.service;

import cz.pollib.common.BaseIntegrationTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_CLASS,
        scripts = "classpath:test-data/person-invoice.sql",
        config = @SqlConfig(transactionMode = SqlConfig.TransactionMode.ISOLATED))
public class PersonInvoiceControllerTest extends BaseIntegrationTest {

    @LocalServerPort
    private int port;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/api";
    }

    @Test
    @DisplayName("Should return invoices of seller")
    void shouldReturnSellersInvoices() {
        given()
                .log().all()
                .accept(ContentType.JSON)
                .pathParam("identificationNumber", "987654321")
                .when()
                .get("/identification/{identificationNumber}/sales")
                .then()
                .log().all()
                .statusCode(200)
                .body("size()", equalTo(1))
                .body("[0].dueDate", equalTo("2020-01-01"))
                .body("[0].invoiceNumber", equalTo(123456))
                .body("[0].issued", equalTo("2019-10-01"))
                .body("[0].price", equalTo(100))
                .body("[0].product", equalTo("product"))
                .body("[0].vat", equalTo(21));
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
                .body("size()", equalTo(1))
                .body("[0].dueDate", equalTo("2020-01-01"))
                .body("[0].invoiceNumber", equalTo(123456))
                .body("[0].issued", equalTo("2019-10-01"))
                .body("[0].price", equalTo(100))
                .body("[0].product", equalTo("product"))
                .body("[0].vat", equalTo(21));
    }
}
