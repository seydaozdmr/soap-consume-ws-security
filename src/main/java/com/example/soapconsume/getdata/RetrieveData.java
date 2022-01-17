package com.example.soapconsume.getdata;

import com.example.soapconsume.soapconnector.SoapConnector;
import io.spring.guides.gs_producing_web_service.GetCountryRequest;
import io.spring.guides.gs_producing_web_service.GetCountryResponse;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RetrieveData {
    @Bean
    CommandLineRunner lookup(SoapConnector soapConnector) {
        return args -> {
            String name = "Yasin";//Default Name
            if(args.length>0){
                name = args[0];
            }

            GetCountryRequest request=new GetCountryRequest();
            request.setName("Poland");

            GetCountryResponse response=(GetCountryResponse) soapConnector.callWebService("http://localhost:8080/ws",request);

            System.out.println(response.getCountry().getCapital());

//            StudentDetailsRequest request = new StudentDetailsRequest();
//            request.setName(name);
//
//            StudentDetailsResponse response =(StudentDetailsResponse) soapConnector.callWebService("http://localhost:8080/service/student-details", request);
//
//            System.out.println("Got Response As below ========= : ");
//            System.out.println("Name : "+response.getStudent().getName());
//            System.out.println("Standard : "+response.getStudent().getStandard());
//            System.out.println("Address : "+response.getStudent().getAddress());
//            System.out.println("Age : "+response.getStudent().getAge());
        };
    }
}
