package com.latihan.java.spring.soap.endpoint;

import com.latihan.java.spring.soap.jaxb.GetCountryRequest;
import com.latihan.java.spring.soap.jaxb.GetCountryResponse;
import com.latihan.java.spring.soap.repository.CountryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
@RequiredArgsConstructor
public class CountryEndpoint {

    private static final String NAMESPACE_URI = "http://www.latihan.com/java/spring/soap/jaxb";
    private final CountryRepository countryRepository;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountry(@RequestPayload GetCountryRequest request){
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));
        return response;
    }

}
