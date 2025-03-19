package Ecom.TestAPIs;

import Ecom.POJO.CreateOrderRequest;
import Ecom.POJO.CreateOrderResponse;
import Ecom.POJO.Orders;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class CreateOrder {

    public static List<String> ordersNo;
    public List<String> productOrderId;
    public String message;

    @Test(dependsOnMethods = {"Ecom.TestAPIs.Login.login","Ecom.TestAPIs.CreateProduct.createProduct"})
    public void createOrder(){

        RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
                .addHeader("Authorization",Login.token).setContentType(ContentType.JSON).build();

        Orders orders = new Orders();
        orders.setCountry("India");
        orders.setProductOrderedId(CreateProduct.productID);

        List<Orders> orderList = new ArrayList<>();
        orderList.add(orders);

        CreateOrderRequest createOrderRequest = new CreateOrderRequest();
        createOrderRequest.setOrders(orderList);

        RequestSpecification res = given().spec(requestSpecification).log().all().body(createOrderRequest);

        CreateOrderResponse createOrderResponse = res.when().post("/api/ecom/order/create-order").then().log().all()
                .assertThat().statusCode(201).extract().response().as(CreateOrderResponse.class);

        ordersNo = createOrderResponse.getOrders();
        System.out.println(ordersNo);
        productOrderId = createOrderResponse.getProductOrderId();
        System.out.println(productOrderId);
        message = createOrderResponse.getMessage();
        System.out.println(message);
    }
}
