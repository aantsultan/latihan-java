package com.latihan.java.spring.design.pattern.factory.inheritence;

import com.latihan.java.spring.design.pattern.factory.SocialMedia;
import com.latihan.java.spring.design.pattern.factory.model.FacebookSocialMedia;
import com.latihan.java.spring.design.pattern.factory.model.TwitterSocialMedia;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;

@SpringBootApplication
public class FactoryInheritenceApplication {

    @Bean
    @Scope("prototype")
    public SocialMedia socialMediaFacebook(){
        return new FacebookSocialMedia();
    }

    @Bean
    @Scope("prototype")
    public SocialMedia socialMediaTwitter(){
        return new TwitterSocialMedia();
    }
}
