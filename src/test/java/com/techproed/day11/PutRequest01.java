package com.techproed.day11;

import com.techproed.TestData.TestDataJsonPlaceHolder;
import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class PutRequest01 extends TestBaseJsonPlaceHolder {
    // Put: var olan kaydin tamamini guncellemek icin kullanilir, authorization kullanilir
    // Patch :var olan kaydin bir kismini gunceller
    // POST ; Sifirdan yani yeni bir kayit olusturur
    /*
    https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde
   {
      "userId": 21,
      "title": "Wash the dishes",
      "completed": false
     }
    Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
    {
     "userId": 21,
     "title": "Wash the dishes",
     "completed": false,
     "id": 198
        }
     */

    @Test
    public void test(){
        //url olustur
        spec01.pathParams("parametre1", "todos",
        "parametre2", 198);

        //request data

        TestDataJsonPlaceHolder testData =new TestDataJsonPlaceHolder();
        JSONObject requestBodyJSONObject = testData.setupTestDataPut01();
        System.out.println("requestBodyJSONObject = " + requestBodyJSONObject);
        //requestBodyJSONObject = {"completed":false,"title":"Wash the dishes","userId":21}

        //expected
            //benim request body ile, request gonderdigim deki response dan donecek olan data ayni oldugu icin
            //asssertion yaparken kullanacagim expected datayi olusturmama gerek yok.
            // Assertion isleminde requestbody i kullanabilirim

        //request gonder
        
        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().basic("admin", "pasword123").
                body(requestBodyJSONObject.toString()).
                when().
                put("/{parametre1}/{parametre2}");
        response.prettyPrint();

        //JsonPath ile

        JsonPath json=response.jsonPath(); //response u json objesine attik
        //json.prettyPrint();  yazdirir

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(requestBodyJSONObject.getInt("userId"),json.getInt("userId"));
        Assert.assertEquals(requestBodyJSONObject.getString("title"),json.getString("title"));
        Assert.assertEquals(requestBodyJSONObject.getBoolean("completed"),json.getBoolean("completed"));





    }


























}
