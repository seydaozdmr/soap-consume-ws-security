package com.example.soapconsume.config;

import com.example.soapconsume.soapconnector.SoapConnector;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import org.springframework.ws.client.support.interceptor.ClientInterceptor;
import org.springframework.ws.soap.security.wss4j2.Wss4jSecurityInterceptor;

import javax.xml.soap.SOAPConnection;

@Configuration
public class WebServiceConfig {

    @Bean
    public Jaxb2Marshaller marshaller(){
        Jaxb2Marshaller marshaller=new Jaxb2Marshaller();
        marshaller.setContextPath("io.spring.guides.gs_producing_web_service");
        return marshaller;
    }

    @Bean
    public SoapConnector soapConnector(Jaxb2Marshaller marshaller){
        SoapConnector soapConnector=new SoapConnector();
        soapConnector.setDefaultUri("http://localhost:8080/ws");
        soapConnector.setMarshaller(marshaller);
        soapConnector.setUnmarshaller(marshaller);
        soapConnector.setInterceptors(new ClientInterceptor[]{ securityInterceptor() });;
        return soapConnector;
    }
    @Bean
    public Wss4jSecurityInterceptor securityInterceptor() {
        Wss4jSecurityInterceptor security = new Wss4jSecurityInterceptor();
        security.setSecurementActions("UsernameToken");
        security.setSecurementUsername("user");
        security.setSecurementPassword("password");
        security.setSecurementPasswordType("PasswordText");
        return security;
    }
}
