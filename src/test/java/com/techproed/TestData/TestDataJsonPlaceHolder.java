package com.techproed.TestData;

import org.junit.Test;

import java.util.HashMap;

public class TestDataJsonPlaceHolder {

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
}
