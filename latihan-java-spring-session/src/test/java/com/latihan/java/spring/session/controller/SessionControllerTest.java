package com.latihan.java.spring.session.controller;

import com.latihan.java.spring.session.LatihanJavaSpringSessionMain;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;
import redis.clients.jedis.Jedis;
import redis.embedded.RedisServer;

import java.io.IOException;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LatihanJavaSpringSessionMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SessionControllerTest {

    private static RedisServer redisServer;

    @LocalServerPort
    private int port;

    private Jedis jedis;
    private TestRestTemplate testRestTemplate;
    private TestRestTemplate testRestTemplateWithAuth;
    private String testurl = "http://localhost:8080";

    @BeforeClass
    public static void startRedisServer() throws IOException {
        redisServer = new RedisServer(6379);
        redisServer.start();
    }

    @AfterClass
    public static void stopRedisServer() {
        redisServer.stop();
    }

    @Before
    public void clearRedisData() {
        testRestTemplate = new TestRestTemplate();
        testRestTemplateWithAuth = new TestRestTemplate("admin", "password");

        jedis = new Jedis("localhost", 6379);
        jedis.flushAll();
    }

    @Test
    public void testRedisEmpty() {
        Set<String> result = jedis.keys("*");
        Assert.assertEquals(0, result.size());
    }

    @Test
    public void testUnauthenticatedCantAccess() {
        ResponseEntity<String> result = testRestTemplate.getForEntity(testurl, String.class);
        Assert.assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    @Test
    public void testRedisControlSession() {
        ResponseEntity<String> result = testRestTemplateWithAuth.getForEntity(getTesturl(), String.class);
        Assert.assertEquals("hello admin", result.getBody());

        Set<String> redisResult = jedis.keys("*");
        Assert.assertTrue(redisResult.size() > 0);

        String sessionCookie = result.getHeaders().get("Set-Cookie").get(0).split(";")[0];
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Cookie", sessionCookie);
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);

        result = testRestTemplate.exchange(getTesturl(), HttpMethod.GET, httpEntity, String.class);
        Assert.assertEquals("hello admin", result.getBody());

        jedis.flushAll();

        result = testRestTemplate.exchange(getTesturl(), HttpMethod.GET, httpEntity, String.class);
        Assert.assertEquals(HttpStatus.UNAUTHORIZED, result.getStatusCode());
    }

    private String getTesturl() {
        return "http://localhost:" + port;
    }
}
