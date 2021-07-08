package com.techproed.TestData;

import org.json.JSONObject;

import java.util.HashMap;

public class TestDataHerokuApp {

    /*
    {
   "firstname": "Eric",
   "lastname": "Smith",
   "totalprice": 555,
   "depositpaid": false,
   "bookingdates": {
       "checkin": "2016-09-09",
       "checkout": "2017-09-21"
    } gibi oldugunu test edin
     */
    public HashMap<String,Object> setUpTestData(){

        //bookingDates icerisinde ayri bir map yapisi vardir. Bu sebebel bu kisim ayri map olusturulur
        //mapin type i String olur. Cunku checkin ve checout degerleri Stringtir

        HashMap<String,String> bookingDatesMap=new HashMap<String, String>();
        bookingDatesMap.put("checkin","2015-09-21");
        bookingDatesMap.put("checkout","2020-12-13");

        HashMap<String,Object> expectedDataMap=new HashMap<String, Object>();
        expectedDataMap.put("firstname","Mary");
        expectedDataMap.put("lastname","Ericsson");
        expectedDataMap.put("totalprice",267);
        expectedDataMap.put("depositpaid",false);
        expectedDataMap.put("bookingdates", bookingDatesMap); // ilk olusturdugumuz map ikinci map in icerisinden

        return expectedDataMap;

    }

    //JSONObject in tek artisi data type ile ugrasmamaktir
    public JSONObject setUpTestData2(){

        JSONObject bookingDates =new JSONObject();
        bookingDates.put("checkin", "2020-09-09");
        bookingDates.put("checkout", "2020-09-21");

        JSONObject booking= new JSONObject();
        booking.put("firstname","Selim");
        booking.put("lastname","Ak");
        booking.put("totalprice",11111);
        booking.put("depositpaid",true);
        booking.put("bookingdates", bookingDates);

        return booking;

    }

}
