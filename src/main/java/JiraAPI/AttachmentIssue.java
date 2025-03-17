package JiraAPI;

import GooglePlaces.files.ResuableMethods;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class AttachmentIssue {

    @Test
    public void createIssue() {
        RestAssured.baseURI = "https://samuelmanoj.atlassian.net";

        String response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Basic c2FtdWVsbWFub2ouYmVAZ21haWwuY29tOkFUQVRUM3hGZkdGMHlOSUpFaTJfX1Y2YlVrSDI4UmZQZ2xCeGVBR3BKOUQ4MldrdzFSR2haMTFTWFBQTzZVUXJXZUpZQlhKUGg1TzE4N1pDbHNycDNmblhwbmhzMGVqOGo1dlRpTmtoalN3OVR3V2huUEh6Nk11Wml1TEZ4bHg4bktSdGNHWXg2RGZVNnBzakFZeEo1ZFpHaW5oazVQbGNuVDQzbHZ4ZU1NNXhvcGw1RUZyNjlnbz1GMjAxNEY4NA==")
                .body("{\n" +
                        "    \"fields\": {\n" +
                        "       \"project\":\n" +
                        "       {\n" +
                        "          \"key\": \"SCRUM\"\n" +
                        "       },\n" +
                        "       \"summary\": \"UI Value\",\n" +
                        "       \"issuetype\": {\n" +
                        "          \"name\": \"Bug\"\n" +
                        "       }\n" +
                        "   }\n" +
                        "}").
        when()
                .post("/rest/api/3/issue").
        then()
                .assertThat()
                .statusCode(201)
                .extract().response().asString();

        System.out.println(response);

        JsonPath js = ResuableMethods.rawToJson(response);
        String testcase_id = js.getString("id");


        String attach_response = given()
                    .header("X-Atlassian-Token", "no-check")
                    .header("Authorization", "Basic c2FtdWVsbWFub2ouYmVAZ21haWwuY29tOkFUQVRUM3hGZkdGMHlOSUpFaTJfX1Y2YlVrSDI4UmZQZ2xCeGVBR3BKOUQ4MldrdzFSR2haMTFTWFBQTzZVUXJXZUpZQlhKUGg1TzE4N1pDbHNycDNmblhwbmhzMGVqOGo1dlRpTmtoalN3OVR3V2huUEh6Nk11Wml1TEZ4bHg4bktSdGNHWXg2RGZVNnBzakFZeEo1ZFpHaW5oazVQbGNuVDQzbHZ4ZU1NNXhvcGw1RUZyNjlnbz1GMjAxNEY4NA==")
                    .pathParams("key", testcase_id)
                    .multiPart("file", new File("src/main/java/Library/files/image.jpg")).
                when()
                    .post("/rest/api/3/issue/{key}/attachments").
                then()
                    .assertThat()
                    .statusCode(200)
                    .extract().response().asString();

        System.out.println(attach_response);


    }
}
