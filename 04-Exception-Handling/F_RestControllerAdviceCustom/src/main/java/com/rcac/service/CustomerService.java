package com.rcac.service;

import com.rcac.entity.UserEntity;
import com.rcac.exception.ErrorMessagesEnum;
import com.rcac.exception.ResourceNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class CustomerService {

    public final String NAME = "unknown";

    public String getName(String name) {
        if (!name.equals(this.NAME))
            throw new ResourceNotFoundException(ErrorMessagesEnum.NO_RECORD_FOUND.getErrorMessage());
        return name;
    }

    public UserEntity createUser(UserEntity userEntity) {
        if (userEntity.getFirstName().equals("karin")) {
            throw new IllegalArgumentException(ErrorMessagesEnum.INTERNAL_SERVER_ERROR.getErrorMessage());
        }
        return userEntity;
    }
}
