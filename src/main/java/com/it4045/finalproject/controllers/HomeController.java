package com.it4045.finalproject.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    
    @GetMapping("/")
    public String redirectToLogin(HttpServletRequest session){
        if(session.getSession().getAttribute("CurrentUser") != null) {
            return "redirect:/courses";
        }
        return "redirect:/auth/login";
    }
}
