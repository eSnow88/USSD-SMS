package com.htc.ussd;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UnirestMethods{
    public String url_ussd = "http://192.168.1.36:8081/api/v1.0/ussds";
    public String url_sms = "http://192.168.1.36:8081/api/v1.0/sms/send";

    public String post(String firstParameter) throws UnirestException {
        HttpResponse<String> httpResponse = Unirest.post(url_ussd)
                .header("Content-Type", "application/json")
                .body("{\"textsent\":\"" + firstParameter + "\"}")
                .asString();
        String response = httpResponse.getBody();
        System.out.println(response);

        return response;
    }

    public String post(String parameter, String parameter2) throws UnirestException {
        HttpResponse<String> httpResponse = Unirest.post(url_sms)
                .header("Content-Type", "application/json")
                .body("{\"phoneNumber\":\""+parameter+"\",\"textMessage\":\""+parameter2+"\"}")
                .asString();
        String response = httpResponse.getBody();

        return response;
    }
}
