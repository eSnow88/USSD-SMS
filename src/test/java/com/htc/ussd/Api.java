package com.htc.ussd;

import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;
import org.json.JSONArray;
import java.io.FileWriter;

import com.htc.ussd.Ussd;

public class Api {

    public String id;
    public JSONArray jsonArray;
    UnirestMethods metodos = new UnirestMethods();

    public String url_ussd;
    public String url_sms;
    public String url_auth;
    public String tokenSecurity;
    private String response;

    public Api() {
        url_ussd = "https://raspi.hightech-corp.com/api/v1.0/ussds";
        url_sms = "http://192.168.1.36:8081/api/v1.0/sms/send";
        url_auth = "https://raspi.hightech-corp.com/api/v1.0/auth";
    }

    public void login(String username, String password) throws UnirestException {
        tokenSecurity = metodos.post(username, password, url_auth);
    }

    public void principalMenuUssd(String value) throws UnirestException {
        String response = metodos.postWithToken(value, url_ussd, tokenSecurity);
        JSONArray arrayMenu = formatMenuToJSONArray(response);

        // print Menu JSON in Terminal
        System.out.println("USSD Menu Principal: ");
        System.out.println(arrayMenu);

        // This method send menu to HTML
        formatMenuToHTML(response);
        jsonArray = arrayMenu;
    }

    public Ussd goUssd() {
        return new Ussd(url_ussd, tokenSecurity);
    }

    public Sms goSms() {
        return new Sms(url_sms, tokenSecurity);
    }

    public String formatMenuToHTML(String response) {
        try {
            response = response.replace("\"MENU\":[\"", "\"table\":{\"rows\":[");
            response = response.replace("\"]}", "]}}");
            response = response.replace("'", "\"");
            response = response.replace("\\\"", "");
            response = response.replace("\"MENU\":\"{\"", "\"table\":{\"rows\":[{\"");
            response = response.replace( "}\"}","}]}}");
            response = response.replace( "id\":10","id\":\" \"");
            FileWriter file = new FileWriter("web\\db.json");
            file.write(response);
            file.flush();
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return response;
    }

    public JSONArray formatMenuToJSONArray(String response) {
        response = response.replace("{\"MENU\":[\"", "[");
        response = response.replace("\"]}", "]");
        response = response.replace("\\\"", "");
        JSONArray jsonArrayMenu = new JSONArray(response);
        return jsonArrayMenu;
    }

    public void close() {
        Configuration conf = new Configuration();
        try {
            FileWriter file = new FileWriter("web\\db.json");
            file.write("");
            file.flush();
            file.close();
        } catch (Exception e) {
            // TODO: handle exception
        }
        conf.closeNavigation();
    }
}
