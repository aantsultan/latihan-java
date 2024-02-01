package com.latihan.java.logic;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
public class LatihanJavaLogicTest {

    private String a = "Java";
    private String b = "Java";
    private String c = new String("Java");

    @Test
    void equalString(){
        Assertions.assertSame(a, b);
    }

    @Test
    void equalNewString() {
        Assertions.assertSame(a, c);
    }

    @Test
    void arrayTest(){
        List<String> datas = Arrays.asList("Java", "is", "fun", "and", "challenging");
        int count = (int)datas.stream()
                .filter(data -> data.length() > 3)
                .map(String::length)
                .count();
        Assertions.assertEquals(2, count);
    }

    @Test
    void arrayTest2(){
        String[] data = new String[10];
        data[0] = "hello";
        data[1] = "world";
        System.out.println(Arrays.toString(data));
    }

    @Test
    void generateString(){
        String value="select tod.product_name " +
                "from hts.dbo.trx_order_detail tod  " +
                "inner join hts.dbo.trx_order_header toh on toh.order_header_id  = tod.order_header_id  " +
                "left join hts.dbo.trx_invoice ti on ti.sales_order_code = toh.sales_order_code " +
                "left join md.dbo.m_product mp on mp.sku = tod.product_sku " +
                "where format(toh.order_date, 'yyyy-MM-dd') BETWEEN CONVERT(varchar(10), CONVERT(date, ?1, 103), 23) and CONVERT(varchar(10), CONVERT(date, ?2, 103), 23) " +
                "and toh.sales_group_code = ?3 " +
                "and toh.customer_id  = ?4 " +
                "and toh.sales_group_code in (SELECT sales_groups FROM hts.dbo.fn_getA5byType (?5)) " +
                "and tod.is_ebs = 0 " +
                "and ( " +
                "(COALESCE(toh.sdl_transaction, 0) = 0 and UPPER(toh.status) = 'CLOSED') " +
                "OR (ti.sdl_invoice_paid = 1 and UPPER(toh.status) = 'CLOSED' and COALESCE(toh.sdl_transaction, 0) = 1) " +
                ") " +
                "group by tod.product_name " +
                "order by min(coalesce(mp.product_sort, 0))";
        System.out.println(value);
    }
}
