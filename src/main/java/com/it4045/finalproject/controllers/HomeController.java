package com.it4045.finalproject.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * A controller for handling the user's initial access to the webpage. Redirects to the login page if no user is logged in,
 * or to the courses page if a user is already logged in.
 *
 * @author Enterprise App Development Final Project Group
 */
@Controller
public class HomeController {
    /**
     * Detects if a user is logged in. Redirects to the courses page if there is, or to the login page if not.
     * @param session the HTTP session containing information about the current user. This function checks for the presence of a "CurrentUser" attribute.
     * @return A redirect to /courses if a user is logged in, or to /auth/login if not.
     */
    @GetMapping("/")
    public String redirectToLogin(HttpServletRequest session){
        if(session.getSession().getAttribute("CurrentUser") != null) {
            return "redirect:/courses";
        }
        return "redirect:/auth/login";
    }
}
