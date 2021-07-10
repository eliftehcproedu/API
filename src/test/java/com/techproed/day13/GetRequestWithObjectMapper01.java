package com.techproed.day13;

import com.techproed.testBase.TestBaseJsonPlaceHolder;
import com.techproed.utilities.JsonUtil;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequestWithObjectMapper01 extends TestBaseJsonPlaceHolder {
    //ObjectMapper : Json datasini java objelerine cevirme islemidir..
    //Cevirecegi uygun java objesini kendi belirler
    //De-serialization yapar
    //toJson methodu ile
    //Json datasini java objelerine cevirmek icin utilities package altinda reusable methodlarin oldugu bir class gereklidir.

    /*
    https://jsonplaceholder.typicode.com/todos/198 url’ine bir get request gönderildiğinde,
    Dönen response ‘un status kodunun 200 ve body kısmının
    {
       "userId": 10,
       "id": 198,
       "title": "quis eius est sint explicabo",
       "completed": true
    }
    Olduğunu Object Mapper kullanarak test edin
         */

    
    
    @Test
    
    public void test(){

        //url olustururuz
        spec01.pathParams("parametre1","todos","parametre2",198);
        
        //expect Data
        //----ilk olarak json ifademi bir Stirnge atayalim
        String jsonData="{\n" +
                "       \"userId\": 10,\n" +
                "       \"id\": 198,\n" +
                "       \"title\": \"quis eius est sint explicabo\",\n" +
                "       \"completed\": true\n" +
                "    }";
        
        //---ikinci olarak neye cevirmesini istiyorsak type belirliyecegim ORN map cevirsin
        //object Mapper yontemi De-serialization islemi yapar
        Map<String,Object> expectedDataMap= JsonUtil.convertJsonToJava(jsonData,Map.class);
        System.out.println("expectedDataMap = " + expectedDataMap);
            //expectedDataMap = {userId=10, id=198, title=quis eius est sint explicabo, completed=true}

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).
                with().get("/{parametre1}/{parametre2}");
        response.prettyPrint();

        Map<String,Object> actualDataMap=JsonUtil.convertJsonToJava(response.asString(),Map.class);
        //convertJsonToJava() methodu String kabul eder, response String formatta degildir
        //response u String formatta gondermek icin response.asString() methodunu kullandik..

        System.out.println("actualDataMap = " + actualDataMap);
        //actualDataMap = {userId=10, id=198, title=quis eius est sint explicabo, completed=true}

        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"));
        Assert.assertEquals(expectedDataMap.get("id"),actualDataMap.get("id"));
        Assert.assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));
        Assert.assertEquals(expectedDataMap.get("completed"),actualDataMap.get("completed"));






    }
}
