package tests.get;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class ListUsers {

    @Test
    public void userLists(){
        baseURI = "https://reqres.in/api/";

        given()
                .get("users?page=2")
        .then()
                .statusCode(200)
                .body("data[1].id",equalTo(8));
    }

    @Test
    public void checkFirstName(){
        baseURI = "https://reqres.in/api/";
        given()
                .get("users?page=2")
        .then()
                .statusCode(200)
                .body("data.first_name",hasItems("Lindsay","Tobias"));
    }
}
