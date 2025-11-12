package com.it4045.finalproject.controllers;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.it4045.finalproject.entities.SignUpRequest;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.repository.UserRepository;
import com.it4045.finalproject.security.WebSecurityConfig;
import com.it4045.finalproject.services.UserService;

import lombok.AllArgsConstructor;

@Controller 
@AllArgsConstructor
public class AuthController {

    private final UserService userService;

    private final WebSecurityConfig securityConfig;

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }


    @GetMapping("/signup")
    public String signUpPage(Model model) {
        model.addAttribute("SignUpRequest", new SignUpRequest());
        return "signup";
    }
    
    @PostMapping("/signup")
    public String handleSignUp(@ModelAttribute("SignUpRequest") SignUpRequest userData, Model model) {
        //TODO: Test if this logic is correct.
        if(userService.getUserEmail(userData.getEmail()) != null){
            model.addAttribute("AlreadyExists", true);
            return "login";
        }
        else{
            User user = new User();
            user.setUserFirstName(userData.getFirstName());
            user.setUserLastName(userData.getLastName());
            user.setUserEmail(userData.getEmail());
            user.setUserPass(securityConfig.passwordEncoder().encode(userData.getPassword()));
            user.setRole("User");
            //Figure out this logic.
            try{
                userService.createUser(user);
                return "login";
            }
            //TODO: Something failed.
            catch(Exception E){
                model.addAttribute("ErrorOccurred", true);      
                return "login";
            }

        }


    }
}