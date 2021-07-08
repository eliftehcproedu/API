package com.techproed.day10;

import com.techproed.TestData.TestDataHerokuApp;
import com.techproed.testBase.TestBaseHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PostRequest02 extends TestBaseHerokuapp {
    /*
    JSONObject i post yaparken java collectionlari kullanmak yerine JSONObject classindan bir obje olusturyoruz..
    bu obje ile request body olusturuyoruz


   https://restful-booker.herokuapp.com/booking  url ine, Request Body olarak
{ "firstname": "Selim",
               "lastname": "Ak",
               "totalprice": 11111,
               "depositpaid": true,
               "bookingdates": {
                   "checkin": "2020-09-09",
                   "checkout": "2020-09-21"
                }
 }gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
 "booking": {
         "firstname": " Selim ",
         "lastname": " Ak ",
         "totalprice":  11111,
         "depositpaid": true,
         "bookingdates": {
             "checkin": "2020-09-01",
              "checkout": " 2020-09-21”
         },
        }
olduğunu test edin
     */

    @Test
    public void test(){

        //url olustur....

        spec02.pathParam("parametre","booking");

        //requestBody i olustur....
        //expected data olusturmaya gerek yok zaten burayi test et diyor

        TestDataHerokuApp testData=new TestDataHerokuApp();

        JSONObject requestBodyJSONObject=testData.setUpTestData2();
        System.out.println(requestBodyJSONObject);
        //{"firstname":"Selim","bookingdates":{"checkin":"2020-09-09","checkout":"2020-09-21"},"totalprice":11111,"depositpaid":true,"lastname":"Ak"}

        Response response=given().
                contentType(ContentType.JSON). //requestBody JSONObject ile olusturulmussa type bu sekilde
                spec(spec02).
                auth().basic("admin","password123").
                body(requestBodyJSONObject.toString()). //requestBody JSONObject ile olusturulmussa toString() yapariz
                when().post("/{parametre}");

        response.prettyPrint();

        //De-Serialization ile
        HashMap<String ,Object> actualDataMap=response.as(HashMap.class);
        System.out.println(actualDataMap);
        //{booking={firstname=Selim, lastname=Ak, totalprice=11111, depositpaid=true, bookingdates={checkin=2020-09-09, checkout=2020-09-21}}, bookingid=43}

        Assert.assertEquals(requestBodyJSONObject.getString("firstname"),
                ((Map)actualDataMap.get("booking")).get("firstname"));

        Assert.assertEquals(requestBodyJSONObject.getString("lastname"),
                ((Map<?, ?>) actualDataMap.get("booking")).get("lastname"));

        Assert.assertEquals(requestBodyJSONObject.getInt("totalprice"),
                ((Map<?, ?>) actualDataMap.get("booking")).get("totalprice"));

        Assert.assertEquals(requestBodyJSONObject.getBoolean("depositpaid"),
                ((Map<?, ?>) actualDataMap.get("booking")).get("depositpaid"));

        Assert.assertEquals(requestBodyJSONObject.getJSONObject("bookingdates").getString("checkin"),
                ((Map)((Map<?, ?>) actualDataMap.get("booking")).get("bookingdates")).get("checkin"));

        //bookingdates->JSONObject donduruyor..
        Assert.assertEquals(requestBodyJSONObject.getJSONObject("bookingdates").getString("checkout"),
                ((Map)((Map<?, ?>) actualDataMap.get("booking")).get("bookingdates")).get("checkout"));

    }


}