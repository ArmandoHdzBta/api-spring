package com.nando.apiExample.model.dao;

import com.nando.apiExample.model.dto.UserDto;
import com.nando.apiExample.model.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface IUserDao extends CrudRepository<User, Integer> {
    User findUserByEmail(String email);

    User save(UserDto userDto);
}
