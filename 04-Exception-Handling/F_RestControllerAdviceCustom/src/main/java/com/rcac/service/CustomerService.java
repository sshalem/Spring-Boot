package com.rcac.service;

import com.rcac.entity.*;
import com.rcac.exception.*;
import org.springframework.stereotype.*;

import java.sql.SQLClientInfoException;

@Component
public class CustomerService {

    private final String NAME = "unknown";

    public String getName(String name) {
        if (!name.equals(this.NAME))
            throw new ResourceNotFoundException(ErrorMessages.NO_RECORD_FOUND.getErrorMessage());
        return name;
    }

    public UserEntity createUser(UserEntity userEntity) {
        if (userEntity.getFirstName().equals("karin")) {
            throw new IllegalArgumentException(ErrorMessages.INTERNAL_SERVER_ERROR.getErrorMessage());
        }
        return userEntity;
    }
}
