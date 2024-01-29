package com.latihan.java.spring.design.pattern.factory;

import com.latihan.java.spring.design.pattern.factory.method.FactoryMethodApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = FactoryMethodApplication.class)
public class FactoryApplicationMethodTest {

    private final ApplicationContext applicationContext;

    @Autowired
    public FactoryApplicationMethodTest(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Test
    void method(){
        SocialMedia facebook = applicationContext.getBean(SocialMedia.class, SocialMediaType.FACEBOOK);
        SocialMedia twitter = applicationContext.getBean(SocialMedia.class, SocialMediaType.TWITTER);

        System.out.println(facebook);
        System.out.println(twitter);
    }
}
