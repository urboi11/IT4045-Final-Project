package com.it4045.finalproject.controllers;

import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.repository.UserRepository;
import com.it4045.finalproject.services.CourseService;
import com.it4045.finalproject.services.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;



@Controller
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {
    private CourseService courseService;

    // gets all courses and is the default list view
    @GetMapping
    public String getAllCourses(Model model, HttpServletRequest session) {
        
        if(session.getSession().getAttribute("CurrentUser").equals(null)) {
            return "redirect:/auth/login";
        }

        List<Course> courses = courseService.getCourses();
        model.addAttribute("courses", courses);
        return "courses/list";
    }

    // search all the courses based on course number (i.e. IT4045C)
    @GetMapping("/search")
    public String searchCourses(@RequestParam(required = false) String courseNum, Model model, HttpServletRequest session) {
        
        if(session.getSession().getAttribute("CurrentUser").equals(null)) {
            return "redirect:/auth/login";
        }

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

        if(session.getSession().getAttribute("CurrentUser").equals(null)) {
            return "redirect:/auth/login";
        }
        
        var course = courseService.getCourseById(id);
        model.addAttribute("course", course);

        // to maintain the list of courses on the side
        List<Course> courses = courseService.getCourses();
        model.addAttribute("courses", courses);

        return "courses/list";
    }

    @PostMapping
    public String createCourse(@ModelAttribute Course course, RedirectAttributes redirectAttributes, HttpServletRequest session) {
        
        if(session.getSession().getAttribute("CurrentUser").equals(null)) {
            return "redirect:/auth/login";
        }
        courseService.createCourse(course);
        // redirects to the user admin page
        return "redirect:/users";
    }

    @PostMapping("/{id}/addrating")
    public String addRating(@RequestParam("rating") String rating, @PathVariable Integer id, HttpServletRequest session) {
        if(session.getSession().getAttribute("CurrentUser").equals(null)) {
            return "redirect:/auth/login";
        }
        courseService.calculateRating(id, rating);
        return  "redirect:/courses/{id}";
    }

    @PostMapping("/{id}/addcomment")
    public String postComment(@RequestParam("commentInput") String comment, @PathVariable Integer id, HttpServletRequest session) {
        if(session.getSession().getAttribute("CurrentUser").equals(null)) {
            return "redirect:/auth/login";
        }
        
        // need to also use the session in here to make sure the user is correct
        // currently user is set to null so this needs to be changed once UserController
        // is implemented
        var course =  courseService.getCourseById(id);
        courseService.commentOnCourse(comment, null, course);
        return "redirect:/courses/{id}";
    }

}