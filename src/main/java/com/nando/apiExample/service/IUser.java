package com.nando.apiExample.service;

import com.nando.apiExample.model.dto.UserDto;
import com.nando.apiExample.model.entity.User;

public interface IUser {
    User save(UserDto user);
}
