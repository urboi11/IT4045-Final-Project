package com.it4045.finalproject.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.services.CourseService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;



@Controller
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {
    private CourseService courseService;

    // gets all courses and is the default list view
    @GetMapping
    public String getAllCourses(Model model, HttpServletRequest session) {

        List<Course> courses = courseService.getCourses();
        model.addAttribute("courses", courses);
        return "courses/list";
    }

    // search all the courses based on course number (i.e. IT4045C)
    @GetMapping("/search")
    public String searchCourses(@RequestParam(required = false) String courseNum, Model model, HttpServletRequest session) {
        

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
        
        courseService.createCourse(course);
        // redirects to the user admin page
        return "redirect:/users";
    }

    @PostMapping("/{id}/addrating")
    public String addRating(@RequestParam("rating") String rating, @PathVariable Integer id, HttpServletRequest session) {
        courseService.calculateRating(id, rating);
        return  "redirect:/courses/{id}";
    }

    @PostMapping("/{id}/addcomment")
    public String postComment(@RequestParam("commentInput") String comment, @PathVariable Integer id, HttpServletRequest session) {
        // need to also use the session in here to make sure the user is correct
        // currently user is set to null so this needs to be changed once UserController
        // is implemented
        var course =  courseService.getCourseById(id);
        courseService.commentOnCourse(comment, null, course);
        return "redirect:/courses/{id}";
    }

}