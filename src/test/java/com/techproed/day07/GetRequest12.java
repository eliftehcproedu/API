package com.techproed.day07;

import com.techproed.TestData.TestDataHerokuApp;
import com.techproed.testBase.TestBaseHerokuapp;
import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

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
        //Url

        spec02.pathParams("parametre1","booking",
                "parametre2",1);

        //Expected

        TestDataHerokuApp testData =new TestDataHerokuApp();

        HashMap<String,Object> expectedDataMap =testData.setUpTestData();
        System.out.println("expectedDataMap : " + expectedDataMap);
    //expectedDataMap : {firstname=Mary, bookingdates={checkin=2016-09-09, checkout=2017-09-21}, totalprice=345, depositpaid=true, lastname=Wilson}
        //Request gonder

        Response response=given().
                accept("application/json").
                spec(spec02).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();
        
        //Deserialization (json format-> java format)

        Map<String,Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap : " + actualDataMap);
    //actualDataMap : {firstname=Eric, additionalneeds=Breakfast, bookingdates={checkin=2018-04-30, checkout=2020-10-26}, totalprice=681, depositpaid=false, lastname=Jones}

        //Assertian islemi
        Assert.assertEquals(expectedDataMap.get("firstname"),actualDataMap.get("firstname"));
        Assert.assertEquals(expectedDataMap.get("lastname"),actualDataMap.get("lastname"));
        Assert.assertEquals(expectedDataMap.get("totalprice"),actualDataMap.get("totalprice"));
        Assert.assertEquals(expectedDataMap.get("depositpaid"),actualDataMap.get("depositpaid"));
        Assert.assertEquals(((Map)expectedDataMap.get("bookingdates")).get("checkin"),
                            ((Map)actualDataMap.get("bookingdates")).get("checkin"));

                // biz get methodu ile iceride ki veriye ulasmamiz lazim. Get methodu da Map’lerde var.
                // Bu yuzden bizim map’e cevirmem gerekiyor ki
                // get methodu kullanarak ic taraftaki checkin bilgisine ulasabileyim

        Assert.assertEquals( ((Map<?, ?>) expectedDataMap.get("bookingdates")).get("checkout"),
                             ((Map)actualDataMap.get("bookingdates")).get("checkin"));



    }


}
