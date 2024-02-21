package com.latihan.java.spring.restfulapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.latihan.java.spring.restfulapi.entity.Contact;
import com.latihan.java.spring.restfulapi.entity.User;
import com.latihan.java.spring.restfulapi.model.ContactResponse;
import com.latihan.java.spring.restfulapi.model.CreateContactRequest;
import com.latihan.java.spring.restfulapi.model.UpdateContactRequest;
import com.latihan.java.spring.restfulapi.model.WebResponse;
import com.latihan.java.spring.restfulapi.repository.ContactRepository;
import com.latihan.java.spring.restfulapi.repository.UserRepository;
import com.latihan.java.spring.restfulapi.security.BCrypt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ContactControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        contactRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setUsername("test");
        user.setPassword(BCrypt.hashpw("test", BCrypt.gensalt()));
        user.setName("test");
        user.setToken("test");
        user.setTokenExpiredAt(System.currentTimeMillis() + 1000000);
        userRepository.save(user);
    }

    @Test
    void createContactBadRequest() throws Exception {
        CreateContactRequest request = new CreateContactRequest();
        request.setFirstName("");
        request.setEmail("salah");

        mockMvc.perform(
                post("/api/contacts")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<WebResponse<String>>() {
                    });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void createContactSuccess() throws Exception {
        CreateContactRequest request = new CreateContactRequest();
        request.setFirstName("elain");
        request.setLastName("eee");
        request.setEmail("elain@test.com");
        request.setPhone("1312124241241");

        mockMvc.perform(
                post("/api/contacts")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<ContactResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<WebResponse<ContactResponse>>() {
                    });

            assertNull(response.getErrors());
            assertEquals(request.getFirstName(), response.getData().getFirstName());
            assertEquals(request.getLastName(), response.getData().getLastName());
            assertEquals(request.getEmail(), response.getData().getEmail());
            assertEquals(request.getPhone(), response.getData().getPhone());

            assertTrue(contactRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void getContactNotFound() throws Exception {
        mockMvc.perform(
                get("/api/contacts/salah")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<WebResponse<String>>() {
                    });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void getContactSuccess() throws Exception {
        User user = userRepository.findById("test").orElse(null);

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName("elain");
        contact.setLastName("eee");
        contact.setEmail("elain@test.com");
        contact.setPhone("1312124241241");
        contact.setUser(user);
        contactRepository.save(contact);

        mockMvc.perform(
                get("/api/contacts/" + contact.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<ContactResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<WebResponse<ContactResponse>>() {
                    });

            assertNull(response.getErrors());

            assertEquals(contact.getId(), response.getData().getId());
            assertEquals(contact.getFirstName(), response.getData().getFirstName());
            assertEquals(contact.getLastName(), response.getData().getLastName());
            assertEquals(contact.getEmail(), response.getData().getEmail());
            assertEquals(contact.getPhone(), response.getData().getPhone());
        });
    }

    @Test
    void updateContactBadRequest() throws Exception {
        UpdateContactRequest request = new UpdateContactRequest();
        request.setFirstName("");
        request.setEmail("salah");

        mockMvc.perform(
                put("/api/contacts/salah")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isBadRequest()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<WebResponse<String>>() {
                    });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void updateContactSuccess() throws Exception {
        User user = userRepository.findById("test").orElse(null);

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName("elain");
        contact.setLastName("eee");
        contact.setEmail("elain@test.com");
        contact.setPhone("1312124241241");
        contact.setUser(user);
        contactRepository.save(contact);


        UpdateContactRequest request = new UpdateContactRequest();
        request.setFirstName("first elain");
        request.setLastName("last elain");
        request.setEmail("elainaaaaaa@test.com");
        request.setPhone("0000000");

        mockMvc.perform(
                put("/api/contacts/" + contact.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<ContactResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<WebResponse<ContactResponse>>() {
                    });

            assertNull(response.getErrors());
            assertEquals(request.getFirstName(), response.getData().getFirstName());
            assertEquals(request.getLastName(), response.getData().getLastName());
            assertEquals(request.getEmail(), response.getData().getEmail());
            assertEquals(request.getPhone(), response.getData().getPhone());

            assertTrue(contactRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void deleteContactNotFound() throws Exception {
        mockMvc.perform(
                delete("/api/contacts/salah")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isNotFound()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<WebResponse<String>>() {
                    });

            assertNotNull(response.getErrors());
        });
    }

    @Test
    void deleteContactSuccess() throws Exception {
        User user = userRepository.findById("test").orElse(null);

        Contact contact = new Contact();
        contact.setId(UUID.randomUUID().toString());
        contact.setFirstName("elain");
        contact.setLastName("eee");
        contact.setEmail("elain@test.com");
        contact.setPhone("1312124241241");
        contact.setUser(user);
        contactRepository.save(contact);

        mockMvc.perform(
                delete("/api/contacts/" + contact.getId())
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<WebResponse<String>>() {
                    });

            assertNull(response.getErrors());

            assertEquals("OK", response.getData());
        });
    }

    @Test
    void searchNotFound() throws Exception {
        mockMvc.perform(
                get("/api/contacts")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<ContactResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<WebResponse<List<ContactResponse>>>() {
                    });

            assertNull(response.getErrors());

            assertEquals(0, response.getData().size());
            assertEquals(0, response.getPaging().getTotalPage());
            assertEquals(0, response.getPaging().getCurrentPage());
            assertEquals(10, response.getPaging().getSize());
        });
    }


    @Test
    void searchSuccess() throws Exception {
        User user = userRepository.findById("test").orElse(null);

        int size = 10;
        int totalContact = 100;
        int currentPage = 0;

        for (int i = 0; i < totalContact; i++) {
            Contact contact = new Contact();
            contact.setId(UUID.randomUUID().toString());
            contact.setFirstName("elain " + i );
            contact.setLastName("eee");
            contact.setEmail("elain@test.com");
            contact.setPhone("1312124241241");
            contact.setUser(user);
            contactRepository.save(contact);
        }

        // using name
        mockMvc.perform(
                get("/api/contacts")
                        .queryParam("name", "elain")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<ContactResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<WebResponse<List<ContactResponse>>>() {
                    });

            assertNull(response.getErrors());

            assertEquals(totalContact/size, response.getData().size());
            assertEquals(totalContact/size, response.getPaging().getTotalPage());
            assertEquals(currentPage, response.getPaging().getCurrentPage());
            assertEquals(size, response.getPaging().getSize());
        });

        // using name
        mockMvc.perform(
                get("/api/contacts")
                        .queryParam("name", "eee")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<ContactResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<WebResponse<List<ContactResponse>>>() {
                    });

            assertNull(response.getErrors());

            assertEquals(totalContact/size, response.getData().size());
            assertEquals(totalContact/size, response.getPaging().getTotalPage());
            assertEquals(currentPage, response.getPaging().getCurrentPage());
            assertEquals(size, response.getPaging().getSize());
        });

        // using phone
        mockMvc.perform(
                get("/api/contacts")
                        .queryParam("phone", "1312124241241")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<ContactResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<WebResponse<List<ContactResponse>>>() {
                    });

            assertNull(response.getErrors());

            assertEquals(totalContact/size, response.getData().size());
            assertEquals(totalContact/size, response.getPaging().getTotalPage());
            assertEquals(currentPage, response.getPaging().getCurrentPage());
            assertEquals(size, response.getPaging().getSize());
        });

        // using email
        mockMvc.perform(
                get("/api/contacts")
                        .queryParam("email", "elain@test.com")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<ContactResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<WebResponse<List<ContactResponse>>>() {
                    });

            assertNull(response.getErrors());

            assertEquals(totalContact/size, response.getData().size());
            assertEquals(totalContact/size, response.getPaging().getTotalPage());
            assertEquals(currentPage, response.getPaging().getCurrentPage());
            assertEquals(size, response.getPaging().getSize());
        });

        // using more than one query
        mockMvc.perform(
                get("/api/contacts")
                        .queryParam("phone", "1312124241241")
                        .queryParam("page", "1000")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("X-API-TOKEN", "test")
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<ContactResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<WebResponse<List<ContactResponse>>>() {
                    });

            assertNull(response.getErrors());

            assertEquals(0, response.getData().size());
            assertEquals(10, response.getPaging().getTotalPage());
            assertEquals(1000, response.getPaging().getCurrentPage());
            assertEquals(10, response.getPaging().getSize());
        });
    }
}
