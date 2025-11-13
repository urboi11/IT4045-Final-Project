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

@Controller 
public class AuthController {

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
    public String handleSignUp(@ModelAttribute("SignUpRequest") SignUpRequest formData) {
        return null;
    }

    // @PostMapping
    // public ResponseEntity<User> login(@RequestBody LoginRequest request) {
    //     return null;
        // Validate credentials
        // Authenticate user
        // Return user details or token
    // }
}