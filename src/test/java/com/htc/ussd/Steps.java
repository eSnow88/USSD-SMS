package com.htc.ussd;

import com.thoughtworks.gauge.Step;
import com.mashape.unirest.http.exceptions.UnirestException;
import java.io.IOException;


public class Steps {

    private Api service = new Api();

    @Step("Servicio USSD <ussdNumber>")
    public void menuPrincipal(String ussdNumber) throws UnirestException, IOException {
       service.principalMenuUssd(ussdNumber);
    }

    @Step("consulta de <option> en USSD")
    public void metodoPruebaUssd(String option) throws UnirestException, IOException {
        service.goUssd().saldo(option, service.id).salirDelMenu(service.jsonArray);

    }

    @Step("Consulta de Saldo en SMS a <phoneNumber> con la palabra <textMessage>")
    public void metodoPruebaSms(String number, String text) throws UnirestException, IOException {
        service.goSms().envioSmS(number, text);

    }
}
