package com.it4045.finalproject.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.it4045.finalproject.entities.UserComments;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.repository.UserCommentRepository;
import com.it4045.finalproject.repository.UserRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class  UserService implements IUserService{


    private UserRepository userRepository;

    private UserCommentRepository userCommentRepository;
    
    @Override
    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public String getUserName(int userId) {
        return userRepository.findById(userId).get().getUserEmail();


    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<UserComments> getCommentsForUser(User user) {
        return userCommentRepository.findByUser(user);
    }


    // @Override 
    // public List<UserComments> getCommentsForUser(User user) {
    //     return null;
    // }
}