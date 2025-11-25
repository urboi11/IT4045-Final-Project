package com.it4045.finalproject.controllers;

import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;
import com.it4045.finalproject.services.CourseService;
import com.it4045.finalproject.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
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
    public String PostComment(@RequestParam("comment") String comment, @PathVariable Integer courseId, HttpServletRequest session, RedirectAttributes redirectAttributes) {
        User postingUser = userService.findByEmail(session.getSession().getAttribute("CurrentUser").toString());
        Course targetCourse = courseService.getCourseById(courseId);
        try{
            courseService.commentOnCourse(comment, postingUser, targetCourse);
            return "redirect:/courses/"+courseId;
        }
        catch (Exception e){
            redirectAttributes.addFlashAttribute("CommentErrorMessage", e.getMessage());
            return "redirect:/courses/"+courseId;
        }



    }
        //NOTE: Authorization provides user validation
    @DeleteMapping("/{id}")
    public String DeleteComment(@PathVariable int id) {

            userService.deleteComment(id);
            return "redirect:/users/profile";


    }



}