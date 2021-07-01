package com.techproed.day03;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest01 {
    /*
    GetRequest01:
    https://restful-booker.herokuapp.com/booking/3 adresine bir request gonderildiginde donecek cevap(response) icin
    ØHTTP status kodunun 200
    ØContent Type’in Json
    ØVe Status Line’in HTTP/1.1 200 OK
    Oldugunu test edin.
    */

    @Test
    public void test01(){

        // 1- url belirlenmelidir
        String url = "https://restful-booker.herokuapp.com/booking/3";

        //2-expected result olusturulmalidir
            // Responce Body'den herhangi bir sey kontrol etmedigimiz icin Expected Result olusturmaya gerek yoktur.

        //3-request gonderilmelidir
            // ( given(): url gondermeden onceki yapilacaklari ifade eder, gereksinimleri tanimlar,text content type: json secmek gibi)
        Response  response=given().
                accept("application/json").
                when().
                get(url);
            //URL aldiginda json formatini kabul et demektir

        response.prettyPrint(); //prettyPrint(): Response body i consolda goruntulememizi saglar, bu satir yazilmazsa consol bos olur

        //4-response alinmalidir (actual result)
            //body testi yapmadigim icin actual result almiyoruz

        //5-assertion islemini yapilmalidir
            //Then() :ciktilari ifade eder, Assertion islemleri gibi..
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                statusLine("HTTP/1.1 200 OK");

        System.out.println("response daki status code :" + response.getStatusCode()); //200
        System.out.println("response daki status line :" + response.getStatusLine()); //HTTP/1.1 200 OK
        System.out.println("response daki headers :" + response.getHeaders()); //ciktisi uzun
        System.out.println("response daki content Type:" + response.getContentType()); //application/json; charset=utf-8
    }
}
