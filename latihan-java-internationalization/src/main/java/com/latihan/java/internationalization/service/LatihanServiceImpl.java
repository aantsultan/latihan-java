package com.latihan.java.internationalization.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@Service
public class LatihanServiceImpl implements LatihanService {
    @Override
    public ResponseEntity<String> latihan(String name, Locale language) {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("message", language);
        String greetings = resourceBundle.getString("greetings");
        MessageFormat messageFormat = new MessageFormat(greetings);
        String result = messageFormat.format(new Object[]{
                name
        });
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
