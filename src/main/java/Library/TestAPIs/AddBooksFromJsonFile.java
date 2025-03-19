package Library.TestAPIs;

import Library.files.payload;
import io.restassured.RestAssured;
import org.testng.annotations.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static io.restassured.RestAssured.given;

public class AddBooksFromJsonFile {
    @Test
    public void addBooksFromJsonFile() throws IOException {
        // Add books from a JSON file
        RestAssured.baseURI = "http://216.10.245.166";

        String response = given()
//                .log().all()
                .header("content-type", "application/json")
                .body(new String(Files.readAllBytes(Paths.get("src/main/java/Library/files/addBooks.json")))).
        when()
                .post("/Library/Addbook.php").
        then()
//                .log().all()
                .assertThat()
                .statusCode(200)
                .extract().response().asString();

        System.out.println(response);
    }
}
