package com.techproed.day05;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest05 {
    /*
    http://dummy.restapiexample.com/api/v1/employees url'ine
    accept type'i "application/json" olan GET request'i yolladigimda
    gelen response'un
    status kodunun 200    ve content type'inin "application/json"
   ve employees sayisinin 24
   ve employee'lerden birinin "Ashton Cox"
   ve girilen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin
     */
    @Test
    public void test(){
        //url olustur
        String url="http://dummy.restapiexample.com/api/v1/employees";

        //expected data olustur...su an ihtiyacimiz yok

        //request gonder
        Response response = given().
                accept("application/json").
                when().
                get(url);

        response.prettyPrint();

        /*response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data.id", Matchers.hasSize(24),
                "data.employee_name", Matchers.hasItem("Ashton Cox"),
                "data.employee_age", Matchers.hasItems(21,61,23));
        yerine */
        response.then().
                assertThat().
                statusCode(200).
                contentType(ContentType.JSON).
                body("data.id", hasSize(24),
                        "data.employee_name", hasItem("Ashton Cox"),
                        "data.employee_age", hasItems(21,61,23));
        // data.id -> kac id var onu sayar yanindaki  hasSize(24) ise bu sayinin 24 olup olmadigini assert eder..
        // data.employee_name -> butun employee_name lere bak hasItem() daki bir isim var mi diye arar...
        // data.employee_age -> butun  employee_age lere bak aralarinda hasItems(21,61,23) deki degerler var mi diye bak..

        // Matchers lari kaldirip hasSize methodunu import edip sonrasinda da daha da genellestirerek
        // import static org.hamcrest.Matchers.*; yaptik..

       /* What methods are you using to verify the size of the response data?
                I use Matchers from Hamcrest
        Int sorusu

        */
    }

}
