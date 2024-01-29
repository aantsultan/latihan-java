package com.latihan.java.springdasar;

import com.common.util.UtilMain;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackageClasses = {
        LatihanJavaSpringDasarMain.class, UtilMain.class
})
public class LatihanJavaSpringDasarMain {

    public static void main(String[] args){
        SpringApplication.run(LatihanJavaSpringDasarMain.class, args);
    }
}
