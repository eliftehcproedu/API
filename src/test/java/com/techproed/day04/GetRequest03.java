package com.techproed.day04;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
public class GetRequest03 {
    /*
    https://restful-booker.herokuapp.com/booking/7 url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200
    ve content type'inin "application/json"
    ve firstname'in "Mark",
    ve lastname'in "Smith",
    ve checkin date'in  "2018-03-12",
    ve checkout date'in "2019-05-30" oldugunu test edin
     */

    @Test
    public void test(){
    String url="https://restful-booker.herokuapp.com/booking/7";
        Response response = given().
                accept("application/json").
                when().
                get(url);

        response.prettyPrint();
        // Matchers class : Api da response u verify etmek icin farkli yontemler vardir,
        // bu yontemlerden en temel olani Matcher class dir...
        // Matcher Class dendiginde bunun asserThat() ifadesi ile kullanildigi gelmelidir.
      /*
       response.then().
               assertThat().
               statusCode(200).
               contentType(ContentType.JSON).
               body("firstname", Matchers.equalTo("Eric")).
               body("lastname",Matchers.equalTo("Brown")).
               body("totalprice", Matchers.equalTo(225)).
               body("depositpaid", Matchers.equalTo(false)).
               body("bookingdates.checkin",Matchers.equalTo("2019-10-16")).
               body("bookingdates.checkout",Matchers.equalTo("2021-01-15")).
               body("additionalneeds",Matchers.equalTo("Breakfast"));

       //Test pass oldu ama Api islemleri surekli oldugu icin isim surekli guncelleniyor, o yuzden fail olur

       Yukardaki uzun islem basamaklari yerine;tek body icinde yazilabilir..
       ayrica her seferinde Matchers classini yazmadan da altindaki static methodlari import ederek kullanabiliriz
       import static org.hamcrest.Matchers.equalTo; yerine import static org.hamcrest.Matchers.*;
       yazdik ki butun Matchers classinin butun methodlarina erisebiliriz
       */
        response.then().
                statusCode(200).
                contentType(ContentType.JSON).
                body("firstname", equalTo("Sally"),
                        "lastname", equalTo("Smith"),
                        "totalprice", equalTo(388),
                        "depositpaid", equalTo(true),
                        "bookingdates.checkin", equalTo("2015-04-28"),
                        "bookingdates.checkout", equalTo("2018-09-23"));

    }
}
