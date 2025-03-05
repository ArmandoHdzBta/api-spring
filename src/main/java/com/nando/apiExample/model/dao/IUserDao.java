package com.nando.apiExample.model.dao;

import com.nando.apiExample.model.dto.UserDto;
import com.nando.apiExample.model.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IUserDao extends CrudRepository<User, Integer> {
    User findUserByEmail(String email);
    @Override
    List<User> findAll();
    User save(UserDto userDto);
    //User update(UserDto userDto);
}
