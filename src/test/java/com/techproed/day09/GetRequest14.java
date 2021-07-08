package com.techproed.day09;

import com.techproed.TestData.TestDataDummy;
import com.techproed.testBase.TestBaseDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class GetRequest14 extends TestBaseDummy {
    /*
    http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
En yüksek maaşın 725000 olduğunu,
En küçük yaşın 19 olduğunu,
İkinci en yüksek maaşın 675000
olduğunu test edin.
     */
    @Test
    public void test() {
        //endpoint olustur
        spec03.pathParam("parametre1", "employees");

        //expected datayi olustur
        TestDataDummy testData = new TestDataDummy();

        HashMap<String, Integer> expectedDataMap = testData.setupTestData2();
        System.out.println("expectedDataMap :" + expectedDataMap);
        //expectedDataMap :{enKucukYas=19, iknciEnYuksekMaas=675000, statusCode=200, enYuksekMaas=725000}

        Response response = given().accept("application/json").
                spec(spec03).when().get("/{parametre1}");

        response.prettyPrint();

        // De-Serialization islemi

        HashMap<String,Object>actualDataMap=response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);
        //actualDataMap = {data=[{id=1, employee_name=Tiger Nixon, employee_salary=320800, employee_age=61, profile_image=},..ciktisi uzun
        Assert.assertEquals(expectedDataMap.get("statusCode"),(Integer) response.getStatusCode());

        //En yüksek maaşın 725000 olduğunu,
        //once maaslardan olusan bir list olusturmaliyiz
        List<Integer> actualMaasList =new ArrayList<Integer>();

        //actualDataMap den donen datanin uzunlugunu (size) bulmaliyiz
        int dataSize = ((List) actualDataMap.get("data")).size();

        //tum employeelerin maaslarini liste aktariyoruz..
        for (int i = 0; i < dataSize; i++) {
            actualMaasList.add((Integer) ((Map) ((List) actualDataMap.get("data")).get(i)).get("employee_salary"));
        }

        Collections.sort(actualMaasList); //sort() kucuktem buyuge dogru siralar
        Assert.assertEquals(expectedDataMap.get("enYuksekMaas"), actualMaasList.get(actualMaasList.size()-1));

        //İkinci en yüksek maaşın 675000
        Assert.assertEquals(expectedDataMap.get("iknciEnYuksekMaas"), actualMaasList.get(actualMaasList.size()-2));

        //En küçük yaşın 19 olduğunu,

        List<Integer>actualYasList =new ArrayList<Integer>();

        for (int i = 0; i < dataSize; i++) {
            actualYasList.add((Integer) ((Map) ((List<?>) actualDataMap.get("data")).get(i)).get("employee_age"));
        }

        Collections.sort(actualYasList);
        Assert.assertEquals(expectedDataMap.get("enKucukYas"), actualYasList.get(0));



        ////========JsonPath Yontemi ile=========

        JsonPath jsonPath=response.jsonPath();

        Assert.assertEquals(expectedDataMap.get("statusCode"),(Integer) response.getStatusCode());
                                        //Wrapper Integer                 //int
        //StatusCode body de degil o yuzden response da yer alir bu yuzden response dan alarak islem yapilir

        List<Integer>jsonYasListesi=jsonPath.getList("data.employee_age");
        Collections.sort(jsonYasListesi);
        Assert.assertEquals(expectedDataMap.get("enKucukYas"),jsonYasListesi.get(0));

        List<Integer>jsonMaasListesi=jsonPath.getList("data.employee_salary");
        Collections.sort(jsonMaasListesi);
        Assert.assertEquals(expectedDataMap.get("enYuksekMaas"),jsonMaasListesi.get(jsonMaasListesi.size()-1)); //23.index 24.elemanin maasi

        Assert.assertEquals(expectedDataMap.get("iknciEnYuksekMaas"),jsonMaasListesi.get(jsonMaasListesi.size()-2));//22.index 23.elemanin maasi


    }

}
