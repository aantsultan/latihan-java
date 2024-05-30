package com.latihan.java.logic.sololearn;

import org.junit.jupiter.api.Test;

class CustomerSaveTest {

    @Test
    void saveCustomerTest(){
        Customer customer = new Customer();
        customer.firstName = "aant";
        customer.lastName = "sultan";
        customer.age = 10;
        customer.roomNumber = 101;

        customer.saveCustomer();

        customer.setAge2(-2);
        System.out.println(customer.getAge2());
    }
}
