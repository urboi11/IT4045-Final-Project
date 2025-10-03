package com.it4045.finalproject.services.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it4045.finalproject.data.UserCommentsDTO;
import com.it4045.finalproject.data.UserDTO;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.services.IUserService;
import com.it4045.finalproject.repositories.UserRepository;
import com.it4045.finalproject.mappers.UserMapper;


@Service
public class UserService implements IUserService{

    
    private UserRepository userRepository;


    private UserMapper userMapper;

    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserDTO createUser(User user) {
        
        userRepository.save(user);
        return userMapper.DTOConverter(user);
    }

    @Override
    public String getUserName(int userId) {
        User userReturned = userRepository.getReferenceById(userId);
        return userReturned.getUserEmail();
    }

    @Override
    public List<UserDTO> getUsers() {
        List<User> userList = userRepository.findAll();
        List<UserDTO> userDtoList = new ArrayList();
        for(User user: userList){
            UserDTO userDto = userMapper.DTOConverter(user);

            userDtoList.add(userDto);
        }
        return userDtoList;
    }

    //TODO: Work on this one. 
    @Override
    public List<UserCommentsDTO> getCommentsForUser(UserDTO user) {
        
    }
    
}
