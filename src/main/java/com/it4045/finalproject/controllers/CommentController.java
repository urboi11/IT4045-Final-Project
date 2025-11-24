package com.it4045.finalproject.controllers;

import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;
import com.it4045.finalproject.services.CourseService;
import com.it4045.finalproject.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/comments")
@AllArgsConstructor

public class CommentController {
    private UserService userService;
    private CourseService courseService;

    @PostMapping("/{courseId}/addcomment")
    public String PostComment(@RequestParam("comment") String comment, @PathVariable Integer courseId, HttpSession session, RedirectAttributes redirectAttributes) {
        User postingUser = userService.findByEmail(session.getAttribute("CurrentUser").toString());
        Course targetCourse = courseService.getCourseById(courseId);
        try{
            courseService.commentOnCourse(comment, postingUser, targetCourse);
            return "redirect:/courses/"+courseId;
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("CommentErrorMessage", "Error: Comment cannot be empty.");
            return "redirect:/courses/"+courseId;
        }



    }
        //NOTE: Auth provides user validation
    @DeleteMapping("/{id}")
    public String DeleteComment(@PathVariable int id, HttpSession session, RedirectAttributes redirectAttributes) {
        if (session.getAttribute("CurrentUser") != null ) {
            courseService.deleteComment(id);
            return "redirect:/courses";
        }
        else{
            redirectAttributes.addFlashAttribute("CommentErrorMessage", "You are not logged in.");
            return "redirect:/courses"; }// Forbidden
    }


    // @PostMapping("/")
    // public ResponseEntity<UserComment> postComment(@RequestBody CommentRequest request) {
    //     // Validate input
    //     // Save comment to database
    //     // Return posted comment
    // }

    // @DeleteMapping("/{id}")
    // public ResponseEntity<Void> deleteComment(@PathVariable int id) {
    //     // Check if user owns comment
    //     // Delete comment
    //     // Return success
    // }
}