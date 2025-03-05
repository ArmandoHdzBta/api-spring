package com.nando.apiExample.service.auth;

import com.nando.apiExample.model.dao.IUserDao;
import com.nando.apiExample.model.dto.UserDto;
import com.nando.apiExample.model.entity.User;
import com.nando.apiExample.service.auth.base.IRegister;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RegisterService implements IRegister {
    private final IUserDao userDao;

    RegisterService(IUserDao userDao) {
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public UserDto register(UserDto user) {
        String hashPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());

        User userEntity = new User();
        userEntity.setName(user.getName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(hashPassword);

        User savedUser = this.userDao.save(userEntity);
        UserDto userDto = new UserDto();
        userDto.setId(savedUser.getId());
        userDto.setName(savedUser.getName());
        userDto.setLastName(savedUser.getLastName());
        userDto.setEmail(savedUser.getEmail());

        return userDto;
    }
}
