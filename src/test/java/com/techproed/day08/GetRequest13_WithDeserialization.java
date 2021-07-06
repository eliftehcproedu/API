package com.techproed.day08;

import com.techproed.TestData.TestDataDummy;
import com.techproed.testBase.TestBaseDummy;
import groovy.lang.DelegatesTo;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest13_WithDeserialization extends TestBaseDummy {
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
    public void test(){
        spec03.pathParam("parametre1","employees");
        TestDataDummy testData = new TestDataDummy();
        HashMap<String, Object> expectedDataMap = testData.setUpTestData();
        System.out.println("expectedDataMap :" +expectedDataMap);
        //expectedDataMap :{besinciCalisan=Airi Satou, calisanSayisi=24, employee11={profile_image=, employee_name=Jena Gaines,
        // employee_salary=90560, id=11, employee_age=30}, yasListesi=[40, 21, 19], statusCode=200, istenenMaas=106450}

        Response response=given().accept("application/json").
                spec(spec03).when().get("/{parametre1}");

        response.prettyPrint();

    //Deserializateion islemi

        HashMap<String,Object>actualDataMap =response.as(HashMap.class);
        System.out.println("actualDataMap :" + actualDataMap);
        //actualDataMap :{data=[{id=1, employee_name=Tiger Nixon, employee_salary=320800, employee_age=61, profile_image=},
        // {id=2, employee_name=Garrett Winters, employee_salary=170750, employee_age=63, profile_image=},....ciktisi uzun distan map map in ilk elemaninda liste var

    //Assertion islemi
        Assert.assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        
        Assert.assertEquals( expectedDataMap.get("besinciCalisan"),
                ((Map) ((List)actualDataMap.get("data")).get(4)).get("employee_name"));
        // en dista map var, map e .get ile ulasirim map in icinde ilk key ->data.. datanin kendisi list ..
        // datadan sonra liste ulasmak icin .get getirmiyor  getirmek icin basina data casting (List) yapiyoruz..
        // artik liste icine girdik..listeden index -> get(4) ile besinci kisiyi aldik..
        // Ancak 4. kisinin bilgileri key value.. map yani..
        // bu seferde List->map e gecmek icin data casting yapiyoruz basina ((Map) koyarak..
        // 4. kisinin ismini istiyoruz.. map de get ile  .get("employee_name") seklinde bilgiyi aliyoruz..

        Assert.assertEquals(expectedDataMap.get("calisanSayisi"),
                ((List) actualDataMap.get("data")).size());

        int dataSize = ((List<?>) actualDataMap.get("data")).size();
        Assert.assertEquals(expectedDataMap.get("istenenMaas"),
                ((Map) ((List<?>) actualDataMap.get("data")).get(dataSize-2)).get("employee_salary")); //id si 23 olanin

        // Map in size :3-> data, message, status
        // Listemiz datanin value lari -> size :24
        
        List<Integer> actualYasListesi = new ArrayList<>();

        for (int i = 0; i <dataSize ; i++) {
          actualYasListesi.add( (Integer) ((Map) ((List) actualDataMap.get("data")).get(i)).get("employee_age"));
        }
        //HashMap<String,Object>actualDataMap oldugu icin  data casting e ihtiyac duyuldu  (Integer) yaptik ..

        Assert.assertTrue(actualYasListesi.containsAll((List)expectedDataMap.get("yasListesi")));


        Assert.assertEquals(
                ((Map) expectedDataMap.get("employee11")).get("id"),
                ((Map) ((List)actualDataMap.get("data")).get(10)).get("id")
        );

        Assert.assertEquals(
                ((Map) expectedDataMap.get("employee11")).get("profile_image"),
                ((Map) ((List)actualDataMap.get("data")).get(10)).get("profile_image")
        );

        Assert.assertEquals(
                ((Map) expectedDataMap.get("employee11")).get("employee_name"),
                ((Map) ((List)actualDataMap.get("data")).get(10)).get("employee_name")
        );

        Assert.assertEquals(
                ((Map) expectedDataMap.get("employee11")).get("employee_salary"),
                ((Map) ((List)actualDataMap.get("data")).get(10)).get("employee_salary")
        );

        Assert.assertEquals(
                ((Map) expectedDataMap.get("employee11")).get("employee_age"),
                ((Map) ((List)actualDataMap.get("data")).get(10)).get("employee_age")
        );

    }

}
