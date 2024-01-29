package com.latihan.java.spring.design.pattern.factory.model;

import com.latihan.java.spring.design.pattern.factory.SocialMedia;
import com.latihan.java.spring.design.pattern.factory.SocialMediaType;
import com.latihan.java.spring.design.pattern.util.JsonHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class TwitterSocialMedia implements SocialMedia {

    @Getter
    private String name = "TWITTER";
    @Getter
    private String link = "https://twitter.com";
    @Getter
    private SocialMediaType type = SocialMediaType.TWITTER;

    @Override
    public String toString(){
        return JsonHelper.valueOf(new TwitterSocialMedia(name, link, type));
    }

}
