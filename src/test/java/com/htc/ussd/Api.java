/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htc.ussd;

import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.io.FileWriter;

import com.htc.ussd.Ussd;

/**
 *
 * @author HTC
 */
public class Api {

    public String id;
    JSONArray jsonArray;
    UnirestMethods metodos = new UnirestMethods();
   
    public String url_ussd;
    public String url_sms;
    public String url_auth;
    public String tokenSecurity;

    public Api() {
        url_ussd = "https://raspi.hightech-corp.com/api/v1.0/ussds";
        url_sms = "http://192.168.1.36:8081/api/v1.0/sms/send";
        url_auth = "https://raspi.hightech-corp.com/api/v1.0/auth";
    }


    public void login(String username, String password) throws UnirestException {
        tokenSecurity = metodos.post(username, password, url_auth);
    }

    public String principalMenuUssd(String value) throws UnirestException {
        String response = metodos.postWithToken(value, url_ussd, tokenSecurity);
        response = response.replace("{\"MENU\":[\"", "[");
        response = response.replace("\"]}", "]");
        System.out.println("Cadena para array: " + response);
        jsonArray = new JSONArray(response);
        for (int i = 0; i < jsonArray.length(); i++) {
            String objeto = jsonArray.optJSONObject(i).toString();
            JSONObject jsonObj = new JSONObject(objeto);

            if (jsonObj.get("option").equals("Tu saldo")) {
                id = jsonObj.get("id").toString();
            }
        }
        return id;
    }

    public void principalMenuUssd2(String value) throws UnirestException {
        String response = metodos.postWithToken(value, url_ussd, tokenSecurity);
        response = response.replace("\"MENU\":[\"", "\"table\":{\"rows\":[");
        response = response.replace("\"]}", "]}}");
        response = response.replace("'","\"");
        try{
        FileWriter file = new FileWriter("web\\db.json");
			file.write(response);
			file.flush();
            file.close();
        } catch (IOException e){
            e.printStackTrace();
        }
       
    }

    public Ussd goUssd() {
        return new Ussd(url_ussd, tokenSecurity);
    }

    public Sms goSms() {
        return new Sms();
    }

    public void formatMenu() {

    }
}
