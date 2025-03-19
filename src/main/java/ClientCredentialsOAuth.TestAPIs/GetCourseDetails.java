package ClientCredentialsOAuth.TestAPIs;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class GetCourseDetails {

    @Test
    public void getCourseDetails(){
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given()
                .formParam("client_id","692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParam("client_secret","erZOWM9g3UtwNRj340YYaK_W")
                .formParam("grant_type", "client_credentials")
                .formParam("scope","trust").
        when()
                .post("/oauthapi/oauth2/resourceOwner/token").
        then()
                .log().all()
                .extract().response().asString();

        JsonPath js = new JsonPath(response);
        String token = js.getString("access_token");

    }
}
