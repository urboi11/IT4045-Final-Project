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

@AllArgsConstructor
@Service
public class UserService implements IUserService{


    private UserRepository userRepository;

    private UserCommentRepository userCommentRepository;

    private UserAndCommentsMapper userMapper;
    
    @Override
    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    @Override
    public String getUserName(int userId) {
        return userRepository.findById(userId).get().getEmail();


    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    

    @Override
    public List<UserComments> getCommentsForUser(User user) {
        return userCommentRepository.findByUser(user);
    }

    @Override
    public User getUser(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public void deleteComment(Integer commentId) {
        userCommentRepository.deleteById(commentId);
    }

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

    @Override
    public void signUp(SignUpRequest signUp) {
        
            User userReturned = userRepository.findByEmail(signUp.getEmail());
            if(userReturned != null){
                throw new AccountExistsException("User Already Exists Exception");
            }

            User user = new User().builder()
            .firstname(signUp.getFirstName())
            .lastname(signUp.getLastName())
            .email(signUp.getEmail())
            .password(signUp.getPassword())
            .role("User").build();

            userRepository.save(user);
        
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    



}