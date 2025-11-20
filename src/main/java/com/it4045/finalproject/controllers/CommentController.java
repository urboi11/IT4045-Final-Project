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

@RestController
@RequestMapping("/comments")
@AllArgsConstructor

public class CommentController {
    private UserService userService;
    private CourseService courseService;

    @PostMapping("/")
    public ResponseEntity<UserComments> PostComment(@RequestParam("comment") String comment, @PathVariable Integer id, HttpSession session) {
        User postingUser = userService.findByEmail(session.getAttribute("CurrentUser").toString());
        Course targetCourse = courseService.getCourseById(id);
       if (comment.length() > 0){ // This should validate more things
               UserComments createdComment = new UserComments();
                createdComment.setComment(comment);
                createdComment.setUser(postingUser);
                createdComment.setCourse(targetCourse);
                courseService.commentOnCourse(comment, postingUser, targetCourse);
                return ResponseEntity.ok(createdComment);
       }
        else return ResponseEntity.badRequest().build();


    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> DeleteComment(@PathVariable int id, HttpSession session) {
        //Check if user owns
        User checkUser = userService.findByEmail(session.getAttribute("CurrentUser").toString());
        UserComments checkComment = null; // Replace with actual comment retrieval logic
        boolean userOwnsComment = false; // Replace with actual ownership check logic

        if (userOwnsComment) {
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