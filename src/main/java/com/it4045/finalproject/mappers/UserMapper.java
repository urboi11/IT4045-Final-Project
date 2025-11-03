package com.it4045.finalproject.mappers;

import org.mapstruct.Mapper;

import com.it4045.finalproject.dtos.UserDto;
import com.it4045.finalproject.entities.User;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toDto(User user);
}
