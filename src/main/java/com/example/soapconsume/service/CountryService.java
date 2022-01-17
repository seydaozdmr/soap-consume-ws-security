package com.example.soapconsume.service;

import com.example.soapconsume.soapconnector.SoapConnector;
import io.spring.guides.gs_producing_web_service.*;
import org.springframework.stereotype.Service;
import org.springframework.ws.soap.client.SoapFaultClientException;

import java.util.List;

@Service
public class CountryService {
    private final SoapConnector soapConnector;
    private final String serviceUri="http://localhost:8080/ws";

    public CountryService(SoapConnector soapConnector) {
        this.soapConnector = soapConnector;
    }

    public List<Country> getAllCountiesFromSoapService(){
        return ((GetAllCountryResponse) soapConnector.callWebService(serviceUri,new GetAllCountryRequest())).getCountry();
    }

    public Country getCountryFromSoapService(String name){
        GetCountryRequest request=new GetCountryRequest();
        request.setName(name);
        Country country;
        try{
            country= ((GetCountryResponse) soapConnector.callWebService(serviceUri,request)).getCountry();
        }catch (SoapFaultClientException e){
            throw new RuntimeException("İstenen ülke bulunamadı");
        }
        return country;
    }
}
