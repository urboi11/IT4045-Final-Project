package com.it4045.finalproject.services;

import java.util.List;

import com.it4045.finalproject.data.UserCommentsDTO;
import com.it4045.finalproject.data.UserDTO;

public interface IUserService {
    
    public UserDTO createUser(String userFirstName, String userLastName, String userEmail, String userPass, boolean isAdmin);

    public String getUserName(int userId);

    public List<UserDTO> getUsers();
    
    public List<UserCommentsDTO> getCommentsForUser(UserDTO user);
}
