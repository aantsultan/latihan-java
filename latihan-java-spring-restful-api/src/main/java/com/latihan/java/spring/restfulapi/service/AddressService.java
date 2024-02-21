package com.latihan.java.spring.restfulapi.service;

import com.latihan.java.spring.restfulapi.entity.User;
import com.latihan.java.spring.restfulapi.model.AddressResponse;
import com.latihan.java.spring.restfulapi.model.CreateAddressRequest;
import com.latihan.java.spring.restfulapi.model.UpdateAddressRequest;

import java.util.List;

public interface AddressService {

    AddressResponse create(User user, CreateAddressRequest request);

    AddressResponse get(User user, String contactId, String addressId);

    AddressResponse update(User user, UpdateAddressRequest request);

    void remove(User user, String contactId, String addressId);

    List<AddressResponse> list(User user, String contactId);

}
