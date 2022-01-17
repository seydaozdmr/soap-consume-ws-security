package com.example.soapconsume.controller;

import com.example.soapconsume.service.CountryService;
import io.spring.guides.gs_producing_web_service.Country;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CountryController {
    private final CountryService countryService;

    public CountryController(CountryService countryService) {
        this.countryService = countryService;
    }

    @GetMapping("/counties")
    public ResponseEntity<List<Country>> findAll(){
        return ResponseEntity.ok(countryService.getAllCountiesFromSoapService());
    }

    @GetMapping("/counties/{name}")
    public ResponseEntity<Country> findByName(@PathVariable String name){
        return ResponseEntity.ok(countryService.getCountryFromSoapService(name));
    }
}
