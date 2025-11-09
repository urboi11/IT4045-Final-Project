package com.it4045.finalproject.controllers;

import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;
import com.it4045.finalproject.services.UserService;

import lombok.AllArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {


    private UserService userService; 

    // This method is for testing the createCourse method in CourseController
    @GetMapping
    public String showAdminPage(Model model) {
        // since the POST for courses is on the admin page, need to add course to model
        model.addAttribute("course", new Course());
        return "users/admin";
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id) {

        return ResponseEntity.ok(userService.getUser(id));
        
    }

    // @GetMapping("/{id}/comments")
    // public ResponseEntity<List<UserComments>> getUserComments(@PathVariable int id) {
    //     // Retrieve all comments made by user
    //     return ResponseEntity.ok(userService.getCommentsForUser());
    //     // Return list of comments
    // }

    // @DeleteMapping("/comments/{commentId}")
    // public ResponseEntity<Void> deleteComment(@PathVariable int commentId) {
        // Validate ownership
        // Delete comment
        // Return success status
    // }
}