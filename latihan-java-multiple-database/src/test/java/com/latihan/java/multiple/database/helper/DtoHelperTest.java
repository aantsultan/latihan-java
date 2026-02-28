package com.latihan.java.multiple.database.helper;

import com.latihan.java.multiple.database.dto.hts.UserDto;
import com.latihan.java.multiple.database.model.hts.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DtoHelperTest {

    @Autowired
    DtoHelper dtoHelper;

    @Test
    void toDto(){
        User user = new User();
        user.setId(10);
        user.setName("aant");
        user.setEmail("aant@mail.com");
        user.setSalesGroupId(1);

        UserDto userDto = dtoHelper.toDto(user, UserDto.class);
        System.out.println(userDto);
    }

}
