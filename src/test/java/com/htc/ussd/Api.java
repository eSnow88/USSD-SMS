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
import com.htc.ussd.Ussd;

/**
 *
 * @author HTC
 */
public class Api {
    
    public String id;
    JSONArray jsonArray;
    UnirestMethods metodos = new UnirestMethods();
    
    
    public String principalMenuUssd(String value) throws UnirestException {
        String response = metodos.post(value);
        response = response.replace("{\"MENU\":[\"", "[");
        response = response.replace("\"]}", "]");
        System.out.println("Cadena para array: " + response);
        jsonArray = new JSONArray(response);
        for (int i = 0; i < jsonArray.length(); i++) {
            String objeto = jsonArray.optJSONObject(i).toString();
            JSONObject jsonObj = new JSONObject(objeto);

            if (jsonObj.get("option").equals("Tu saldo")) {
                id=jsonObj.get("id").toString();
            }
        }
        return id;
    }
    
    public Ussd goUssd(){
        return new Ussd();
    }
    
     public Sms goSms(){
        return new Sms();
    }
    
    public void formatMenu(){
        
    }
}