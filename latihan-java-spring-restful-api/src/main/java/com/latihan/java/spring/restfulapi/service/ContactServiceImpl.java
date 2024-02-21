package com.latihan.java.spring.restfulapi.service;

import com.latihan.java.spring.restfulapi.entity.Contact;
import com.latihan.java.spring.restfulapi.entity.User;
import com.latihan.java.spring.restfulapi.model.ContactResponse;
import com.latihan.java.spring.restfulapi.model.CreateContactRequest;
import com.latihan.java.spring.restfulapi.model.SearchContactRequest;
import com.latihan.java.spring.restfulapi.model.UpdateContactRequest;
import com.latihan.java.spring.restfulapi.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactRepository repository;
    private final ValidationService validationService;


    @Autowired
    public ContactServiceImpl(ContactRepository repository, ValidationService validationService) {
        this.repository = repository;
        this.validationService = validationService;
    }

    @Transactional
    @Override
    public ContactResponse create(User user, CreateContactRequest request) {
        validationService.validate(request);

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());
        contact.setUser(user);

        repository.save(contact);

        return this.toContactResponse(contact);
    }

    @Transactional(readOnly = true)
    @Override
    public ContactResponse get(User user, String id) {
        Contact contact = repository.findFirstByUserAndId(user, id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));
        return this.toContactResponse(contact);
    }

    @Transactional
    @Override
    public ContactResponse update(User user, UpdateContactRequest request) {
        validationService.validate(request);

        Contact contact = repository.findFirstByUserAndId(user, request.getId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));

        contact.setFirstName(request.getFirstName());
        contact.setLastName(request.getLastName());
        contact.setEmail(request.getEmail());
        contact.setPhone(request.getPhone());

        repository.save(contact);

        return this.toContactResponse(contact);
    }

    @Transactional
    @Override
    public void delete(User user, String contactId) {
        Contact contact = repository.findFirstByUserAndId(user, contactId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Contact not found"));
        repository.delete(contact);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<ContactResponse> search(User user, SearchContactRequest request) {
        validationService.validate(request);

        Specification<Contact> specification = (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            predicates.add(criteriaBuilder.equal(root.get("user"), user));
            if (Objects.nonNull(request.getName())) {
                predicates.add(criteriaBuilder.or(
                        criteriaBuilder.like(root.get("firstName"), "%" + request.getName() + "%"),
                        criteriaBuilder.like(root.get("lastName"), "%" + request.getName() + "%")
                ));
            }

            if (Objects.nonNull(request.getEmail())) {
                predicates.add(criteriaBuilder.like(root.get("email"), "%" + request.getEmail() + "%"));
            }

            if (Objects.nonNull(request.getPhone())) {
                predicates.add(criteriaBuilder.like(root.get("phone"), "%" + request.getPhone() + "%"));
            }

            return query.where(predicates.toArray(new Predicate[]{})).getRestriction();
        };
        Pageable pageable = PageRequest.of(request.getPage(), request.getSize());
        Page<Contact> contactPage = repository.findAll(specification, pageable);

        List<ContactResponse> contactResponseList = contactPage.getContent().stream()
                .map(this::toContactResponse)
                .collect(Collectors.toList());
        return new PageImpl<>(contactResponseList, pageable, contactPage.getTotalElements());
    }

    private ContactResponse toContactResponse(Contact contact) {
        return ContactResponse.builder()
                .id(contact.getId())
                .firstName(contact.getFirstName())
                .lastName(contact.getLastName())
                .email(contact.getEmail())
                .phone(contact.getPhone())
                .build();
    }
}
