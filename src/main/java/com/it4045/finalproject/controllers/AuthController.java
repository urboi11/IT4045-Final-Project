package com.it4045.finalproject.controllers;

import com.it4045.finalproject.dtos.RegisterUserRequest;
import com.it4045.finalproject.dtos.UserDto;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.exceptions.UserEmailExistsException;
import com.it4045.finalproject.exceptions.WeakPasswordException;
import com.it4045.finalproject.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

//Changed from @RestController to @Controller
@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    // @PostMapping("/login")
    // public ResponseEntity<User> login(@RequestBody LoginRequest request) {
    //     // Validate credentials
    //     // Authenticate user
    //     // Return user details or token
    // }

    // @PostMapping("/signup")
    // public ResponseEntity<User> signup(@RequestBody SignupRequest request) {
    //     // Check if email already exists
    //     // Create new user
    //     // Return success response
    // }

    // stub for registering a user
    @PostMapping("/register")
    public String register(@ModelAttribute RegisterUserRequest registerUserRequest,
                               HttpSession httpSession, RedirectAttributes redirectAttributes) {
        try {
            // Call service layer for authentication
            UserDto user = userService.createUser(registerUserRequest.getUserFirstName(),
                    registerUserRequest.getUserLastName(),
                    registerUserRequest.getUserEmail(),
                    registerUserRequest.getUserPass());

            redirectAttributes.addFlashAttribute("successMessage", "Account Created Successfully! Please log in.");
            return "redirect:/login";

        } catch (WeakPasswordException | IllegalArgumentException |UserEmailExistsException e) {
            redirectAttributes.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/register";
        }


    }
}