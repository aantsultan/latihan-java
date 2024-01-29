package com.latihan.java.internationalization;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

@SpringBootTest
public class NumberCurrencyI18NTest {

    @Test
    void formatTest(){
        String expected = "28.932.031,00";
        String input = "28932031";
        Double inputD = new Double(input);
        Locale indonesia = new Locale("in", "ID");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(indonesia);
        String result = numberFormat.format(inputD).replace("Rp", "");
        Assertions.assertEquals(expected, result);
    }

}
