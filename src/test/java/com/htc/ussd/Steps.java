package com.htc.ussd;

import com.thoughtworks.gauge.Step;


import org.openqa.selenium.remote.DesiredCapabilities;
import java.nio.file.*;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.File;
import java.io.IOException;


public class Steps {

    private Api service = new Api();
    Configuration config = new Configuration();
    
    public Steps(){
        config.Driver();
    }

    @Step("Login a Servicio USSD con usuario: <username> y contrasena <password>")
    public void loginUssd(String username, String password) throws UnirestException, IOException{
        service.login(username, password);
    }

    

    @Step("Servicio USSD <ussdNumber>")
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

    @Step("Prueba 1")
    public void pruebaUno() throws Exception{
        service.formatMenu();
    }

}
