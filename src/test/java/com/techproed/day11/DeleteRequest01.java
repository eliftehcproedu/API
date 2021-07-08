package com.techproed.day11;

import com.techproed.TestData.TestDataDummy;
import com.techproed.testBase.TestBaseDummy;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DeleteRequest01 extends TestBaseDummy {
/*
http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde

Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
 "status": "success",
 "data": "2",
 "message": "Successfully! Record has been deleted"
}
 */
    @Test
    public void test(){
        //url olustur
        spec03.pathParams("parametre1","delete","parametre2",2);

        //expected data olustur

        TestDataDummy testData=new TestDataDummy();
        JSONObject expectedData= testData.setUpDelete01();
        System.out.println("expectedData = " + expectedData);

        //request gonder
        Response response=given().
                spec(spec03).
                auth().
                basic("admin","password123").
                when().delete("/{parametre1}/{parametre2}");
                response.prettyPrint();

        response.then().
                assertThat().
                statusCode(expectedData.getInt("statusCode")).
                body("status", equalTo(expectedData.getString("status")),
                        "data",equalTo(expectedData.getString("data")),
                        "message",equalTo(expectedData.getString("message")));

    }
}
