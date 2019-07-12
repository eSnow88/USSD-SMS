/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htc.ussd;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author HTC
 */
public class Ussd extends Api{
    
    JSONArray jsonArray;
    
        public Ussd saldo(String option, String value) throws UnirestException {
        String response = metodos.post(value);
        response = response.replace("{\"MENU\":[\"", "[");
        response = response.replace("\"]}", "]");
        jsonArray = new JSONArray(response);
        for (int i = 0; i < jsonArray.length(); i++) {
            String objeto = jsonArray.optJSONObject(i).toString();
            JSONObject jsonObj = new JSONObject(objeto);

            if (jsonObj.get("option").toString().contains(option)) {
                System.out.println(jsonObj.get("option").toString());
            }

        }
        return this;
}
        
        public void salirDelMenu(JSONArray json) throws UnirestException {
        for (int i = 0; i < jsonArray.length(); i++) {
            String objeto = jsonArray.optJSONObject(i).toString();
            JSONObject jsonObj = new JSONObject(objeto);
            if (jsonObj.get("option").equals("Salir")) {
                String id = jsonObj.get("id").toString();
                metodos.post(id);
            }

        }
    }
}

