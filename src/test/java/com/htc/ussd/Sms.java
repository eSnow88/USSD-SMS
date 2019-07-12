/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htc.ussd;

import com.mashape.unirest.http.exceptions.UnirestException;

/**
 *
 * @author HTC
 */
public class Sms extends Api{
    UnirestMethods metodos = new UnirestMethods();
    
    public Sms envioSmS(String phoneNumber, String textMessage) throws UnirestException{
        String response=metodos.post(phoneNumber, textMessage);
        response = response.replace("{\"sentMessage\":[\"", "");
        response = response.replace(",\"\"]}", "");
        response = response.replace("\\", "");
        response = response.replace("\"", "");
        response = response.replace(",,", ",");
        System.out.println(response);
        String[] parts = response.split(",");
        for (int i = 0; i < parts.length; i++) {
            System.out.println(parts[i]);
        }
    return this;
    }
}
