package com.techproed.day06;

import com.techproed.testBase.TestBaseDummy;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest08 extends TestBaseDummy {
    /*
    http://dummy.restapiexample.com/api/v1/employees url’inde bulunan
   1) Butun calisanlarin isimlerini consola yazdıralim
   2) 3. calisan kisinin ismini konsola yazdıralim
   3) Ilk 5 calisanin adini konsola yazdiralim
   4) En son calisanin adini konsola yazdiralim

   !!!!ContentType.JSON  = "application/json" ile ayni... ancak ContentType.JSON yazmakkodu dinamik yapara tecih edilmeli ..
   ama "I am a tea pot" hatasi veriyor garanti olan "application/json" dir.
     */

    @Test
    public void test(){

       spec03.pathParam("parametre","employees");
        Response response=given().accept("application/json").
                spec(spec03).when().get("/{parametre}");

        response.prettyPrint();

        // 1) Butun calisanlarin isimlerini consola yazdıralim

        JsonPath json=response.jsonPath();
        //System.out.println(json.getString("data.employee_name")); yerine
        System.out.println(json.getList("data.employee_name")); //de yazilabilir..ikisi liste donduruyor
        /*
        [Tiger Nixon, Garrett Winters, Ashton Cox, Cedric Kelly, Airi Satou,
        Brielle Williamson, Herrod Chandler, Rhona Davidson, Colleen Hurst,
        Sonya Frost, Jena Gaines, Quinn Flynn, Charde Marshall, Haley Kennedy,
        Tatyana Fitzpatrick, Michael Silva, Paul Byrd, Gloria Little, Bradley Greer,
        Dai Rios, Jenette Caldwell, Yuri Berry, Caesar Vance, Doris Wilder]  butun isimleri veriyor..
         */
        //response daki tum datalari

        //2) 3. calisan kisinin ismini konsola yazdıralim

        System.out.println("3.calisan kisinin ismi :"+ json.getString("data.employee_name[2]"));
            //3.calisan kisinin ismi :Ashton Cox

        //3) Ilk 5 calisanin adini konsola yazdiralim

        System.out.println("Ilk 5 calisanin adi :" + json.getString("data.employee_name[0,1,2,3,4,5]"));
            //Ilk 5 calisanin adi :[Tiger Nixon, Garrett Winters, Ashton Cox, Cedric Kelly, Airi Satou, Brielle Williamson]

        //4) En son calisanin adini konsola yazdiralim

        System.out.println("En son calisanin adi :" + json.getString("data.employee_name[-1]"));
            //En son calisanin adi :Doris Wilder

    }


}
