package com.latihan.java.spring.design.pattern.factory.method;

import com.latihan.java.spring.design.pattern.factory.SocialMedia;
import com.latihan.java.spring.design.pattern.factory.SocialMediaType;
import com.latihan.java.spring.design.pattern.factory.model.FacebookSocialMedia;
import com.latihan.java.spring.design.pattern.factory.model.TwitterSocialMedia;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class FactoryMethodApplication {

    @Bean
    @Scope("prototype")
    public SocialMedia socialMedia(SocialMediaType type) {
        if (type == SocialMediaType.FACEBOOK) {
            return new FacebookSocialMedia();
        } else if (type == SocialMediaType.TWITTER) {
            return new TwitterSocialMedia();
        } else {
            throw new IllegalArgumentException("Unsupported Social Media Type");
        }
    }
}
