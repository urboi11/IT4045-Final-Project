package com.it4045.finalproject.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
public class CourseController {

    // @GetMapping("/search")
    // public ResponseEntity<List<Course>> searchCourses(@RequestParam String courseId) {
    //     // Search for matching courses
    //     // Return list of results
    // }

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