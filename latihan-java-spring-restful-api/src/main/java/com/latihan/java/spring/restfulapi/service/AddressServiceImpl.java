package com.latihan.java.spring.restfulapi.service;

import com.latihan.java.spring.restfulapi.entity.Address;
import com.latihan.java.spring.restfulapi.entity.Contact;
import com.latihan.java.spring.restfulapi.entity.User;
import com.latihan.java.spring.restfulapi.model.AddressResponse;
import com.latihan.java.spring.restfulapi.model.CreateAddressRequest;
import com.latihan.java.spring.restfulapi.model.UpdateAddressRequest;
import com.latihan.java.spring.restfulapi.repository.AddressRepository;
import com.latihan.java.spring.restfulapi.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository repository;
    private final ContactRepository contactRepository;
    private final ValidationService validationService;

    @Autowired
    public AddressServiceImpl(AddressRepository repository, ContactRepository contactRepository, ValidationService validationService) {
        this.repository = repository;
        this.contactRepository = contactRepository;
        this.validationService = validationService;
    }

    @Transactional
    @Override
    public AddressResponse create(User user, CreateAddressRequest request) {
        validationService.validate(request);

        Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

        Address address = new Address();
        address.setId(UUID.randomUUID().toString());
        address.setContact(contact);
        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setProvince(request.getProvince());
        address.setCountry(request.getCountry());
        address.setPostalCode(request.getPostalCode());

        repository.save(address);

        return this.toAddressResponse(address);
    }

    @Transactional(readOnly = true)
    @Override
    public AddressResponse get(User user, String contactId, String addressId) {

        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

        Address address = repository.findFirstByContactAndId(contact, addressId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address is not found"));

        return this.toAddressResponse(address);
    }

    @Transactional
    @Override
    public AddressResponse update(User user, UpdateAddressRequest request) {
        validationService.validate(request);

        Contact contact = contactRepository.findFirstByUserAndId(user, request.getContactId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

        Address address = repository.findFirstByContactAndId(contact, request.getAddressId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address is not found"));

        address.setStreet(request.getStreet());
        address.setCity(request.getCity());
        address.setProvince(request.getProvince());
        address.setCountry(request.getCountry());
        address.setPostalCode(request.getPostalCode());

        address.setContact(contact);

        repository.save(address);

        return this.toAddressResponse(address);
    }

    @Transactional
    @Override
    public void remove(User user, String contactId, String addressId) {
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

        Address address = repository.findFirstByContactAndId(contact, addressId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Address is not found"));

        repository.delete(address);
    }

    @Transactional(readOnly = true)
    @Override
    public List<AddressResponse> list(User user, String contactId) {
        Contact contact = contactRepository.findFirstByUserAndId(user, contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact is not found"));

        List<Address> addresses = repository.findByContact(contact);

        return addresses.stream().map(this::toAddressResponse).collect(Collectors.toList());
    }

    private AddressResponse toAddressResponse(Address address) {
        return AddressResponse.builder()
                .id(address.getId())
                .street(address.getStreet())
                .city(address.getCity())
                .country(address.getCountry())
                .postalCode(address.getPostalCode())
                .province(address.getProvince())
                .build();
    }
}
