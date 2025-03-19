package SerializationDeserialization.TestAPIs;

import SerializationDeserialization.POJO.Api;
import SerializationDeserialization.POJO.GetCourseResponse;
import SerializationDeserialization.POJO.WebAutomation;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ClientCredentailsOAuthGetCourseDetails {

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


        GetCourseResponse getCourseResponse = given()
                .queryParam("access_token", token).
        when()
                .get("oauthapi/getCourseDetails").
        then()
                .log().all()
                .extract().response().as(GetCourseResponse.class);

        System.out.println(token);
        System.out.println(getCourseResponse.getLinkedIn());
        System.out.println(getCourseResponse.getInstructor());

        System.out.println(getCourseResponse.getCourses().getApi().get(1).getCourseTitle());

        List<Api> apiCourses = getCourseResponse.getCourses().getApi();
        for(int i=0; i<apiCourses.size(); i++){
            if(apiCourses.get(i).getCourseTitle().equalsIgnoreCase("SoapUI Webservices testing")){
                System.out.println(apiCourses.get(i).getPrice());
            }
        }

        System.out.println("Title under Web Automation");

        List<WebAutomation> webAutomationCourses = getCourseResponse.getCourses().getWebAutomation();
        for(int i=0; i<webAutomationCourses.size(); i++){
            System.out.println(webAutomationCourses.get(i).getCourseTitle());
        }
    }
}
