package com.techproed.TestData;

import org.json.JSONObject;

import java.util.HashMap;

public class TestDataJsonPlaceHolder {

    public int statusCode=201;  //statusCode u dinamik yapmak icin PostRequest03 sinifinda ulasmakicin obje ile cagirabiliriz

    public HashMap<String,Object> setUpTestData(){

    HashMap<String,Object> expectedDataMap=new HashMap<String,Object>();

        expectedDataMap.put("statusCode",200);
        expectedDataMap.put("completed",false);
        expectedDataMap.put("userId",1);
        expectedDataMap.put("title","quis ut nam facilis et officia qui");
        expectedDataMap.put("Via","1.1 vegur");
        expectedDataMap.put("Server","cloudflare");
        return expectedDataMap;
    }

    public JSONObject setUpTestDataPost2(){

        JSONObject requestBody= new JSONObject();
        //JSONObject classindan requestBody objesi olusturuyorum buna map gibi davrandik...
        // map yerine bunu kullandik neden ?type casting de sikinti yasamamak icin bunu tercih ettik
        requestBody.put("userId",55);
        requestBody.put("title","Tidy your room");
        requestBody.put("completed", false);

        return requestBody;

    }

    public JSONObject setupTestDataPut01(){
        JSONObject requestBody= new JSONObject();
        requestBody.put("userId",21);
        requestBody.put("title","Wash the dishes");
        requestBody.put("completed",false);

        return requestBody;

    }

    public JSONObject setUpDataPatch01(){
        JSONObject requestBody= new JSONObject();
        requestBody.put("title","API calismaliyim");

        return requestBody;
    }

    public JSONObject setUpexpectedBody(){
        JSONObject expectedBody=new JSONObject();
        expectedBody.put("userId",10);
        expectedBody.put("title","API calismaliyim");
        expectedBody.put("completed",true);

        return expectedBody;

    }
}
