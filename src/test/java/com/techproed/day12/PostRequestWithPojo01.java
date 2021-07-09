package com.techproed.day12;

import com.techproed.pojos.TodosPojo;
import com.techproed.testBase.TestBaseJsonPlaceHolder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.Request;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequestWithPojo01 extends TestBaseJsonPlaceHolder {
    // POJO : Plain Old Java Object : Basit Eski Java Objesi
    // POJO lar encapsulation(data saklama) yontemiyle olusturulur
    // UI testinde Driver i olustururken polymorphisim
    // basit manada olusturacagimiz expectedData, requestBody ve actualDatalar icin birer kaliptir

    //pojo classlar nasil olusturulur?
        //1.json objectten gelen tum keyler private bir degisken olarak tanimlanir
    /*
    https://jsonplaceholder.typicode.com/todos
    Request body  {
                      "userId": 21,
                      "id": 201,
                      "title": "Tidy your room",
                      "completed": false
                    }
   Status code is 201
    response body {
                      "userId": 21,
                      "id": 201,
                      "title": "Tidy your room",
                      "completed": false
                    }
     */
        //2.Her degisken icin getter ve setter
        //3.Parametresiz constructor olusturulur
        //4.Parametreli constructor olusturulur
        //5.toString() olusturulur

    @Test
    public void test(){
        //url olustur
        spec01.pathParam("parametre","todos");

        //requestBody olustur
        TodosPojo todos=new TodosPojo(21,201,"Tidy your room",false);
        //DataType                //Constructor- 4 parametreyi buraya girebiliriz

        System.out.println("todos = " + todos);
        //todos = TodosPojo{userId=21, id=201, title='Tidy your room', completed=false}

        //request gonder
        Response response=given().
                contentType(ContentType.JSON).
                spec(spec01).
                auth().
                basic("admin", "password123").
                body(todos).  //parametreleri todos a attik o yuzden body e todos u gonderdik
                when().
                post("/{parametre}");

        response.prettyPrint();

        //De-serialization(Gson)_------POJO
        TodosPojo actualData=response.as(TodosPojo.class);//response dan gelen cevabi TodosPojo daki sekliyle ctualDataya aktar

        Assert.assertEquals(201,response.getStatusCode());
        Assert.assertEquals(todos.getId(),actualData.getId());
        Assert.assertEquals(todos.getUserId(),actualData.getUserId());
        Assert.assertEquals(todos.getTitle(),actualData.getTitle());
        Assert.assertEquals(todos.isCompleted(),actualData.isCompleted());

        // JsonPath----POJO
        JsonPath json=response.jsonPath();
        Assert.assertEquals(todos.getId(),json.getInt("id"));
        Assert.assertEquals(todos.getUserId(),json.getInt("userId"));
        Assert.assertEquals(todos.getTitle(),json.getString("title"));
        Assert.assertEquals(todos.isCompleted(),json.getBoolean("completed"));

        //Matchers class
        response.
                then().
                assertThat().
                body("userId", equalTo(todos.getUserId()),
                        "id",equalTo(todos.getId()),
                        "title", equalTo(todos.getTitle()),
                        "completed", equalTo(todos.isCompleted()));

    }

}
