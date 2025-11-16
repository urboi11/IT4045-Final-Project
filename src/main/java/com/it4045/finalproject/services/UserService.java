package com.it4045.finalproject.services;

import java.util.List;

import org.springframework.stereotype.Service;
import com.it4045.finalproject.entities.UserComments;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.repository.UserCommentRepository;
import com.it4045.finalproject.repository.UserRepository;

import lombok.AllArgsConstructor;

/**
 * Service class for managing user-related business logic.
 * Handles user creation, retrieval, and user comment operations.
 */
@AllArgsConstructor
@Service
public class  UserService implements IUserService{


    private UserRepository userRepository;

    private UserCommentRepository userCommentRepository;
    
    /**
     * Creates and persists a new user to the database.
     *
     * @param user the user entity to create
     * @return the saved user entity with generated ID
     */
    @Override
    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    /**
     * Retrieves the email address of a user by their ID.
     *
     * @param userId the ID of the user
     * @return the user's email address
     * @throws java.util.NoSuchElementException if no user exists with the given ID
     */
    @Override
    public String getUserName(int userId) {
        return userRepository.findById(userId).get().getUserEmail();


    }

    /**
     * Retrieves all users from the database.
     *
     * @return list of all users
     */
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    /**
     * Retrieves all comments made by a specific user.
     *
     * @param user the user whose comments to retrieve
     * @return list of comments made by the user
     */
    @Override
    public List<UserComments> getCommentsForUser(User user) {
        return userCommentRepository.findByUser(user);
    }


    // @Override 
    // public List<UserComments> getCommentsForUser(User user) {
    //     return null;
    // }
}