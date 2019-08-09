/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htc.ussd;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.WebDriver;


public class Ussd extends Api {

    public JSONArray jsonArray2;
    WebDriver driver;
    
    public Ussd(){

    }

    public Ussd(String url_Ussd, String tokenSecurity) {
        this.url_ussd = url_Ussd;
        this.tokenSecurity = tokenSecurity;
    }

    public JSONArray getMenu(){
        System.out.println(jsonArray2);
        return jsonArray2;
    }

    public Ussd selectOptionMenu(String option, JSONArray menu) throws UnirestException {
        String value;
        if(menu==null){
            menu=jsonArray2;
        }
        for (int i = 0; i < menu.length(); i++) {
            String optionObject = menu.optJSONObject(i).toString();
            JSONObject menuPrincipal = new JSONObject(optionObject);
            System.out.println(option);
            String optionMenu = menuPrincipal.get("option").toString();
            if (optionMenu.contains(option)) {
                value = menuPrincipal.get("id").toString();
                System.out.println(value);
                String response = metodos.postMenu(value, url_ussd, tokenSecurity);
                JSONArray arrayMenu = formatMenuToJSONArray(response);

                // print Menu JSON in Terminal
                System.out.println("USSD Menu:");
                System.out.println(arrayMenu);
                jsonArray2=null;
                jsonArray2=arrayMenu;
                formatMenuToHTML(response);
                try {
                    Thread.sleep(5*1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                
            }else{
                System.out.println("Error en if");
            }
        }
        

        return this;
    }

    public void exitMenu() throws UnirestException {
        String value;
        System.out.println(jsonArray2);
        for (int i = 0; i < jsonArray2.length(); i++) {
            String optionObject = jsonArray2.optJSONObject(i).toString();
            JSONObject menuPrincipal = new JSONObject(optionObject);
            if (menuPrincipal.get("option").equals("Salir")) {
                value = menuPrincipal.get("id").toString();
                String response = metodos.postMenu(value, url_ussd, tokenSecurity);
               // JSONArray arrayMenu = formatMenuToJSONArray(response);

                // print Menu JSON in Terminal
                System.out.println("USSD Menu:");
                //System.out.println(arrayMenu);

                formatMenuToHTML(response);
            }
        }
        

    }
}
