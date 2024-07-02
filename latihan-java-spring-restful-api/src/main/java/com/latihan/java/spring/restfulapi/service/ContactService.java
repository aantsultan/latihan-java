package com.latihan.java.spring.restfulapi.service;

import com.latihan.java.spring.restfulapi.entity.User;
import com.latihan.java.spring.restfulapi.model.ContactResponse;
import com.latihan.java.spring.restfulapi.model.CreateContactRequest;
import com.latihan.java.spring.restfulapi.model.SearchContactRequest;
import com.latihan.java.spring.restfulapi.model.UpdateContactRequest;
import org.springframework.data.domain.Page;

public interface ContactService {

    ContactResponse create(User user, CreateContactRequest request);

    ContactResponse get(User user, String id);

    ContactResponse update(User user, UpdateContactRequest request);

    void delete(User user, String contactId);

    Page<ContactResponse> search(User user, SearchContactRequest request);
}
