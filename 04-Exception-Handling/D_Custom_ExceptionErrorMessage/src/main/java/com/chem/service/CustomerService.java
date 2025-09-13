package com.chem.service;

import org.springframework.stereotype.Component;
import com.chem.entity.UserEntity;
import com.chem.exception.NameAlreadyExistException;

@Component
public class CustomerService {

    private final String NAME = "karin";

    public String getName(String name) {
        if (name.equals(this.NAME))
            throw new NameAlreadyExistException("Name " + this.NAME + " already exist");
        return name;
    }
}
