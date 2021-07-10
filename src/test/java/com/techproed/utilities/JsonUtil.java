package com.techproed.utilities;

import org.codehaus.jackson.map.ObjectMapper;

import java.io.IOException;

public class JsonUtil {

    //object mapper kullanarak de_serialization yapma yontemi
    private  static ObjectMapper mapper; //class ismi ile her yerden ulasilir
    static{  //ilk calisir
        mapper=new ObjectMapper();
    }
    //Methodumuzu olusturacagiz. Stringe cevrilmis json datasini java objesine donusturecek.
    //T class :
            // Genelde developerlarin kullandigi generic bir ifadedir, butun dataTypelari kapsar
            // Object ten farki ic ice bir yapisi olup olmadigina bakmasizin , ceviriyor
            // Cevirecegi uygun java objesini kendi belirler
    public static <T> T convertJsonToJava(String json,Class<T> cls){
        T javaResult= null;
        try {
            javaResult = mapper.readValue(json, cls); //methoddan gelen parametreleri mapper objesi araciligiyla gelen degerleri okur ve javaResult a aktariyor
        } catch (IOException e) {
            System.err.println("json datası javaya donusturulemedi"+e.getMessage());
        }
        return javaResult;
    }
}

// try catch : kodu handle eder ,  try catch reusable methodlarda kullanlilrher seferinde hata vermesin diye..
// throw : kodu handle eder , reusable methodlara uygun degil