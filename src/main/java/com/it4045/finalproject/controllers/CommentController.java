package com.it4045.finalproject.controllers;

import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;
import com.it4045.finalproject.services.CourseService;
import com.it4045.finalproject.services.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RestController
@RequestMapping("/comments")
@AllArgsConstructor

public class CommentController {
    private UserService userService;
    private CourseService courseService;

    @PostMapping("/")
    public String PostComment(@RequestParam("comment") String comment, @PathVariable Integer id, HttpSession session, RedirectAttributes redirectAttributes) {
        User postingUser = userService.findByEmail(session.getAttribute("CurrentUser").toString());
        Course targetCourse = courseService.getCourseById(id);
        try{
            courseService.commentOnCourse(comment, postingUser, targetCourse);
            return "redirect:/courses/{id}";
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("CommentErrorMessage", "Error: Comment cannot be empty.");
            return "redirect:/courses/{id}";
        }



    }
        //NOTE: Auth provides user validation
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteComment(@PathVariable int id, HttpSession session) {
        //Check if user owns
        User checkUser = userService.findByEmail(session.getAttribute("CurrentUser").toString());
        if (session.getAttribute("CurrentUser") != null ) {
            courseService.deleteComment(id);
            return ResponseEntity.ok().build();
        }
        else return ResponseEntity.status(403).build(); // Forbidden
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