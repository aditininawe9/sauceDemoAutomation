package com.testingacademy.Apis;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class ApiTest {
    @Test
    public void getUsersTest() {
        given().header("x-api-key", "pro_8a53d346f917848f922fde353b53e3143e98b9c6102a896c9daa23ce8947a9a0")
                .when()
                    .get("https://reqres.in/api/users?page=2")
                .then()
                    .statusCode(200)
                    .body("page", equalTo(2))
                    .body("data[0].first_name", equalTo("Michael"));
    }

    @Test
    public void createUserTest() {
        String requestBody = "{ \"name\": \"Aditi\", \"job\": \"QA\" }";

        given()
                .header("x-api-key", "pro_8a53d346f917848f922fde353b53e3143e98b9c6102a896c9daa23ce8947a9a0")
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                .body("name", equalTo("Aditi"));
    }
}
