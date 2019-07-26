/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.htc.ussd;

import com.mashape.unirest.http.exceptions.UnirestException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import java.io.FileWriter;
import java.io.IOException;

public class Ussd extends Api {

    /**
     *
     */
    
    
    JSONArray jsonArray;
    WebDriver driver;
  

    public Ussd(String url_Ussd, String tokenSecurity) {
        this.url_ussd = url_Ussd;
        this.tokenSecurity = tokenSecurity;
    }

    public Ussd saldo(String option) throws UnirestException{
        Configuration config = new Configuration();
        try {
            Thread.sleep(8000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        WebElement Saldo = config.driver.findElement(By.name("Tu_saldo"));                                          
        String value = Saldo.getAttribute("id");
        String response = metodos.postMenu(value, url_ussd, tokenSecurity);
        System.out.println("Respuesta: "+response);
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
        
        return this;
    }

    public void salirDelMenu() throws UnirestException {
        Configuration config = new Configuration();
        try {
            Thread.sleep(8000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        WebElement Salir = config.driver.findElement(By.name("Salir"));                                          
        String value = Salir.getAttribute("id");
        String response = metodos.postMenu(value, url_ussd, tokenSecurity);
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
        try {
            Thread.sleep(10000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        config.driver.close();
        
       

    }
}
