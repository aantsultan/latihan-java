package com.latihan.java.spring.bc365.dao;

import com.latihan.java.spring.bc365.model.ODataV4;
import com.latihan.java.spring.bc365.model.SampleUserList;

public interface SampleUserListDao {

    ODataV4<SampleUserList> findAll();

}
