package com.techproed.day12;

import com.techproed.pojos.ActualBookingPojo;
import com.techproed.pojos.BookingDatesPojo;
import com.techproed.pojos.BookingPojo;
import com.techproed.testBase.TestBaseDummy;
import com.techproed.testBase.TestBaseHerokuapp;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PostRequestWithPojo02 extends TestBaseHerokuapp {

    /*
    https://restful-booker.herokuapp.com/booking
    request body {
                   "firstname": "Selim",
                   "lastname": "Ak",
                   "totalprice": 15000,
                   "depositpaid": true,
                   "bookingdates": {
                       "checkin": "2020-09-09",
                       "checkout": "2020-09-21"
                    }
                 }
   Status code is 200
    response body  {
                            "bookingid": 11,
                            "booking": {
                                "firstname": "Selim",
                                "lastname": "Ak",
                                "totalprice": 15000,
                                "depositpaid": true,
                                "bookingdates": {
                                    "checkin": "2020-09-09",
                                    "checkout": "2020-09-21"
                                }
                            }
                         }
     */

    @Test
    public void test(){
        //url olustur
        spec02.pathParam("parametre","booking");

        //requestBody olustur
        //en ic katmandan en dis katmana dogru tanimlaniyor
        BookingDatesPojo bookingdates=new BookingDatesPojo("2020-09-09","2020-09-21");
        BookingPojo booking=new BookingPojo("Selim","Ak",15000,true,bookingdates);
        System.out.println("booking = " + booking);
        //booking = BookingPojo{firstname='Selim', lastname='Ak', totalprice=15000, depositpaid=true, bookingdates=BookingDatesPojo{checkin='2020-09-09', checkout='2020-09-21'}}
        //request olustur
        Response response=given().
                contentType(ContentType.JSON).
                spec(spec02).
                auth().basic("admin","password123").
                body(booking).
                when().post("/{parametre}");

        response.prettyPrint();


        // De-serialization

        ActualBookingPojo actualData=response.as(ActualBookingPojo.class);
        System.out.println("actualData = " + actualData);
        //actualData = ActualBookingPojo{bookingid=21, booking=BookingPojo{firstname='Selim', lastname='Ak', totalprice=15000, depositpaid=true, bookingdates=BookingDatesPojo{checkin='2020-09-09', checkout='2020-09-21'}}}

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(booking.getFirstname(),actualData.getBooking().getFirstname());
        Assert.assertEquals(booking.getLastname(),actualData.getBooking().getLastname());
        Assert.assertEquals(booking.getTotalprice(),actualData.getBooking().getTotalprice());
        Assert.assertEquals(booking.isDepositpaid(),actualData.getBooking().isDepositpaid());
        Assert.assertEquals(booking.getBookingdates().getCheckin(),
                actualData.getBooking().getBookingdates().getCheckin());
        Assert.assertEquals(booking.getBookingdates().getCheckout(),
                actualData.getBooking().getBookingdates().getCheckout());


/*
{
    "bookingid": 21,  response dan geliyor
    "booking": {
        "firstname": "Selim",
        "lastname": "Ak",
        "totalprice": 15000,
        "depositpaid": true,
        "bookingdates": {
            "checkin": "2020-09-09",
            "checkout": "2020-09-21"
        }
    }
}
 */
    }

}
