package com.latihan.java.database;

import com.common.util.specific.GeneralUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CommonGeneralUtilTest {

    @Test
    void testAddUtil() {
        int result = GeneralUtil.add(1,1);
        Assertions.assertEquals(2, result);
    }
}
