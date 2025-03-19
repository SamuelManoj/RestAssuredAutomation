package SpecBuilders.TestAPIs;

import SerializationDeserialization.POJO.AddPlaceRequest;
import SerializationDeserialization.POJO.Location;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.apache.http.client.methods.RequestBuilder;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GooglePlaceAddPlace {

    @Test
    public void addPlace() {
        RestAssured.baseURI = "";

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

        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                        .addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(200)
                .expectContentType(ContentType.JSON).build();

        RequestSpecification req = given().spec(requestSpecification).body(addPlaceRequest).log().all();

        Response res = req.when().post("/maps/api/place/add/json").then().spec(responseSpecification).extract().response();

        System.out.println(res.asString());
    }
}
