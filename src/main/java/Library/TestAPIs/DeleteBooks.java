package Library.TestAPIs;

import GooglePlaces.files.ResuableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteBooks {
    @Test(dataProvider="DeleteBooks")
    public void deleteBook(String isbn, String aisle) {
        RestAssured.baseURI = "http://216.10.245.166";

        String response = given()
                .header("content-type", "application/json")
                .body("{\n" +
                        " \n" +
                        "\"ID\" : \""+isbn+aisle+"\"\n" +
                        " \n" +
                        "}").
        when()
                .delete("/Library/DeleteBook.php").
        then()
                .assertThat()
                .extract().response().asString();

        JsonPath js = ResuableMethods.rawToJson(response);
        String msg = js.getString("msg");
        System.out.println(msg);
    }

    @DataProvider(name="DeleteBooks")
    public Object[][] getData(){
        return new Object[][]{{"123","sam"},{"456","tom"},{"789","jerry"}};
    }
}
