package GooglePlaces.TestAPIs;

import GooglePlaces.files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class UpdatePlace {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given()
                    .log().all()
                    .queryParam("key", "qaclick123")
                    .header("content-type", "application/json")
                    .body(payload.AddPlaces()).
                when()
                    .post("maps/api/place/add/json").
                then()
                    .log().all()
                    .assertThat()
                    .statusCode(200)
                    .header("Server", "Apache/2.4.52 (Ubuntu)")
                    .body("scope",equalTo("APP"))
                    .extract().response().asString();

        System.out.println(response);
        JsonPath js = new JsonPath(response);
        String place_id = js.getString("place_id");

        System.out.println(place_id);

        given()
                .log().all()
                .queryParam("key", "qaclick123")
                .header("content-type", "application/json")
                .body("{\n" +
                        "\"place_id\":\""+place_id+"\",\n" +
                        "\"address\":\"70 Summer walk, USA\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}").
        when()
                .put("maps/api/place/update/json").
        then()
                .log().all()
                .assertThat()
                .header("server", "Apache/2.4.52 (Ubuntu)")
                .statusCode(200)
                .body("msg", equalTo("Address successfully updated"));

    }
}
