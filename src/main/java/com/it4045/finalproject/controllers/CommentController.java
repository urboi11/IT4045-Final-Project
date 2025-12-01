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

/**
 * Controller for handling posting and deletion of comments.
 * This functionality was originally implemented in the Course controller, but has been rebuilt here to enhance separation of responsibilities.
 * @author Enterprise App Development Final Project Group
 */
@Controller
@RequestMapping("/comments")
@AllArgsConstructor

public class CommentController {
    private UserService userService;
    private CourseService courseService;

    /**
     * Used for adding a comment to a course.
     * @param comment
     *         A string containing the text contents of the comment
     * @param courseId
     *          An integer ID corresponding to the target course for the comment
     * @param session
     *          HttpServletRequest containing information about the current user from the browser
     * @param redirectAttributes
     *          Stores attributes for page redirects
     * @return A redirect to the course page, with an error message if an exception occurs
     *
     * @see CourseService#commentOnCourse(String, User, Course)
     *This method uses the CommentOnCourse service method to interact with the reporitories and save comments
     *
     * @see UserService#findByEmail(String)
     * This method uses the FindByEmail service method to associate a user with the comment
     *
     * @author Enterprise App Development Final Project Group
     *
     * Possible Exceptions: EmptyCommentException
     */
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

    /**
     * This method deletes a comment, removing it from the database.
     *
     * @param id
     *        An integer ID corresponding to the target comment for deletion
     * @return A redirect to the user profile page
     *
     * @see UserService#deleteComment(Integer)
     * This method uses the DeleteComment service method to interact with the repository and delete comments
     *
     * @author Enterprise App Development Final Project Group
     */
        //NOTE: Authorization provides user validation
    @DeleteMapping("/{id}")
    public String DeleteComment(@PathVariable int id) {

            userService.deleteComment(id);
            return "redirect:/users/profile";


    }



}