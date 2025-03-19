package Ecom.TestAPIs;

import Ecom.POJO.CreateProductResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class CreateProduct {

    public static String productID;

    @Test(dependsOnMethods = {"Ecom.TestAPIs.Login.login"})
    public void createProduct() {

        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization", Login.token)
                .build();

        RequestSpecification req = given().log().all().spec(requestSpecification).param("productName","qwerty")
                .param("productAddedBy",Login.userID).param("productCategory","fashion")
                .param("productSubCategory","shirts").param("productPrice",11500)
                .param("productDescription","Addias Originals").param("productFor","women")
                .multiPart("productImage", new File("src/main/java/Library/files/image.jpg"));

        CreateProductResponse createProductResponse = req.when().post("/api/ecom/product/add-product").then().log()
                .all().assertThat().statusCode(201).extract().response().as(CreateProductResponse.class);

        productID = createProductResponse.getProductId();
        System.out.println(productID);

    }
}
