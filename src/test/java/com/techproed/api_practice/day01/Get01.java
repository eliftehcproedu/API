package com.techproed.api_practice.day01;

import com.techproed.testBase.TestBaseHerokuapp;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Get01 {
    /*
         Positive Scenario:
         When Asagidaki Endpoint'e bir GET request yolladim
         https://restful-booker.herokuapp.com/booking/10
     And Accept type “application/json” dir
     Then
     HTTP Status Code 200
     And Response format "application/json"
     And firstname "Mark"
     And lastname "Wilson"
     And depositpaid true
     And checkin date "2016-06-19"
     And checkout date "2019-08-26"
     1.Adim Url'i olustur
     2.Adim Data'yi oluştur
     3.Adim Request gönder
     4.Adim Validation yap
        */

    @Test
    public void test(){
        String endpoint = "https://restful-booker.herokuapp.com/booking/10";
        Response response= RestAssured.given().           //sadece given() da olur o zaman; import static io.restassured.RestAssured.given;
                accept("application/json").
                when().
                get(endpoint);

        response.prettyPrint();
        response.then().
                assertThat().
                statusCode(200).
                body("firstname", Matchers.equalTo("Jim"),
                        "lastname", Matchers.equalTo("Brown"),
                        "depositpaid", Matchers.equalTo(false),
                        "bookingdates.checkin", Matchers.equalTo("2020-11-06"),
                        "bookingdates.checkout", Matchers.equalTo("2020-11-24"));

        JsonPath jsonPath=response.jsonPath();
        Assert.assertEquals("Mark", jsonPath.getString("firstname"));
        Assert.assertEquals("Jackson", jsonPath.getString("lastname"));
        Assert.assertFalse(jsonPath.getBoolean("depositpaid"));
        Assert.assertEquals(970, jsonPath.getInt("totalprice"));
        Assert.assertEquals("2015-02-11", jsonPath.getString("bookingdates.checkin"));
        Assert.assertEquals("2016-06-20", jsonPath.getString("bookingdates.checkout"));

  }

}
