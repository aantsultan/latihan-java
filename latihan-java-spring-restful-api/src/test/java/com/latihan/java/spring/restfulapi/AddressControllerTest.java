package com.latihan.java.spring.restfulapi;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.latihan.java.spring.restfulapi.entity.Address;
import com.latihan.java.spring.restfulapi.entity.Contact;
import com.latihan.java.spring.restfulapi.entity.User;
import com.latihan.java.spring.restfulapi.model.AddressResponse;
import com.latihan.java.spring.restfulapi.model.CreateAddressRequest;
import com.latihan.java.spring.restfulapi.model.UpdateAddressRequest;
import com.latihan.java.spring.restfulapi.model.WebResponse;
import com.latihan.java.spring.restfulapi.repository.AddressRepository;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AddressControllerTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ContactRepository contactRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        addressRepository.deleteAll();
        contactRepository.deleteAll();
        userRepository.deleteAll();

        User user = new User();
        user.setUsername("test");
        user.setPassword(BCrypt.hashpw("rahasia", BCrypt.gensalt()));
        user.setName("test");
        user.setToken("test");
        user.setTokenExpiredAt(System.currentTimeMillis() + 1000000000000L);
        userRepository.save(user);

        Contact contact = new Contact();
        contact.setId("testContact");
        contact.setFirstName("elain");
        contact.setLastName("eee");
        contact.setEmail("elain@test.com");
        contact.setPhone("1312124241241");
        contact.setUser(user);
        contactRepository.save(contact);
    }

    @Test
    void createAddressBadRequest() throws Exception {
        CreateAddressRequest request = new CreateAddressRequest();
        request.setCountry("");

        mockMvc.perform(
                post("/api/contacts/{contactId}/addresses", "testContact")
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
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
    void createAddressSuccess() throws Exception {
        CreateAddressRequest request = new CreateAddressRequest();
        request.setStreet("jalan");
        request.setCity("sungai bambu");
        request.setProvince("jakarta");
        request.setCountry("indonesia");
        request.setPostalCode("1234");

        mockMvc.perform(
                post("/api/contacts/{contactId}/addresses", "testContact")
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<WebResponse<AddressResponse>>() {
                    });

            assertNull(response.getErrors());
            assertEquals(request.getCity(), response.getData().getCity());
            assertEquals(request.getCountry(), response.getData().getCountry());
            assertEquals(request.getStreet(), response.getData().getStreet());
            assertEquals(request.getProvince(), response.getData().getProvince());
            assertEquals(request.getPostalCode(), response.getData().getPostalCode());

            assertTrue(addressRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void getAddressNotFound() throws Exception {
        mockMvc.perform(
                get("/api/contacts/{contactId}/addresses/{addressId}", "testContact", "salah")
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
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
    void getAddressSuccess() throws Exception {
        Contact contact = contactRepository.findById("testContact").orElse(null);

        Address address = new Address();
        address.setId("testAddress");
        address.setStreet("jalan");
        address.setCity("kota");
        address.setProvince("province");
        address.setCountry("indonesia");
        address.setPostalCode("1234");
        address.setContact(contact);

        addressRepository.save(address);

        mockMvc.perform(
                get("/api/contacts/{contactId}/addresses/{addressId}", "testContact", address.getId())
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<WebResponse<AddressResponse>>() {
                    });

            assertNull(response.getErrors());

            assertEquals(address.getId(), response.getData().getId());
            assertEquals(address.getPostalCode(), response.getData().getPostalCode());
            assertEquals(address.getCity(), response.getData().getCity());
            assertEquals(address.getCountry(), response.getData().getCountry());
            assertEquals(address.getProvince(), response.getData().getProvince());
            assertEquals(address.getStreet(), response.getData().getStreet());
        });
    }

    @Test
    void badAddressBadRequest() throws Exception {
        UpdateAddressRequest request = new UpdateAddressRequest();
        request.setCountry("");

        mockMvc.perform(
                put("/api/contacts/{contactId}/addresses/{addressId}", "testContact", "salah")
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
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
    void updateAddressSuccess() throws Exception {
        Contact contact = contactRepository.findById("testContact").orElse(null);

        Address address = new Address();
        address.setId("testAddress");
        address.setStreet("jalan");
        address.setCity("kota");
        address.setProvince("province");
        address.setCountry("indonesia");
        address.setPostalCode("1234");
        address.setContact(contact);

        addressRepository.save(address);

        UpdateAddressRequest request = new UpdateAddressRequest();
        request.setStreet("jalan");
        request.setCity("sungai bambu");
        request.setProvince("jakarta");
        request.setCountry("indonesia a");
        request.setPostalCode("6666");

        mockMvc.perform(
                put("/api/contacts/{contactId}/addresses/{addressId}", "testContact", address.getId())
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request))
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<AddressResponse> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<WebResponse<AddressResponse>>() {
                    });

            assertNull(response.getErrors());
            assertEquals(request.getCity(), response.getData().getCity());
            assertEquals(request.getCountry(), response.getData().getCountry());
            assertEquals(request.getStreet(), response.getData().getStreet());
            assertEquals(request.getProvince(), response.getData().getProvince());
            assertEquals(request.getPostalCode(), response.getData().getPostalCode());

            assertTrue(addressRepository.existsById(response.getData().getId()));
        });
    }

    @Test
    void deleteAddressNotFound() throws Exception {
        mockMvc.perform(
                delete("/api/contacts/{contactId}/addresses/{addressId}", "testContact", "salah")
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
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
    void deleteAddressSuccess() throws Exception {
        Contact contact = contactRepository.findById("testContact").orElse(null);

        Address address = new Address();
        address.setId("testAddress");
        address.setStreet("jalan");
        address.setCity("kota");
        address.setProvince("province");
        address.setCountry("indonesia");
        address.setPostalCode("1234");
        address.setContact(contact);

        addressRepository.save(address);

        mockMvc.perform(
                delete("/api/contacts/{contactId}/addresses/{addressId}", "testContact", address.getId())
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<String> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<WebResponse<String>>() {
                    });

            assertNull(response.getErrors());
            assertEquals("OK", response.getData());

            assertFalse(addressRepository.existsById(address.getId()));
        });
    }

    @Test
    void listAddressNotFound() throws Exception {
        mockMvc.perform(
                get("/api/contacts/{contactId}/addresses", "salah")
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
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
    void listAddressSuccess() throws Exception {
        Contact contact = contactRepository.findById("testContact").orElse(null);

        for (int i = 0; i < 5; i++) {
            Address address = new Address();
            address.setId("testAddress - " + i);
            address.setStreet("jalan");
            address.setCity("kota");
            address.setProvince("province");
            address.setCountry("indonesia");
            address.setPostalCode("1234");
            address.setContact(contact);

            addressRepository.save(address);
        }

        mockMvc.perform(
                get("/api/contacts/{contactId}/addresses", "testContact")
                        .header("X-API-TOKEN", "test")
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpectAll(
                status().isOk()
        ).andDo(result -> {
            WebResponse<List<AddressResponse>> response = objectMapper.readValue(result.getResponse().getContentAsString(),
                    new TypeReference<WebResponse<List<AddressResponse>>>() {
                    });

            assertNull(response.getErrors());
            assertEquals(5, response.getData().size());
        });
    }
}
