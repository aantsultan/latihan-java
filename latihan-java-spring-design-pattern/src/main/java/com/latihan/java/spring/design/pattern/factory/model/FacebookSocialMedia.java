package com.latihan.java.spring.design.pattern.factory.model;

import com.latihan.java.spring.design.pattern.factory.SocialMedia;
import com.latihan.java.spring.design.pattern.factory.SocialMediaType;
import com.latihan.java.spring.design.pattern.util.JsonHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class FacebookSocialMedia implements SocialMedia {

    @Getter
    private String name = "FACEBOOK";
    @Getter
    private String link = "https://facebook.com";
    @Getter
    private SocialMediaType type = SocialMediaType.FACEBOOK;

    @Override
    public String toString(){
        return JsonHelper.valueOf(new FacebookSocialMedia(name, link, type));
    }
}
