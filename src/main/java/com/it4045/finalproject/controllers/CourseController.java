package com.it4045.finalproject.controllers;

import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.services.CourseService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/courses")
@AllArgsConstructor
public class CourseController {
    private CourseService courseService;

    // gets all courses and is the default list view
    @GetMapping
    public String getAllCourses(Model model) {
        List<Course> courses = courseService.getCourses();
        model.addAttribute("courses", courses);
        return "courses/list";
    }

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
    public String getCourseDetails(@PathVariable Integer id, Model model) {
        var course = courseService.getCourseById(id);
        model.addAttribute("course", course);

        // to maintain the list of courses on the side
        List<Course> courses = courseService.getCourses();
        model.addAttribute("courses", courses);

        return "courses/list";
    }

    // @PostMapping("/")
    // public ResponseEntity<Course> createCourse(@RequestBody Course course) {
    //     // Check admin privileges
    //     // Save course to database
    //     // Return created course
    // }

    // @GetMapping("/{id}/comments")
    // public ResponseEntity<List<UserComment>> getCourseComments(@PathVariable int id) {
    //     // Fetch comments for course
    //     // Return list of comments
    // }

    // @GetMapping("/{id}/rating")
    // public ResponseEntity<Double> getCourseRating(@PathVariable int id) {
    //     // Calculate average rating
    //     // Return rating
    // }
}