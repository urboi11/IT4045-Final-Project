package com.it4045.finalproject.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.it4045.finalproject.dtos.LoginRequest;
import com.it4045.finalproject.dtos.SignUpRequest;
import com.it4045.finalproject.exceptions.AccountDoesNotExistException;
import com.it4045.finalproject.exceptions.AccountExistsException;
import com.it4045.finalproject.exceptions.IncorrectPasswordException;
import com.it4045.finalproject.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

/**
 * A controller for handling user authentication and creation. The security provided by this class is used to ensure
 * valid results in other methods.
 *
 * @see UserService
 * @see LoginRequest
 * @see SignUpRequest
 * @author Enterprise App Development Final Project Group
 * JavaDoc by Ethan Goudy
 */

@Controller
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    /**
     * Adds a LoginRequest object to the model and returns a redirect.
     * @param model The target model to attach the LoginRequest to.
     * @return A redirect to /auth/login
     * @author Enterprise App Development Final Project Group
     */
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginRequest", new LoginRequest());
        return "/auth/login";    
    }

    /**
     * Processes a LoginRequest after submission, invoking the Login service method to validate the credentials provided.
     * If credentials are valid, writes user information to the session and redirects. Catches exceptions and redirects with error messages when provided
     * invalid credentials.
     * @param loginRequest the LoginRequest DTO containing the submitted credentials.
     * @param session the session to write information to.
     * @param redirectAttributes Used to attach error messages to redirect.
     * @param model the target model.
     * @return a redirect to /courses if a user successfully logs in, otherwise returns to /auth/login with an error message.
     */
    @PostMapping("/login")
    public String login(@ModelAttribute("loginRequest") LoginRequest loginRequest, HttpServletRequest session, RedirectAttributes redirectAttributes, Model model) {
        try{
                userService.login(loginRequest);
                session.getSession().setAttribute("CurrentUser", loginRequest.getUsername());
                return "redirect:/courses";
        }
        catch(AccountDoesNotExistException | IncorrectPasswordException e) {
            redirectAttributes.addFlashAttribute("LoginError", e.getMessage());
            return "redirect:/auth/login";
        }

    }

    /**
     * Used to connect a SignUpRequest to the relevant form on the signup page.
     * @param model The target model for attaching the SignUpRequest DTO
     * @return A redirect to the signup page
     * @see SignUpRequest
     * @author Enterprise App Development Final Project Group
     */
    @GetMapping("/signup")
    public String signUp(Model model){
        model.addAttribute("signUpRequest", new SignUpRequest());
        return "/auth/signup";
    }

    /**
     * Processes a SignUpRequest DTO submitted from the signup page, using the SignUp service method
     * to validate and create a new user account. Catches exceptions due to duplicated user credentials.
     * @param request the DTO containing the proposed user informaton
     * @param model The model the DTO is attached to
     * @param redirectAttributes Usecd to attach status messages to the redirect
     * @throws AccountExistsException when a proposed user's information is checked
     * against existing records and found to be a duplicate of an existing user
     * @return a redirect to the login page with a status message attached via flash attribute
     * @see SignUpRequest
     * @see UserService#signUp(SignUpRequest)
     * @author Enterprise App Development Final Project Group
     */
    @PostMapping("/signup")
    public String signUp(@ModelAttribute("signUpRequest") SignUpRequest request, Model model, RedirectAttributes redirectAttributes) {
       
       try{
            userService.signUp(request);
            redirectAttributes.addFlashAttribute("SignUpSuccess", "Successfully signed up! Log in to get started.");
            return "redirect:/auth/login";
        }
        catch(AccountExistsException e){
            redirectAttributes.addFlashAttribute("LoginError", e.getMessage());
            return "redirect:/auth/login";    
        }

    }

    /**
     * Invalidates the current session records, logging the current user out.
     * Redirects to the login page and provides a status message via flash attribute.
     * @param session the current HttpServletRequest session that needs to be reset
     * @param redirectAttributes used to attach the status message
     * @return a redirect to the login page
     * @author Enterprise App Development Final Project Group
     */
    @GetMapping("/logout")
    public String logout(HttpServletRequest session, RedirectAttributes redirectAttributes) {
        session.getSession().invalidate();
        redirectAttributes.addFlashAttribute("logoutSuccess", "You have been logged out successfully");
        return "redirect:/auth/login";
    }
    
}