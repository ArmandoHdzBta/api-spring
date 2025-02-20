package com.nando.apiExample.service;

import com.nando.apiExample.model.dao.IUserDao;
import com.nando.apiExample.model.dto.UserDto;
import com.nando.apiExample.model.entity.User;
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
                .map(user -> new UserDto(
                        user.getId(),
                        user.getName(),
                        user.getLastName(),
                        user.getEmail()
                ))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public UserDto save(UserDto user) {
        User userEntity = new User();
        userEntity.setName(user.getName());
        userEntity.setLastName(user.getLastName());
        userEntity.setEmail(user.getEmail());

        User savedUser = this.userDao.save(userEntity);
        return new UserDto(savedUser.getId(), savedUser.getName(), savedUser.getLastName(), savedUser.getEmail());
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getUserById(Integer id) {
        User user = this.userDao.findById(id).orElse(null);
        return new UserDto(user.getId(), user.getName(), user.getLastName(), user.getEmail());
    }

    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return this.userDao.findUserByEmail(email);
    }
}
