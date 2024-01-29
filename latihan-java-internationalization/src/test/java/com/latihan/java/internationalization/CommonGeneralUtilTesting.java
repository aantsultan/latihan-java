package com.latihan.java.internationalization;

import com.common.util.specific.GeneralUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommonGeneralUtilTesting {

    @Test
    void addUtil() {
        int result = GeneralUtil.add(2,3);
        Assertions.assertEquals(5, result);
    }
}
