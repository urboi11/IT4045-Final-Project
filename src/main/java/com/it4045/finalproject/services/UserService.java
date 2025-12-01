package com.it4045.finalproject.services;

import java.util.List;
import org.springframework.stereotype.Service;
import com.it4045.finalproject.entities.UserComments;
import com.it4045.finalproject.exceptions.AccountDoesNotExistException;
import com.it4045.finalproject.exceptions.AccountExistsException;
import com.it4045.finalproject.exceptions.IncorrectPasswordException;
import com.it4045.finalproject.mappers.UserAndCommentsMapper;
import com.it4045.finalproject.dtos.LoginRequest;
import com.it4045.finalproject.dtos.SignUpRequest;
import com.it4045.finalproject.dtos.UserDto;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.repository.UserCommentRepository;
import com.it4045.finalproject.repository.UserRepository;
import lombok.AllArgsConstructor;

/**
 * Service class for managing user-related business logic.
 * Handles user creation, retrieval, and user comment operations.
 * @see IUserService
 * @see UserRepository
 * @see UserCommentRepository
 * JavaDoc by Gabby Herlocher and ethan Goudy
 * @author Enterprise App Development Final Project Group
 */
@AllArgsConstructor
@Service
public class UserService implements IUserService{


    private UserRepository userRepository;

    private UserCommentRepository userCommentRepository;

    private UserAndCommentsMapper userMapper;

    /**
     * Creates and persists a new user to the database.
     *
     * @param user the user entity to create
     * @return the saved user entity with generated ID
     * @author Enterprise App Development Final Project Group
     * JavaDoc proposed by Gabby Herlocher and edited by Ethan Goudy
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
     * @author Enterprise App Development Final Project Group
     * JavaDoc proposed by Gabby Herlocher and edited by Ethan Goudy
     */
    @Override
    public String getUserName(int userId) {
        return userRepository.findById(userId).get().getEmail();


    }

    /**
     * Retrieves all users from the database.
     *
     * @return list of all users
     * @author Enterprise App Development Final Project Group
     * JavaDoc proposed by Gabby Herlocher and edited by Ethan Goudy
     */
    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }


    /**
     * Retrieves all comments made by a specific user.
     * @param user a User entity used to find the comments
     * @return a list of UserComments made by the provided user
     * @author Enterprise App Development Final Project Group
     * Javadoc by Ethan Goudy
     */
    @Override
    public List<UserComments> getCommentsForUser(User user) {
        return userCommentRepository.findByUser(user);
    }

    /**
     * Retrieves a user with a specified ID from the database.
     * @param id the ID of the target user in Integer format
     * @return the target user as a User entity
     * @author Enterprise App Development Final Project Group
     * Javadoc by Ethan Goudy
     */
    @Override
    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }

    /**
     * Deletes a comment from the database, given its ID
     * @param commentId the ID of the target comment in Integer format
     * @author Enterprise App Development Final Project Group
     * Javadoc by Ethan Goudy
     */
    @Override
    public void deleteComment(Integer commentId) {
        userCommentRepository.deleteById(commentId);
    }

    /**
     * This method is used for user authentication by comparing credentials provided in the UI to database records.
     * Initial user matching is performed based on e-mail
     *
     * @param login a LoginRequest DTO containing the credentials provided by the user
     * @return a UserDTO containing the details of an authenticated user
     * @throws AccountDoesNotExistException if no matching user record is found
     * @throws IncorrectPasswordException if there is a matching user record, but the password does not match
     * @see LoginRequest
     * @see UserDto
     * @see com.it4045.finalproject.controllers.AuthController
     * @author Enterprise App Development Final Project Group
     * Javadoc by Ethan Goudy
     */
    @Override
    public UserDto login(LoginRequest login) {
        //Check to make sure email exists

        User user = userRepository.findByEmail(login.getUsername());

        if(user == null){
            throw new AccountDoesNotExistException("Account Does not Exist.");
        }
        //Check that password is valid.
        if(!user.getPassword().equals(login.getPassword())) {
            throw new IncorrectPasswordException("Incorrect Password.");
        }
        //Let the user in.
        return userMapper.UserDto(user);

    }

    /**
     * This method accepts a SignUpRequest DTO from the UI, processes it, and uses it to save a new record to the database assuming there isn't already a user with that email.
     * @param signUp a signUpRequest DTO containing the information provided by the user in the signup dialog
     * @throws AccountExistsException if a user already exists with the email provided
     * @see SignUpRequest
     * @see com.it4045.finalproject.controllers.AuthController
     * @author Enterprise App Development Final Project Group
     * Javadoc by Ethan Goudy
     */
    @Override
    public void signUp(SignUpRequest signUp) {
        
            User userReturned = userRepository.findByEmail(signUp.getEmail());
            if(userReturned != null){
                throw new AccountExistsException("User already exists, did you want to log in?");
            }

            User user = new User().builder()
            .firstname(signUp.getFirstName())
            .lastname(signUp.getLastName())
            .email(signUp.getEmail())
            .password(signUp.getPassword())
            .role("User").build();

            userRepository.save(user);
        
    }

    /**
     * Finds and returns a user record from the database given a target email address.
     * @param email The target email address in String form.
     * @return A User entity mathing the target email.
     */
    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    



}