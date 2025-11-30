package com.it4045.finalproject.controllers;



import java.util.ArrayList;
import java.util.List;

import com.it4045.finalproject.services.CourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import com.it4045.finalproject.dtos.CommentsDto;
import com.it4045.finalproject.dtos.UserDto;
import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;
import com.it4045.finalproject.mappers.UserAndCommentsMapper;
import com.it4045.finalproject.repository.UserCommentRepository;
import com.it4045.finalproject.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@AllArgsConstructor
@Controller
@Slf4j
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    private final UserAndCommentsMapper mapper;
    private final UserCommentRepository commentRepository;
    private final CourseService courseService;

    @GetMapping("/profile")
    public String showProfilePage(HttpServletRequest session, Model model, RedirectAttributes redirectAttributes) {
        if(session.getSession().getAttribute("CurrentUser") == null) {
            redirectAttributes.addFlashAttribute("LoginError", "You need to be logged in to view that page.");
            return "redirect:/auth/login";
        }
        //Check for Current Role of user
        User user = userService.findByEmail(session.getSession().getAttribute("CurrentUser").toString());     

        model.addAttribute("userInfo", user);

        List<UserComments> commentsForUser = commentRepository.findByUser(user);
        model.addAttribute("comments", commentsForUser);
        
        model.addAttribute("isAdmin", false);
        if(user.getRole().equals("Admin")){
            model.addAttribute("isAdmin", true);

            model.addAttribute("course", new Course());
            model.addAttribute("allUsers", userService.getUsers());
            model.addAttribute("allCourses", courseService.getCourses());
        }

        return "/users/profile";
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable int id, HttpServletRequest session) {
        User user = userService.getUser(id);
        if(user == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(mapper.UserDto(user));

    }

    @GetMapping("/{id}/comments")
    public ResponseEntity<List<CommentsDto>> getUserComments(@PathVariable int id) {
        // Retrieve all comments made by user
        User user = userService.getUser(id);
        List<UserComments> comments =  userService.getCommentsForUser(user);
        List<CommentsDto> commentsDto = new ArrayList();

        for(UserComments comment : comments) {
            commentsDto.add(mapper.CommentsDto(comment));
        }
        return ResponseEntity.ok(commentsDto);
    }


}