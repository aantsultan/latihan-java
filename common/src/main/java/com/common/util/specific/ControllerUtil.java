package com.common.util.specific;

import com.common.util.model.ApplicationProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class ControllerUtil {

    @Autowired
    ApplicationProperties applicationProperties;

    public boolean iso8583(LinkedHashMap<String, String> request){
        int temp = 0;
        try {
            for(Map.Entry<String, String> map : request.entrySet()){
                String key = map.getKey();
                Integer keyId = Integer.valueOf(key.split(applicationProperties.getKeyName())[1]);
                if(keyId > temp){
                    temp = keyId;
                } else {
                    return false;
                }
            }
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            return false;
        }
        return true;
    }
}
