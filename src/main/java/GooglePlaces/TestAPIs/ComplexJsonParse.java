package GooglePlaces.TestAPIs;

import GooglePlaces.files.payload;
import io.restassured.path.json.JsonPath;

public class ComplexJsonParse {
    public static void main(String[] args) {
        JsonPath js = new JsonPath(payload.CoursePrice());

//        1. Print No of courses returned by API
        int count = js.getInt("courses.size()");
        System.out.println("No of courses is " + count);

//        2.Print Purchase Amount
        int purchaseAmount = js.getInt("dashboard.purchaseAmount");
        System.out.println("Purchase Amount is " + purchaseAmount);

//        3. Print Title of the first course
        String firstTitle = js.getString("courses[0].title");
        System.out.println("Title of the first course is " + firstTitle);

//        4. Print All course titles and their respective Prices
        for (int i = 0; i < count; i++) {
            String title = js.getString("courses[" + i + "].title");
            int price = js.getInt("courses[" + i + "].price");
            System.out.println("Title of the course is " + title + " and price is " + price);
        }

//        5. Print no of copies sold by RPA Course
        for (int i = 0; i < count; i++) {
            String title = js.getString("courses[" + i + "].title");
            if (title.equalsIgnoreCase("RPA")) {
                int copies = js.getInt("courses[" + i + "].copies");
                System.out.println("No of copies sold by RPA Course is " + copies);
                break;
            }
        }

//        6. Verify if Sum of all Course prices matches with Purchase Amount
        int sum = 0;
        for (int i = 0; i < count; i++) {
            int price = js.getInt("courses[" + i + "].price");
            int copies1 = js.getInt("courses[" + i + "].copies");
            sum = sum + price * copies1;
        }
        System.out.println("Sum of all course prices is " + sum);
        if (sum == purchaseAmount) {
            System.out.println("Sum of all course prices matches with Purchase Amount");
        } else {
            System.out.println("Sum of all course prices does not match with Purchase Amount");
        }
    }
}
