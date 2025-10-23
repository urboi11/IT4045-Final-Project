package com.it4045.finalproject.services;

import java.util.List;

import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;

public interface IUserService {
    public User createUser(User user);

    public String getUserName(int userId);

    public List<User> getUsers();

    public List<UserComments> getCommentsForUser(User user);

}