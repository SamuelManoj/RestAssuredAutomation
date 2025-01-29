package tests.delete;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class Delete {

    @Test
    public void deleteUser(){
        baseURI = "https://reqres.in/api/";

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
        .when()
                .delete("users/2")
        .then()
                .statusCode(204)
                .log().all();
    }
}
