package com.nando.apiExample.service.auth.base;

import com.nando.apiExample.model.dto.AuthDto;
import com.nando.apiExample.model.dto.UserDto;

public interface IAuth {
    UserDto login(AuthDto authDto);
}
