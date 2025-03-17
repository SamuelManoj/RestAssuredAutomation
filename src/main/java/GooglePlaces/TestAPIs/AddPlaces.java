package GooglePlaces.TestAPIs;

import GooglePlaces.files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AddPlaces {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";
        String response = given()
                    .log().all()
                    .queryParam("key", "qaclick123")
                    .header("Content-Type", "application/json")
                    .body(payload.AddPlaces())
                .when()
                    .post("/maps/api/place/add/json")
                .then()
                    .log().all()
                    .assertThat()
                    .statusCode(200)
                    .body("scope",equalTo("APP"))
                    .header("Server", "Apache/2.4.52 (Ubuntu)")
                    .extract().response().asString();

        System.out.println(response);

        JsonPath js = new JsonPath(response);
        String place_id=js.getString("place_id");
        System.out.println(place_id);
    }
}
