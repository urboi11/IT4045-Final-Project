package com.it4045.finalproject.controllers;

import java.util.List;

import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.services.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;

/**
 * Controller for handling course-related interactions, such as listing posted courses, searching for courses,
 * adding ratings, and creating or deleting courses as an administrator.
 * Uses service methods from CourseService to interact with the database.
 * @see CourseService
 * @author Enterprise App Development Final Project Group
 */


@Controller
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {
    private final CourseService courseService;

    // gets all courses and is the default list view

    /**
     * Retrieves a list of all currently posted courses to be listed on the page.
     * Checks if a user is logged in before proceeding; if not, redirects to the login page and displays an error message
     * @param model The target model to attach the list of courses to
     * @param session The HTTP session, where information about the current user is stored
     * @param redirectAttributes Used to attach an error message to the redirect
     * @return A redirect to the course list (if a user is logged in) or the login page (if not)
     * @author Enterprise App Development Final Project Group
     */
    @GetMapping
    public String getAllCourses(Model model, HttpServletRequest session, RedirectAttributes redirectAttributes) {
        if(session.getSession().getAttribute("CurrentUser") == null) {
            redirectAttributes.addFlashAttribute("LoginError", "You need to be logged in to view that page.");
            return "redirect:/auth/login";
        }
        List<Course> courses = courseService.getCourses();
        model.addAttribute("courses", courses);
        return "courses/list";
    }

    /**
     * Searches for courses based on a provided course number.
     * If the search box is empty, the default behavior is to show the entire list.
     * Displays a status message if a search produces no results.
     * Uses the searchCourses service method to retrieve matches.
     * @param courseNum The target course number, as a search parameter
     * @param model The target model for attachment of results
     * @return A redirect to the course list, with either the results or  a status message attached
     */
    // search all the courses based on course number (i.e. IT4045C)
    @GetMapping("/search")
    public String searchCourses(@RequestParam(required = false) String courseNum, Model model) {
        

        // if the search box is empty, show all the courses
        if (courseNum == null || courseNum.trim().isEmpty()) {
            return "redirect:/courses";
        }

        List<Course> courses = courseService.searchCourses(courseNum);
        // if no courses with the number found, return the error message
        if (courses.isEmpty())
            model.addAttribute("message", "Hmm, we couldn't find any course with that ID.");

        model.addAttribute("courses",courses);
        return "courses/list";
    }

    // returns the course details to the page
    @GetMapping("/{id}")
    public String getCourseDetails(@PathVariable Integer id, Model model, HttpServletRequest session) {
        
        var course = courseService.getCourseById(id);
        model.addAttribute("course", course);

        // to maintain the list of courses on the side
        List<Course> courses = courseService.getCourses();
        model.addAttribute("courses", courses);

        return "courses/list";
    }

    @PostMapping
    public String createCourse(@ModelAttribute Course course, RedirectAttributes redirectAttributes, HttpServletRequest session) {
        try {
            courseService.createCourse(course);
        }
        catch(Exception e) {
            redirectAttributes.addFlashAttribute("createError", e.getMessage());
            return "redirect:/users/profile";
        }
        return "redirect:/users/profile";
    }

    @PostMapping("/delete/{id}")
    public String deleteCourse(@PathVariable Integer id, RedirectAttributes redirectAttributes, HttpServletRequest session) {
        courseService.deleteCourse(id);
        return "redirect:/users/profile";
    }

    @PostMapping("/{id}/addrating")
    public String addRating(@RequestParam("rating") int rating, @PathVariable Integer id, HttpServletRequest session) {
        try {
            courseService.calculateRating(id, rating);
        }
        catch(Exception e) {
            return  "redirect:/courses/{id}";
        }
        return  "redirect:/courses/{id}";
    }


}