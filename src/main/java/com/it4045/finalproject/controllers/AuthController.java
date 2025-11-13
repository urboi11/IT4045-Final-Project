package com.it4045.finalproject.controllers;

import java.util.Optional;

import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.it4045.finalproject.dtos.LoginRequest;
import com.it4045.finalproject.dtos.SignUpRequest;
import com.it4045.finalproject.dtos.UserDto;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.exceptions.AccountDoesNotExistException;
import com.it4045.finalproject.exceptions.AccountExistsException;
import com.it4045.finalproject.exceptions.ErrorOnSignUpException;
import com.it4045.finalproject.exceptions.IncorrectPasswordException;
import com.it4045.finalproject.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.AllArgsConstructor;



@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginRequest", new LoginRequest());
        return "/auth/login";    
    }


    //TODO:
    @PostMapping("/login")
    public String login(@ModelAttribute("loginRequest") LoginRequest loginRequest, HttpServletRequest session) {

        try{
                userService.login(loginRequest);
                session.getSession().setAttribute("CurrentUser", loginRequest.getUsername());
                return "redirect:/courses";
        }
        catch(AccountDoesNotExistException | IncorrectPasswordException e) {
            
            return "redirect:/auth/login";
        }


    }
    

    @GetMapping("/signup")
    public String signUp(Model model){
        model.addAttribute("signUpRequest", new SignUpRequest());
        return "/auth/signup";
    }

    @PostMapping("/signup")
    public String signUp(@ModelAttribute("signUpRequest") SignUpRequest request, Model model, RedirectAttributes redirectAttributes) {
       
       try{
            userService.signUp(request);
            return "redirect:/auth/login";
        }
        catch(AccountExistsException e){
            redirectAttributes.addFlashAttribute("AlreadyExists", new AccountExistsException("Account already exists"));
            return "redirect:/auth/login";    
        }

    }

    //TODO: Implement Logout Feature. 
    
}