package com.techproed.day06;

import com.techproed.testBase.TestBaseDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class GetRequest10 extends TestBaseDummy {
/*
    http://dummy.restapiexample.com/api/v1/employees
    url ine bir istek gönderildiğinde
    Dönen response un
     Status kodunun 200,
     1)10’dan büyük tüm id’leri ekrana yazdırın ve
    10’dan büyük 14 id olduğunu,
     2)30’dan küçük tüm yaşları ekrana yazdırın ve
      bu yaşların içerisinde en büyük yaşın 23 olduğunu
     3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
      bunların içerisinde “Charde Marshall” olduğunu test edin
 */
    @Test
    public void test(){

        spec03.pathParam("parametre", "employees");
        Response response=given().accept("application/json").
                spec(spec03).when().get("/{parametre}");

        response.prettyPrint();

        JsonPath json = response.jsonPath();
        Assert.assertEquals(200, response.getStatusCode()); //passed

        //Groovy dili => Javanin alt dillerinden biridir
        //it ifadesi -> this gibi
        // findAll => Belirtilen data nin icerisinde istenen sarta bagli olan degerleri getirir.

        // 1)10’dan büyük tüm id’leri ekrana yazdırın ve 10’dan büyük 14 id olduğunu test edin

        List<Integer> idList= json.getList("data.findAll{it.id>10}.id");
        System.out.println(idList);//[11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24]

        Assert.assertEquals(14,idList.size());

        //2)30’dan küçük tüm yaşları ekrana yazdırın ve
        // bu yaşların içerisinde en büyük yaşın 23 olduğunu test edin

        List<Integer> ageList=json.getList("data.findAll{it.employee_age<30}.employee_age");
        System.out.println(ageList);//[22, 23, 22, 19, 21, 23]
        Collections.sort(ageList);
        Assert.assertEquals(Integer.valueOf("23"),ageList.get(ageList.size()-1));
        //datatype integer i gormedigi icin String yapip integer a cevirdik : Data Casting
            // Assert.assertEquals(23,(int) ageList.get(ageList.size()-1)); de olur


        //3)Maası 350000 den büyük olan tüm employee name’leri ekrana yazdırın ve
        //  bunların içerisinde “Charde Marshall” olduğunu test edin


        List<String>nameList=json.getList("data.findAll{it.employee_salary>350000}.employee_name");
        System.out.println(nameList);
        //[Cedric Kelly, Brielle Williamson, Charde Marshall, Tatyana Fitzpatrick, Paul Byrd, Yuri Berry]
        Assert.assertTrue(nameList.contains("Charde Marshall")); //passed

















    }
}


    /* 1.soru
        Lambda ile
        JsonPath js = response.jsonPath();
        List<Integer> list = js.getList("data.id");
        list.stream().filter(x-> x>10).forEach(x-> System.out.print(x + " ")); //11 12 13 14 15 16 17 18 19 20 21 22 23 24

        ya da
        JsonPath js = response.jsonPath();
        List<Integer> list = js.getList("data.id");
          for(int i=10;i<list.size();i++) {
        System.out.println(liste.get(i)); }
    */



