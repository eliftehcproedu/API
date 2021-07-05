package com.techproed.TestData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TestDataDummy {
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

    public HashMap<String,Object> setUpTestData(){
        HashMap<String,Object> expectedDataMap =new HashMap<String,Object>();

        //yaslari liste yaptik cunku sadece yas bilgisi var key value ya uygun veri olmadigi icin liste yaptik...
        List<Integer> yaslar = new ArrayList<Integer>();
        yaslar.add(40);
        yaslar.add(21);
        yaslar.add(19);

        HashMap<String,Object> onbirinci = new HashMap<String, Object>();
        onbirinci.put("id",11);
        onbirinci.put("employee_name","Jena Gaines");
        onbirinci.put("employee_salary",90560);
        onbirinci.put("employee_age",30);
        onbirinci.put("profile_image","");

        expectedDataMap.put("yasListesi", yaslar);
        expectedDataMap.put("employee11", onbirinci);
        expectedDataMap.put("statusCode", 200);
        expectedDataMap.put("besinciCalisan", "Airi Satou");
        expectedDataMap.put("calisanSayisi", 24);
        expectedDataMap.put("istenenMaas", 106450);

        return expectedDataMap;

    }
}
