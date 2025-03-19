package Ecom.TestAPIs;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DeleteProduct {
    @Test(dependsOnMethods = {"Ecom.TestAPIs.CreateProduct.createProduct","Ecom.TestAPIs.Login.login",
            "Ecom.TestAPIs.CreateOrder.createOrder","Ecom.TestAPIs.ViewOrderDetails.viewOrderDetails"})
    public void deleteProduct(){
        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",Login.token).build();

        RequestSpecification res = given().relaxedHTTPSValidation().pathParams("id",CreateProduct.productID)
                .spec(requestSpecification).log().all();

        String response = res.when().delete("/api/ecom/product/delete-product/{id}").then().log().all().assertThat()
                .statusCode(200).extract().response().asString();

        JsonPath jsonPath = new JsonPath(response);
        String message = jsonPath.get("message");
        System.out.println(message);

        Assert.assertEquals("Product Deleted Successfully",message);
    }
}
