package com.latihan.java.multiple.database.service;

import com.latihan.java.multiple.database.dto.UserSalesGroupDto;
import com.latihan.java.multiple.database.dto.hts.UserDto;

public interface UserSalesGroupService {

    String save(UserSalesGroupDto dto);

    String saveEm(UserDto dto);

}
