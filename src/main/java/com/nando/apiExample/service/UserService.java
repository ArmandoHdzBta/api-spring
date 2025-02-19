package com.nando.apiExample.service;

import com.nando.apiExample.model.dao.IUserDao;
import com.nando.apiExample.model.dto.UserDto;
import com.nando.apiExample.model.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements IUser {
    private final IUserDao userDao;

    public UserService(IUserDao userDao){
        this.userDao = userDao;
    }

    @Transactional
    @Override
    public User save(UserDto user) {
        return this.userDao.save(user);
    }

    @Transactional(readOnly = true)
    public User getUserById(Integer id) {
        return this.userDao.findById(id).orElse(null);
    }

    @Transactional(readOnly = true)
    public User getUserByEmail(String email) {
        return this.userDao.findUserByEmail(email);
    }
}
