package Library.TestAPIs;

import GooglePlaces.files.ResuableMethods;
import Library.files.payload;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class AddBooks {
    @Test(dataProvider = "Addbooks")
    public void addBooks(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";

        String response = given()
//                .log().all()
                .header("content-type", "application/json")
                .body(payload.AddBooks(isbn, aisle)).
        when()
                .post("/Library/Addbook.php").
        then()
//                .log().all()
                .assertThat()
                .statusCode(200)
                .extract().response().asString();

        JsonPath js = ResuableMethods.rawToJson(response);
        String id = js.getString("ID");
        System.out.println(id);
    }

    @DataProvider(name="Addbooks")
        public Object[][] getData(){
            return new Object[][]{{"123","sam"},{"456","tom"},{"789","jerry"}};
    }
}
