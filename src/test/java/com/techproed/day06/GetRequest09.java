package com.techproed.day06;

import com.techproed.testBase.TestBaseDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest09 extends TestBaseDummy {

/*
http://dummy.restapiexample.com/api/v1/employees
url ine bir istek gönderildiğinde,
status kodun 200,
gelen body de,
5. çalışanın isminin "Airi Satou" olduğunu ,
6. çalışanın maaşının "372000" olduğunu ,
Toplam 24 tane çalışan olduğunu,
"Rhona Davidson" ın employee lerden biri olduğunu
"21", "23", "61" yaşlarında employeeler olduğunu test edin
 */

    @Test
    public void test(){


        spec03.pathParam("parametre", "employees");
        Response response=given().accept("application/json").
                spec(spec03).when().get("/{parametre}");

        response.prettyPrint();

        Assert.assertEquals(200,response.getStatusCode()); //passed

        JsonPath json=response.jsonPath();

        Assert.assertEquals("Airi Satou", json.getString("data.employee_name[4]")); //passed

        Assert.assertEquals(372000, json.getInt("data.employee_salary[5]")); //passed

        //System.out.println("toplam calisan sayisi :" +json.getList("data.id").size()); //toplam calisan sayisi :24
        Assert.assertEquals(24,json.getList("data.id").size() ); //passed

        Assert.assertTrue(json.getList("data.employee_name").contains("Rhona Davidson"));  //passed

        Assert.assertTrue(json.getList("data.employee_age").contains(21));
        Assert.assertTrue(json.getList("data.employee_age").contains(23));
        Assert.assertTrue(json.getList("data.employee_age").contains(61));
        //yukaridaki 3 satir yerine;

        List<Integer> yasList =new ArrayList<Integer>();
        yasList.add(21);
        yasList.add(23);
        yasList.add(61);
        System.out.println(yasList); //[21,23,61]
        Assert.assertTrue(json.getList("data.employee_age").containsAll(yasList)); //passed


    }
}
