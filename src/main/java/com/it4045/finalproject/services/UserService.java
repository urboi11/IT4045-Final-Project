package com.it4045.finalproject.services;

import java.util.List;

import com.it4045.finalproject.dtos.UserDto;
import com.it4045.finalproject.exceptions.UserEmailExistsException;
import com.it4045.finalproject.exceptions.WeakPasswordException;
import com.it4045.finalproject.mappers.UserMapper;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.it4045.finalproject.entities.UserComments;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.repository.UserCommentRepository;
import com.it4045.finalproject.repository.UserRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class  UserService implements IUserService{

    private final UserRepository userRepository;
    private final UserCommentRepository userCommentRepository;
    private final UserMapper userMapper;


    /**
     *Registers a new user after validating inputs.
     *
     * @param firstName User first name
     * @param lastName User last name
     * @param email User new email
     * @param password User new password
     * @return userDto
     * @exception IllegalArgumentException bad data format
     * @exception UserEmailExistsException User email exists
     * @exception WeakPasswordException User's password invalid
     */
    @Transactional
    @Override
    public UserDto createUser(String firstName, String lastName, String email, String password) {
       if (firstName.isBlank()) {
           throw new IllegalArgumentException("First name is required");
       }

        if (lastName.isBlank()) {
            throw new IllegalArgumentException("Last name is required");
        }

        if (email.isBlank() || !email.matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")){
            throw new IllegalArgumentException("A valid email is required");
        }
        if (password.isBlank()){
            throw new IllegalArgumentException("Password is required");
        }

        if (userRepository.findByEmail(email).isPresent()){
            throw new UserEmailExistsException("This email already exists");
        }

        // checks if user password is strong enough
        validatePassword(password);

        User user = new User();
        user.setUserFirstName(firstName);
        user.setUserLastName(lastName);
        user.setUserEmail(email);
        user.setUserPass(password);

        return userMapper.toDto(userRepository.save(user));
    }

    /**
     * Gets username by user ID.
     *
     * @param userId
     *
     *
     * @return user's email
     */
    @Override
    public String getUserEmail(int userId) {
        return userRepository.findById(userId).get().getUserEmail();
    }

    /**
     * Gets all users in the system.
     *
     * @return list of users
     */
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    /** Gets comments for a specific user.
     *
     * @param user User entity
     *
     * @return list of user comments
     */
    @Override
    public List<UserComments> getCommentsForUser(User user) {
        return userCommentRepository.findByUser(user);
    }


    // @Override 
    // public List<UserComments> getCommentsForUser(User user) {
    //     return null;
    // }

    /**
     *Validates password strength against local policy.
     *
     * @param password password to validate requirements
     * @throws WeakPasswordException if any rule is violated
     */
    public void validatePassword(String password){
        if (password == null || password.length() < 8) {
            throw new WeakPasswordException("Password must be at least 8 characters long");
        }
        if (!password.matches(".*[A-Z].*")) {
            throw new WeakPasswordException("Password must contain at least one uppercase letter");
        }
        if (!password.matches(".*[a-z].*")) {
            throw new WeakPasswordException("Password must contain at least one lowercase letter");
        }
        if (!password.matches(".*\\d.*")) {
            throw new WeakPasswordException("Password must contain at least one digit");
        }
    }
}