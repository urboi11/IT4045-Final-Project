package com.it4045.finalproject.controllers;

import com.it4045.finalproject.entities.Course;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/users")
@AllArgsConstructor
public class UserController {

    // This method is for testing the createCourse method in CourseController
    @GetMapping
    public String showAdminPage(Model model) {
        // since the POST for courses is on the admin page, need to add course to model
        model.addAttribute("course", new Course());
        return "users/admin";
    }

    // @GetMapping("/{id}")
    // public ResponseEntity<User> getUser(@PathVariable int id) {
    //     // Fetch user from service
    //     // Return user profile
    // }

    // @GetMapping("/{id}/comments")
    // public ResponseEntity<List<UserComment>> getUserComments(@PathVariable int id) {
    //     // Retrieve all comments made by user
    //     // Return list of comments
    // }

    // @DeleteMapping("/comments/{commentId}")
    // public ResponseEntity<Void> deleteComment(@PathVariable int commentId) {
    //     // Validate ownership
    //     // Delete comment
    //     // Return success status
    // }
}