package com.techproed.day09;

import com.techproed.TestData.TestDataDummy;
import com.techproed.testBase.TestBaseDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest13_WhitJsonPath extends TestBaseDummy {
     /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
    Status kodun 200 olduğunu,
    5. Çalışan isminin "Airi Satou" olduğunu ,  çalışan sayısının 24 olduğunu,
    Sondan 2. çalışanın maaşının 106450 olduğunu
    40,21 ve 19 yaslarında çalışanlar olup olmadığını
    11. Çalışan bilgilerinin
      {
     “id”:”11”
     "employee_name": "Jena Gaines",
    "employee_salary": 90560,
    "employee_age": 30,
    "profile_image": "" }
    }
    gibi olduğunu test edin.
     */

    @Test
    public void test() {
        spec03.pathParams("parametre1", "employees");
        TestDataDummy testData = new TestDataDummy();
        HashMap<String, Object> expectedDataMap = testData.setUpTestData();
        System.out.println("expectedDataMap :" + expectedDataMap);
        //expectedDataMap :{besinciCalisan=Airi Satou, calisanSayisi=24, employee11={profile_image=, employee_name=Jena Gaines,
        // employee_salary=90560, id=11, employee_age=30}, yasListesi=[40, 21, 19], statusCode=200, istenenMaas=106450}

        Response response = given().accept("application/json").
                spec(spec03).when().get("/{parametre1}");

        response.prettyPrint();

        JsonPath json= response.jsonPath();

        Assert.assertEquals(200, response.getStatusCode());
        Assert.assertEquals(expectedDataMap.get("besinciCalisan"), json.getString("data[4].employee_name"));
        Assert.assertEquals(expectedDataMap.get("calisanSayisi"), json.getList("data.id").size());
        Assert.assertEquals(expectedDataMap.get("istenenMaas"), json.getInt("data[-2].employee_salary"));
        Assert.assertTrue(json.getList("data.employee_age").containsAll(((List) expectedDataMap.get("yasListesi"))));

        Assert.assertEquals(
                ((Map) expectedDataMap.get("employee11")).get("id"), json.getInt("data[10].id"));
        Assert.assertEquals(
                ((Map) expectedDataMap.get("employee11")).get("employee_name"), json.getInt("data[10].employee_name"));
        Assert.assertEquals(
                ((Map) expectedDataMap.get("employee11")).get("employee_salary"), json.getInt("data[10].employee_salary"));
        Assert.assertEquals(
                ((Map) expectedDataMap.get("employee11")).get("employee_age"), json.getInt("data[10].employee_age"));
        Assert.assertEquals(
                ((Map) expectedDataMap.get("employee11")).get("profile_image"), json.getInt("data[10].profile_image"));

    }
}