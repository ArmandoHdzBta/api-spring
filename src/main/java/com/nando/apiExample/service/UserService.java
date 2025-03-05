package com.nando.apiExample.service;

import com.nando.apiExample.model.dao.IUserDao;
import com.nando.apiExample.model.dto.UserDto;
import com.nando.apiExample.model.entity.User;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUser {
    private final IUserDao userDao;

    public UserService(IUserDao userDao){
        this.userDao = userDao;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> findAll() {
        List<User> userList = this.userDao.findAll();
        return userList.stream()
                .map(user -> {
                    UserDto userDto = new UserDto();
                    userDto.setId(user.getId());
                    userDto.setName(user.getName());
                    userDto.setLastName(user.getLastName());
                    userDto.setEmail(user.getEmail());
                    return userDto;
                })
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getUserById(Integer id) {
        User user = this.userDao.findById(id).orElse(null);

        if(user == null) {
            return null;
        }

        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setLastName(user.getLastName());
        userDto.setEmail(user.getEmail());

        return userDto;
    }

    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return this.userDao.findUserByEmail(email);
    }
}
