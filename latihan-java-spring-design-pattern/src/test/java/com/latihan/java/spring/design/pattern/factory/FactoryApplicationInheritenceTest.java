package com.latihan.java.spring.design.pattern.factory;

import com.latihan.java.spring.design.pattern.factory.inheritence.FactoryInheritenceApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest(classes = FactoryInheritenceApplication.class)
public class FactoryApplicationInheritenceTest {

    private final ApplicationContext applicationContext;

    @Autowired
    public FactoryApplicationInheritenceTest(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Test
    void inheritence(){
        SocialMedia facebook = applicationContext.getBean("socialMediaFacebook", SocialMedia.class);
        SocialMedia twitter = applicationContext.getBean("socialMediaTwitter", SocialMedia.class);
        System.out.println(facebook);
        System.out.println(twitter);
    }
}
