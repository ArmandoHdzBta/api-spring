package com.nando.apiExample.service;

import com.nando.apiExample.model.dto.UserDto;
import com.nando.apiExample.model.entity.User;

import java.util.List;

public interface IUser {
    UserDto save(UserDto user);

    List<UserDto> findAll();

    UserDto getUserById(Integer id);
}
