package Ecom.TestAPIs;

import Ecom.POJO.ViewOrderDetailsResponse;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ViewOrderDetails {

    @Test(dependsOnMethods = {"Ecom.TestAPIs.CreateOrder.createOrder",
            "Ecom.TestAPIs.Login.login","Ecom.TestAPIs.CreateProduct.createProduct"})
    public void viewOrderDetails(){
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization",Login.token).build();

        RequestSpecification res = given().log().all().spec(requestSpecification).param("id",CreateOrder.ordersNo.get(0));

        ViewOrderDetailsResponse viewOrderDetailsResponse = res.when().get("/api/ecom/order/get-orders-details")
                .then().log().all().assertThat().statusCode(200).extract().response()
                .as(ViewOrderDetailsResponse.class);

        System.out.println(viewOrderDetailsResponse.getData());
        System.out.println(viewOrderDetailsResponse.getMessage());
        System.out.println("**********************");
        System.out.println(viewOrderDetailsResponse.getData().get_id());
        System.out.println(viewOrderDetailsResponse.getData().getOrderById());
        System.out.println(viewOrderDetailsResponse.getData().getOrderBy());
        System.out.println(viewOrderDetailsResponse.getData().getProductOrderedId());
        System.out.println(viewOrderDetailsResponse.getData().getProductName());
        System.out.println(viewOrderDetailsResponse.getData().getCountry());
        System.out.println(viewOrderDetailsResponse.getData().getProductDescription());
        System.out.println(viewOrderDetailsResponse.getData().getProductImage());
        System.out.println(viewOrderDetailsResponse.getData().getOrderPrice());
        System.out.println(viewOrderDetailsResponse.getData().get__v());
        System.out.println("**********************");

    }
}
