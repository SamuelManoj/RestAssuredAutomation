package Ecom.TestAPIs;

import Ecom.POJO.LoginRequest;
import Ecom.POJO.LoginResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class Login {

    public static String token;
    public static String userID;

    @Test
    public void login(){

        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("Samuel_Manoj@Outlook.com");
        loginRequest.setUserPassword("Ani@0310");

        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON).build();

        RequestSpecification req = given().spec(requestSpecification).body(loginRequest).log().all();

        LoginResponse response = req.when().post("api/ecom/auth/login").then().extract().response().as(LoginResponse.class);

        token = response.getToken();
        System.out.println(token);
        userID=response.getUserId();
        System.out.println(response.getMessage());
        System.out.println(userID);

    }
}
