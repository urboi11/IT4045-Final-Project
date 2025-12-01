package com.it4045.finalproject.services;

import java.util.List;
import java.util.Optional;

import com.it4045.finalproject.dtos.LoginRequest;
import com.it4045.finalproject.dtos.SignUpRequest;
import com.it4045.finalproject.dtos.UserDto;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;

/**
 * A service interface defining methods for managing User entities and their related operations.
 * @see User
 * @see UserComments
 * @see com.it4045.finalproject.controllers.UserController
 * @author Enterprise Application Development Final Project Group
 */
public interface IUserService {
    public User createUser(User user);

    public String getUserName(int userId);

    public List<User> getUsers();

    public List<UserComments> getCommentsForUser(User user);

    public User getUser(Integer id);

    public void deleteComment(Integer commentId);

    public UserDto login(LoginRequest login);
    
    public void signUp(SignUpRequest signUp);

    public User findByEmail(String email);
}