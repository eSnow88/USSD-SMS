package com.htc.ussd;

import com.thoughtworks.gauge.Step;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;


public class Steps{
    
    private Api service = new Api();
    Ussd ussd = new Ussd();
    

    Configuration config = new Configuration();
    
    public Steps(){
        config.Driver();
    }

    @Step("login in ussd service user: <user> and pass: <pass>")
    public void loginUssd(String username, String password) throws UnirestException, IOException{
        service.login(username, password);
    }

    @Step("See Principal Menu in <ussd>")
    public void menuPrincipal(String ussdNumber) throws UnirestException, IOException {
       service.principalMenuUssd(ussdNumber);
    }

    @Step("consulta de <option> en USSD")
    public void metodoPruebaUssd(String option) throws UnirestException, IOException {
        service.goUssd().selectOptionMenu("Tu saldo", service.jsonArray).selectOptionMenu("Saldo Principal", ussd.jsonArray2).exitMenu();
    }

    @Step("Consulta de Saldo en SMS a <phoneNumber> con la palabra <textMessage>")
    public void metodoPruebaSms(String number, String text) throws UnirestException, IOException {
        service.goSms().envioSmS(number, text);
    }

    @Step("Close Service")
    public void closeService() throws Exception{
       service.close();
    }

}
