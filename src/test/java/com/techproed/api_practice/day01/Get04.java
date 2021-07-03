package com.techproed.api_practice.day01;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

public class Get04 {
    /*
            Positive Scenario:
         When Asagidaki Endpoint'e bir GET request yolladim
         https://www.gmibank.com/api/tp-customers/42697
     And Accept type “application/json” dir
     Then
     HTTP Status Code 200
     And Response format "application/json"
     And firstname "Ali"
     And lastname "Deckow"
     And middleInitial Leroy Hoeger Sipes
     And email com.github.javafaker.Name@7c011174@gmail.com
     And zelleEnrolled false
     And country null
     */

    @Test
    public void test(){
        String endpoint = "https://www.gmibank.com/api/tp-customers/42697";

        String bearerToken = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJjd29idXJuIiwiYXV0aCI6IlJPTEVfQURNSU4iLCJleHAiOjE2MjU0MDM4NDh9.PAc0NB_A76hmKw7F0DI5KLft2tj1BPHqYh9U-AnX9DlVA2us3YCdjJVIN2uVJ9o4B_qs_cBYtPA77JeGT9ON4g";

        Response response= RestAssured.given().
                auth().oauth2(bearerToken).    //bearer token icin yetki alir..oauth2(bearerToken)
                accept("application/json").
                when().
                get(endpoint);

        response.prettyPrint();
        response.
                then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstName", equalTo("Ali"),
                        "lastName", equalTo("Deckow"),
                        "middleInitial", equalTo("Leroy Hoeger Sipes"),
                        "email", equalTo("com.github.javafaker.Name@7c011174@gmail.com"),
                        "zelleEnrolled", equalTo(false),
                        "country", equalTo(null));
        JsonPath jsonPath = response.jsonPath();
        Assert.assertEquals("Isimler uyusmuyor", "Ali", jsonPath.getString("firstName"));
        Assert.assertEquals("Deckow", jsonPath.getString("lastName"));
        Assert.assertEquals("Leroy Hoeger Sipes", jsonPath.getString("middleInitial"));
        Assert.assertEquals("com.github.javafaker.Name@7c011174@gmail.com", jsonPath.getString("email"));
        Assert.assertEquals(false, jsonPath.getString("zelleEnrolled"));
    }
}