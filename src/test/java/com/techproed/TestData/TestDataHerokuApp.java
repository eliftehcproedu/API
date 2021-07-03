package com.techproed.TestData;

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

        HashMap<String,String> bookingDatesMap= new HashMap<String,String>();
        bookingDatesMap.put("checkout","2016-09-09");
        bookingDatesMap.put("checkout","2017-09-21");

        HashMap<String,Object>expectedDataMap=new HashMap<String,Object>();
        expectedDataMap.put("firstname","Mary");
        expectedDataMap.put("lastname", "Wilson");
        expectedDataMap.put("totalprice", 345);
        expectedDataMap.put("depositpaid", true);
        expectedDataMap.put("bookingdates", bookingDatesMap); // ilk olusturdugumuz map ikinci map in icerisinden

        return expectedDataMap;

    }

}
