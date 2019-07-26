package com.htc.ussd;


import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UnirestMethods{
   public String token;


    public String post(String username, String password, String url) throws UnirestException {
        HttpResponse<String> httpResponse = Unirest.post(url)
        .header("Content-Type", "application/json")
        .body("{\"username\":\"" + username + "\",\"password\":\"" + password + "\"}")
        .asString();
        token = httpResponse.getBody();
        token = token.replace("{\"access_token\":\"", "");
        token = token.replace("\"}", "");
        System.out.println("El token de seguridad es: "+token);
        return token;
    }

    public String postMenu(String value, String url, String tokenSecurity) throws UnirestException {
        System.out.println("Imprimiendo Token en Unirest: "+tokenSecurity);
        HttpResponse<String> httpResponse = Unirest.post(url)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer "+tokenSecurity)
                .body("{\"textsent\":\"" + value + "\"}")
                .asString();
        String response = httpResponse.getBody();
        return response;
    }

    public String postWithToken(String valueUssd, String url, String tokenSecurity) throws UnirestException {
        HttpResponse<String> httpResponse = Unirest.post(url)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer "+tokenSecurity)
                .body("{\"textsent\":\"" + valueUssd + "\"}")
                .asString();
        String response = httpResponse.getBody();
        System.out.println(response);

        return response;
    }

    public String post(String parameter, String parameter2) throws UnirestException {
        HttpResponse<String> httpResponse = Unirest.post("")
                .header("Content-Type", "application/json")
                .body("{\"phoneNumber\":\""+parameter+"\",\"textMessage\":\""+parameter2+"\"}")
                .asString();
        String response = httpResponse.getBody();

        return response;
    }
}
