package com.nando.apiExample.service.auth;

import com.nando.apiExample.model.dao.IUserDao;
import com.nando.apiExample.model.dto.AuthDto;
import com.nando.apiExample.model.dto.UserDto;
import com.nando.apiExample.model.entity.User;
import com.nando.apiExample.service.auth.base.IAuth;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthService implements IAuth {
    private final IUserDao user;

    AuthService(IUserDao user) {
        this.user = user;
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto login(AuthDto authDto) {
        User findUser = this.user.findUserByEmail(authDto.getEmail());

        if(findUser == null) {
            return null;
        }

        if(!BCrypt.checkpw(authDto.getPassword(), findUser.getPassword())) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setId(findUser.getId());
        userDto.setEmail(findUser.getEmail());
        userDto.setName(findUser.getName());
        userDto.setLastName(findUser.getLastName());

        return userDto;
    }
}
