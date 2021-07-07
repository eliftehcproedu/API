package com.techproed.day10;

import com.techproed.TestData.TestDataDummy;
import com.techproed.testBase.TestBaseDummy;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

public class PostRequest01 extends TestBaseDummy {

    //Get ile Post farki ;
    //GET: bilgiyi alir, var olan kayidi getirir, kayitta degisiklik olmaz, authorization yoktur
    //POST: yeni bilgi olusturur, yeni kayit yapar, authorization vardir..
    //postta yeni bir kayit olusturdugumuz icin authorization yaptik extra..diger butun asamlar get ile ayni aslinda
    /*
    http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
    "name":"Ahmet Aksoy",
           "salary":"1000",
           "age":"18",
           "profile_image": ""
}
gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
{
   "status": "success",
           "data": {
        “id”:…
   },
   "message": "Successfully! Record has been added."
}
olduğunu test edin
     */

    @Test
    public void test(){
        // url olusturma
        spec03 .pathParam("parametre","create");

        //expected data olusturmadan once post yaparken request body gonderdigi icin onu olusturmaliyiz

        TestDataDummy testData =new TestDataDummy();
        HashMap<String,Object> requestBodyMap = testData.setUpTestData3();
        System.out.println("requestBodyMap = " + requestBodyMap);
        //requestBodyMap = {profile_image=, name=Ahmet Aksoy, salary=1000, age=18}

        //expected data olusturma
        HashMap<String, Object> expectedDataMap = testData.setUpTestData4();
        System.out.println("expectedDataMap = " + expectedDataMap);
        //expectedDataMap = {message=Successfully! Record has been added., statusCode=200, status=success}


        //request olusturma
        // authorization -> belli bir database ya da server a ulasabilme yetkisi
        // yada prada yapilabilen islemleri belirler.
        // Kisiyi yetkilendiren ve sinirlandiran yapidir..bilgi degisikligi icin yetkidir..

        Response response = RestAssured.given().
                accept("application/json").
                spec(spec03).
                body(requestBodyMap).
                auth().
                basic("admin","password123").
                when().post("/{parametre}");

        response.prettyPrint();

        //de-serialization islemi
        HashMap<String, Object> actualDataMap =response.as(HashMap.class);

        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());

        Assert.assertEquals(expectedDataMap.get("status"), actualDataMap.get("success"));

        Assert.assertEquals(expectedDataMap.get("message"), actualDataMap.get("message"));


    }
}
