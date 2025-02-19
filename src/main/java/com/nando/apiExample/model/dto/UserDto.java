package com.nando.apiExample.model.dto;

public class UserDto {
    private Integer id;

    private String name;

    private String lastName;

    private String email;

    public UserDto(Integer id, String name, String lastName, String email) {    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }
}
