package com.it4045.finalproject.mappers;

import com.it4045.finalproject.dtos.UserDto;
import com.it4045.finalproject.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel="spring")
public interface UserMapper {
    UserDto toDto(User user);
    User toEntity(UserDto userDto);

}
