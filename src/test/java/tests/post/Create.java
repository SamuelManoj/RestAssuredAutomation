package tests.post;

import io.restassured.http.ContentType;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;


public class Create {

    @Test
    public void createUser(){
        baseURI = "https://reqres.in/api";

        JSONObject request = new JSONObject();
        request.put("name","Samuel");
        request.put("job","leader");

        given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body(request.toJSONString())
        .when()
                .post("/users")
        .then()
                .statusCode(201)
                .log().all();
    }
}
