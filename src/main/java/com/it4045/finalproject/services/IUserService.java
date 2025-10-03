package com.it4045.finalproject.services;

import java.util.List;

import com.it4045.finalproject.data.UserCommentsDTO;
import com.it4045.finalproject.data.UserDTO;
import com.it4045.finalproject.entities.User;

public interface IUserService {
    
    public UserDTO createUser(User user);

    public String getUserName(int userId);

    public List<UserDTO> getUsers();
    
    public List<UserCommentsDTO> getCommentsForUser(UserDTO user);
}
