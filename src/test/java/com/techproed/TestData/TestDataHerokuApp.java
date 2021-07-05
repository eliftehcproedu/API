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

}
