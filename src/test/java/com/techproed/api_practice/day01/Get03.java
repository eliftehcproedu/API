package com.techproed.api_practice.day01;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;

import static org.hamcrest.Matchers.equalTo;

public class Get03 {
    /*
    1)
    When I send a GET request to REST API URL
    https://jsonplaceholder.typicode.com/todos/123
    Then HTTP Status Code should be 200
    And "Server" in Headers should be "cloudflare"
    And response content type is “application/JSON”
    And "userId" should be 5,
    And "excepturi a et neque qui expedita vel voluptate"
    And "completed" should be false
            */

    @Test
    public void test(){
     String endpoint = "https://jsonplaceholder.typicode.com/todos/123";
        Response response=RestAssured.given().
                accept("application/json").
                when().get(endpoint);
        response.prettyPrint();

        response.then().
                assertThat().
                statusCode(200).
                contentType("application/json").
                header("Server",equalTo("cloudflare")).
                body("userId",equalTo(7),
                        "title",equalTo("esse et quis iste est earum aut impedit"),
                        "completed"   ,equalTo(false) );

    }
}
