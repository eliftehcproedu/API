package com.techproed.api_practice.day01;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

public class Get02 {
    /*
            Positive Scenario:
                 When I send a GET Request to
                     http://dummy.restapiexample.com/api/v1/employees
             And Accept type  “application/JSON” olsun
             Then
                 HTTP Status Code 200 olsun
             And Response'in format "application/JSON" olsun
             And 24 employees olsun
             And "Tiger Nixon" employee'lerden biri olsun
             And 59, 63, ve 23 employee yaslarindan biri olsun
     */
  @Test
    public void test(){

        String endpoint = "http://dummy.restapiexample.com/api/v1/employees";
        Response response= RestAssured.given().
                accept("application/json").
                when().
                get(endpoint);

        response.prettyPrint();

        JsonPath jsonPath=response.jsonPath();

        response.then().assertThat().
                statusCode(200).
                contentType("application/json").
                body("data.id", Matchers.hasSize(24),
                        "data.employee_name", Matchers.hasItem("Tiger Nixon"),
                        "data.employee_age", Matchers.hasItems(23,59,63));





    }
}
