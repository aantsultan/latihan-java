package com.latihan.java.spring.restfulapi.controller;

import com.latihan.java.spring.restfulapi.entity.User;
import com.latihan.java.spring.restfulapi.model.AddressResponse;
import com.latihan.java.spring.restfulapi.model.CreateAddressRequest;
import com.latihan.java.spring.restfulapi.model.UpdateAddressRequest;
import com.latihan.java.spring.restfulapi.model.WebResponse;
import com.latihan.java.spring.restfulapi.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AddressController {

    private final AddressService service;

    @Autowired
    public AddressController(AddressService service) {
        this.service = service;
    }


    @PostMapping(
            path = "/api/contacts/{contactId}/addresses",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> create(User user,
                                               @RequestBody CreateAddressRequest request,
                                               @PathVariable("contactId") String contactId) {
        request.setContactId(contactId);

        AddressResponse response = service.create(user, request);
        return WebResponse.<AddressResponse>builder().data(response).build();
    }

    @GetMapping(
            path = "/api/contacts/{contactId}/addresses/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> get(User user,
                                            @PathVariable("contactId") String contactId,
                                            @PathVariable("addressId") String addressId) {
        AddressResponse response = service.get(user, contactId, addressId);
        return WebResponse.<AddressResponse>builder().data(response).build();
    }

    @PutMapping(
            path = "/api/contacts/{contactId}/addresses/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<AddressResponse> update(User user,
                                               @RequestBody UpdateAddressRequest request,
                                               @PathVariable("contactId") String contactId,
                                               @PathVariable("addressId") String addressId) {
        request.setContactId(contactId);
        request.setAddressId(addressId);

        AddressResponse response = service.update(user, request);
        return WebResponse.<AddressResponse>builder().data(response).build();
    }

    @DeleteMapping(
            path = "/api/contacts/{contactId}/addresses/{addressId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<String> delete(User user,
                                      @PathVariable("contactId") String contactId,
                                      @PathVariable("addressId") String addressId) {
        service.remove(user, contactId, addressId);
        return WebResponse.<String>builder().data("OK").build();
    }

    @GetMapping(
            path = "/api/contacts/{contactId}/addresses",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public WebResponse<List<AddressResponse>> get(User user,
                                                  @PathVariable("contactId") String contactId) {
        List<AddressResponse> responses = service.list(user, contactId);
        return WebResponse.<List<AddressResponse>>builder().data(responses).build();
    }
}
