package com.it4045.finalproject.mappers;


import com.it4045.finalproject.dtos.UserDto;
import com.it4045.finalproject.dtos.CommentsDto;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserAndCommentsMapper {
    UserDto UserDto(User user);
    CommentsDto CommentsDto(UserComments comment);
}
