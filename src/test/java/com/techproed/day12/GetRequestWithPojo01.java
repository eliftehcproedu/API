package com.techproed.day12;

import com.google.gson.Gson;
import com.techproed.pojos.DataPojo;
import com.techproed.pojos.EmployeePojo;
import com.techproed.testBase.TestBaseDummy;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequestWithPojo01 extends TestBaseDummy{
    /*  https://www.jsonschema2pojo.org/  Generate Plain Old Java Objects from JSON or JSON-Schema.

   bu siteye request body girilerek istenilen veriler encapsule edilerek pojo class olusturabiliriz
   en alltta Preview tiklanip gorulebilir

    GET Request to the URL http://dummy.restapiexample.com/api/v1/employee/1
            Status code is 200
             {
                                  "status": "success",
                                  "data": {
                                      "id": 1,
                                      "employee_name": "Tiger Nixon",
                                      "employee_salary": 320800,
                                      "employee_age": 61,
                                      "profile_image": ""
                                  },
                                  "message": "Successfully! Record has been fetched."
                                 }
}
     */

    @Test
    public void test() {
        //url olustur
        spec03.pathParams("parametre1", "employee", "parametre2", 1);

        //expected Data olustur
        //Pojo ile expectedData kalibi olustur
        //en ic katmandan en dis katmana dogru tanimlaniyor

        DataPojo data= new DataPojo(1,"Tiger Nixon",320800,61,"");
        EmployeePojo expectedData=new EmployeePojo("success",data,"Successfully! Record has been fetched.");
        System.out.println("EmployeePojodan gelen expectedData = " + expectedData);
        //employeePojo benim expected datam

        //request gonder

        Response response=given().
                contentType(ContentType.JSON).
                spec(spec03).
                when().
                get("/{parametre1}/{parametre2}");

        response.prettyPrint();

        //actualDatayi employeePojo kalibindan olusturabiliriz

        EmployeePojo actualData=response.as(EmployeePojo.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedData.getStatus(),actualData.getStatus());
        Assert.assertEquals(expectedData.getData().getId(),actualData.getData().getId());
        Assert.assertEquals(expectedData.getData().getEmployee_name(),actualData.getData().getEmployee_name());
        Assert.assertEquals(expectedData.getData().getEmployee_salary(),actualData.getData().getEmployee_salary());
        Assert.assertEquals(expectedData.getData().getEmployee_age(),actualData.getData().getEmployee_age());
        Assert.assertEquals(expectedData.getData().getProfile_image(),actualData.getData().getProfile_image());
        Assert.assertEquals(expectedData.getMessage(),actualData.getMessage());


        //Serialization islemi ile java objesini json objesine ceviririm
        // Gson sinifinden  bir oble uretecegim

        Gson gson=new Gson();
        String jsobFromJava=gson.toJson(actualData);
        //urettigim obje uzerinden toJson methodunu kullandim
        System.out.println("jsobFromJava = " + jsobFromJava);


/*  actualData = EmployeePojo{status='success', data=DataPojo{id=1, employee_name='Tiger Nixon', employee_salary=320800,
    employee_age=61, profile_image=''}, message='Successfully! Record has been fetched.'}

    jsobFromJava = {"status":"success","data":{"id":1,"employee_name":"Tiger Nixon","employee_salary":320800,
    "employee_age":61,"profile_image":""},"message":"Successfully! Record has been fetched."}

*/


    }
}
