package com.latihan.java.spring.bc365.dao;

import com.latihan.java.spring.bc365.model.Job;
import com.latihan.java.spring.bc365.model.ODataV4;

public interface JobDao {

    ODataV4<Job> get();

}
