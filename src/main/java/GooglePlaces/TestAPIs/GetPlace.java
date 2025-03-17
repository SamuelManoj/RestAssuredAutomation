package GooglePlaces.TestAPIs;

import GooglePlaces.files.ResuableMethods;
import GooglePlaces.files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetPlace {
    public static void main(String[] args) {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        //post request
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
                    .header("server", "Apache/2.4.52 (Ubuntu)")
                    .body("scope", equalTo("APP"))
                    .extract().response().asString();

        System.out.println(response);

        JsonPath js = ResuableMethods.rawToJson(response);
        String place_id = js.getString("place_id");

        //update request
        String address = "70 winter walk, USA";
        String updatedResponse = given()
                .log().all()
                .queryParam("key","qaclick123")
                .header("content-type","application/json")
                .body("{\n" +
                        "\"place_id\":\""+place_id+"\",\n" +
                        "\"address\":\""+ address+ "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}").
        when()
                .put("maps/api/place/update/json").
        then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .header("server","Apache/2.4.52 (Ubuntu)")
                .body("msg",equalTo("Address successfully updated"))
                .extract().response().asString();

        System.out.println(updatedResponse);

        //Get Place
        String getPlaceResponse= given()
                .log().all()
                .queryParam("key","qaclick123")
                .queryParam("place_id",place_id)
                .header("content-type","application/json").
        when()
                .get("maps/api/place/get/json").
        then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract().response().asString();

        System.out.println(getPlaceResponse);

        JsonPath js1 = ResuableMethods.rawToJson(getPlaceResponse);
        String actualAddress = js1.getString("address");
        Assert.assertEquals(actualAddress,address);

    }
}
