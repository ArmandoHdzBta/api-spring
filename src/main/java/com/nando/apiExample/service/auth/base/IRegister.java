package com.nando.apiExample.service.auth.base;

import com.nando.apiExample.model.dto.UserDto;

public interface IRegister {
    UserDto register(UserDto userDto);
}
