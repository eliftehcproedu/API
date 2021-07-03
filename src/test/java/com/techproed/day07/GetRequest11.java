package com.techproed.day07;

import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class GetRequest11 extends TestBaseJsonPlaceHolder{

/*
https://jsonplaceholder.typicode.com/todos/2 url ‘ine istek gönderildiğinde,
 Dönen response un
 Status kodunun 200, dönen body de,
       "completed": değerinin false
       "title”: değerinin “quis ut nam facilis et officia qui”
       "userId"  sinin 1 ve header değerlerinden
 "Via" değerinin “1.1 vegur” ve
       "Server" değerinin “cloudflare” olduğunu test edin…
 */

    @Test
    public void test(){
     //  pathParams da ilk girilen parametreyi string aliyor geri kalanlari object olarak aliyor
        //1-url olustur
        spec01.pathParams("parametre1","todos",
                "parametre2",2);
        //2- expected data olustur

        HashMap<String,Object>expectedDataMap=new HashMap<String,Object>();
        expectedDataMap.put("statusCode",200);
        expectedDataMap.put("completed",false);
        expectedDataMap.put("userId",1);
        expectedDataMap.put("title","quis ut nam facilis et officia qui");
        expectedDataMap.put("Via","1.1 vegur");
        expectedDataMap.put("Server","cloudflare");

        System.out.println("ExpectedData");
        System.out.println(expectedDataMap);
        //{Server=cloudflare, completed=false, title=quis ut nam facilis et officia qui, userId=1, statusCode=200, Via=1.1 vegur}
        System.out.println("===============================");

        //3-Request gonder
        Response response= given().
                accept("application/json").
                spec(spec01).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();
        //4-Actual datayi olustur
        //json format-> java objesine donusturme = Deserialization

       HashMap<String,Object> actualDataMap=response.as(HashMap.class);  //Deserialization
       //api den gelen response body i HashMap gibi yap, yaptigin bu HashMap i git actualDataMap e at
        Assert.assertEquals(expectedDataMap.get("statusCode"),response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("completed"), actualDataMap.get("completed"));
        Assert.assertEquals(expectedDataMap.get("userId"), actualDataMap.get("userId"));
        Assert.assertEquals(expectedDataMap.get("title"), actualDataMap.get("title"));
        Assert.assertEquals(expectedDataMap.get("Via"), response.getHeader("Via"));
        Assert.assertEquals(expectedDataMap.get("Server"),response.getHeader("Server"));
        Assert.assertEquals("application/json; charset=utf-8",response.contentType());

        System.out.println("======================");
        System.out.println("Actual Data");
        System.out.println(actualDataMap);
        //{id=2, completed=false, title=quis ut nam facilis et officia qui, userId=1}





    }
}
