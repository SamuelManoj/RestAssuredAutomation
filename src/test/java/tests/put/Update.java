package tests.put;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;

public class Update {

    @Test
    public void updateUser(){
        baseURI = "https://reqres.in/api/";

        JSONObject response = new JSONObject();
        response.put("name","samuel");
        response.put("job","lead");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(response.toJSONString())
        .when()
                .put("users/2")
        .then()
                .statusCode(200)
                .log().all();
    }
}
