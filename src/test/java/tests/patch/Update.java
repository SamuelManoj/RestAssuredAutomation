package tests.patch;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class Update {

    @Test
    public void patchUpdate() {
        baseURI = "https://reqres.in/api";

        JSONObject response = new JSONObject();
        response.put("job", "manager");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(response.toJSONString()).
        when()
                .patch("users/2").
        then()
                .statusCode(200);
    }
}

