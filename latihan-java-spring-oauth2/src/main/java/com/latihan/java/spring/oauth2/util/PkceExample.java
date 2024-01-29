package com.latihan.java.spring.oauth2.util;

import lombok.extern.slf4j.Slf4j;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

@Slf4j
public class PkceExample {

    public static void main(String[] args) {
        try {
            PkceUtil pkceUtil = new PkceUtil();
            String codeVerifier = pkceUtil.generateCodeVerifier();
            log.info("Code verifier : {}", codeVerifier);

            String codeChallenge = pkceUtil.generateCodeChallenge(codeVerifier);
            log.info("Code challenge : {}", codeChallenge);
        } catch (UnsupportedEncodingException | NoSuchAlgorithmException ex) {
            Logger.getLogger(PkceExample.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
