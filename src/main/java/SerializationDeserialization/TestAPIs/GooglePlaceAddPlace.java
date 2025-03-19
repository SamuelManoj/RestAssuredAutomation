package SerializationDeserialization.TestAPIs;

import SerializationDeserialization.POJO.AddPlaceRequest;
import SerializationDeserialization.POJO.Location;
import com.beust.ah.A;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GooglePlaceAddPlace {

    @Test
    public void addPlace() {
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        AddPlaceRequest addPlaceRequest = new AddPlaceRequest();
        Location location = new Location();
        location.setLat(-38.383494);
        location.setLng(33.427362);

        addPlaceRequest.setLocation(location);
        addPlaceRequest.setAccuracy(50);
        addPlaceRequest.setName("Frontline house");
        addPlaceRequest.setPhone_number("(+91) 983 893 3937");
        addPlaceRequest.setAddress("29, side layout, cohen 09");
        List<String> types = new ArrayList<>();
        types.add("shoe park");
        types.add("shop");
        addPlaceRequest.setTypes(types);
        addPlaceRequest.setWebsite("http://google.com");
        addPlaceRequest.setLanguage("French-IN");

        given()
                .queryParam("key", "qaclick123")
                .header("Content-Type", "application/json")
                .body(addPlaceRequest)
                .log().all().
        when()
                .post("/maps/api/place/add/json").
        then()
                .statusCode(200)
                .log().all();
    }
}
