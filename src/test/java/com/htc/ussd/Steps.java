package com.htc.ussd;

import com.thoughtworks.gauge.Step;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;


public class Steps {

    private Api service = new Api();
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
       service.principalMenuUssd2(ussdNumber);
    }

    @Step("consulta de <option> en USSD")
    public void metodoPruebaUssd(String option) throws UnirestException, IOException {
        service.goUssd().saldo(option).salirDelMenu();

    }

    @Step("Consulta de Saldo en SMS a <phoneNumber> con la palabra <textMessage>")
    public void metodoPruebaSms(String number, String text) throws UnirestException, IOException {
        service.goSms().envioSmS(number, text);
    }

    @Step("Close Service")
    public void pruebaUno() throws Exception{
       
    }

}
