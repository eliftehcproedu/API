package com.techproed.day07;

import com.techproed.testBase.TestBaseHerokuapp;
import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest12 extends TestBaseHerokuapp {
    /*
    https://restful-booker.herokuapp.com/booking/1 url ine bir istek gönderildiğinde
 dönen response body nin
  {
   "firstname": "Eric",
   "lastname": "Smith",
   "totalprice": 555,
   "depositpaid": false,
   "bookingdates": {
       "checkin": "2016-09-09",
       "checkout": "2017-09-21"
    }
} gibi olduğunu test edin.
     */
    @Test
    public void test(){

        spec02.pathParams("parametre1","booking",
                "parametre2",1);
        Response response=given().
                accept("application/json").
                spec(spec02).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();






    }


}
