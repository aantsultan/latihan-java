package com.latihan.java.spring.soap.repository;

import com.latihan.java.spring.soap.jaxb.Country;
import com.latihan.java.spring.soap.jaxb.Currency;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class CountryRepository {

    private static final Map<String, Country> countries = new HashMap<>();

    @PostConstruct
    public void initData(){
        Country indonesia = new Country();
        indonesia.setName("Indonesia");
        indonesia.setCapital("Jakarta");
        indonesia.setCurrency(Currency.EUR);
        indonesia.setPopulation(1000);

        Country japan = new Country();
        japan.setName("Jepang");
        japan.setCapital("Tokyo");
        japan.setCurrency(Currency.GBP);
        japan.setPopulation(100);

        countries.put("indonesia", indonesia);
        countries.put("japan", japan);
    }

    public Country findCountry(String name){
        return countries.get(name);
    }
}
