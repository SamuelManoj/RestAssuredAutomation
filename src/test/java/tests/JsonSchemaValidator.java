package tests;

import io.restassured.http.ContentType;
import org.junit.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;


public class JsonSchemaValidator {

    @Test
    public void schemaValidator(){
        baseURI = "https://reqres.in/api/";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON).
        when()
                .get("users?page=2").
        then()
                .assertThat().body(matchesJsonSchemaInClasspath("schema.json"))
                .statusCode(200);
    }
}

