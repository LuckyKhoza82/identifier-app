package org.apps;

import io.quarkus.test.junit.QuarkusTest;
import org.hamcrest.core.Is;
import org.junit.jupiter.api.Test;

import javax.ws.rs.core.MediaType;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;

@QuarkusTest
public class ClientResourceTest {

    @Test
    public void testGetClientWithEmptyParams() {
        given()
                .when().get("/client")
                .then()
                .statusCode(400)
                .body(equalTo("No valid parameter"));
    }

    @Test
    public void testGetClientWithIDParams() {
        given()
                .queryParam("idNumber", "9202438957084")
                .when().get("/client")
                .then()
                .statusCode(200)
                .body(containsString("9202438957084"));
    }

    @Test
    public void testCreateClient() {
        given()
                .body("{\n" +
                        "\t\"idNumber\": \"9202438957083\",\n" +
                        "\t\"name\": \"55544\",\n" +
                        "\t\"surname\": \"55544\",\n" +
                        "\t\"mobileNumber\": \"55544\",\n" +
                        "\t\"physicalAddress\": \"55544\"\n" +
                        "}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when().post("/client")
                .then()
                .statusCode(200)
                .body(equalTo("Saved Successfully"));
    }

    @Test
    public void testUpdateClient() {
        given()
                .body("{\n" +
                        "\t\"idNumber\": \"9202438957083\",\n" +
                        "\t\"name\": \"55544\",\n" +
                        "\t\"surname\": \"55544\",\n" +
                        "\t\"mobileNumber\": \"55544\",\n" +
                        "\t\"physicalAddress\": \"55544\"\n" +
                        "}")
                .header("Content-Type", MediaType.APPLICATION_JSON)
                .when().put("/client/2")
                .then()
                .statusCode(200)
                .body(equalTo("Saved Successfully"));
    }
}