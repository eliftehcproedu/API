package com.techproed.day11;

import com.techproed.TestData.TestDataJsonPlaceHolder;
import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequest03 extends TestBaseJsonPlaceHolder {  //url e ulasmak icin TestBase
    /*
    https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
     }
     "userId": 55,
     "title": "Tidy your room",
     "completed": false
   }
    Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
   {
     "userId": 55,
     "title": "Tidy your room",
     "completed": false,
     "id": …
    }
     */

    @Test
    public void test() {

        //url olustur....

        spec01.pathParam("parametre", "todos");

        //requestBody i olustur... requestbody ile expecteddatam ayni oldugu icin expectedbody olusturmaya gerek yok

        TestDataJsonPlaceHolder testData = new TestDataJsonPlaceHolder();
            //TestDataJsonPlaceHolder classindan obje ureterek o class daki methodlara ulasiyoruz

        JSONObject requestBodyJSONObject = testData.setUpTestDataPost2();
        System.out.println(requestBodyJSONObject); //{"completed":false,"title":"Tidy your room","userId":55}

        //request gonder
        Response response = given().
                contentType(ContentType.JSON). //diger turlu hata verebilir
                spec(spec01).
                auth().basic("admin", "password123").
                body(requestBodyJSONObject.toString()).
                when().post("/{parametre}");

        response.prettyPrint();  //sonucu ekrana yazdirir

        //De- seraialization---Gson () : bu kutuphane ile deserialization islemi yapabiliyoruz..Deserialization= Gson yontemi de deniliyor

        HashMap<String,Object> actualDatamap=response.as(HashMap.class); //Response dan gelen datayi actualDataMAp e koyuyoruz
        System.out.println("actualDatamap = " + actualDatamap); //actualDatamap = {completed=false, id=201, title=Tidy your room, userId=55}

        Assert.assertEquals(testData.statusCode,response.getStatusCode());
        Assert.assertEquals(requestBodyJSONObject.getInt("userId"),actualDatamap.get("userId"));
        Assert.assertEquals(requestBodyJSONObject.getString("title"),actualDatamap.get("title"));
        Assert.assertEquals(requestBodyJSONObject.getBoolean("completed"),actualDatamap.get("completed"));

        //JsonPath

        JsonPath json=response.jsonPath(); //response dan gelen datayi json objesine attik
        Assert.assertEquals(testData.statusCode,response.getStatusCode());
        Assert.assertEquals(requestBodyJSONObject.getInt("userId"),json.getInt("userId"));
        Assert.assertEquals(requestBodyJSONObject.getString("title"),json.getString("title"));
        Assert.assertEquals(requestBodyJSONObject.getBoolean("completed"),json.getBoolean("completed"));

        //Matchers class

        response.then().assertThat().statusCode(testData.statusCode).
                body("userId",equalTo(requestBodyJSONObject.getInt("userId")),
                "title", equalTo(requestBodyJSONObject.getString("title")),
                "completed", equalTo(requestBodyJSONObject.getBoolean("completed")));


    }

}
