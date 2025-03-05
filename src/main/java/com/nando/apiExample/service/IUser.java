package com.nando.apiExample.service;

import com.nando.apiExample.model.dto.UserDto;
import com.nando.apiExample.model.entity.User;

import java.util.List;

public interface IUser {
    List<UserDto> findAll();

    UserDto getUserById(Integer id);

    //UserDto partialUpdateUser(Integer id, UserDto user);
}
