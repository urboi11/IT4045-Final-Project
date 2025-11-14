package com.it4045.finalproject.services;

import java.util.List;

import com.it4045.finalproject.dtos.RegisterUserRequest;
import com.it4045.finalproject.dtos.UserDto;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;

public interface IUserService {
    public UserDto createUser(String firstName, String lastName, String email, String password);

    public String getUserName(int userId);

    public List<User> getUsers();

    public List<UserComments> getCommentsForUser(User user);

}