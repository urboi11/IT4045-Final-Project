package com.it4045.finalproject.repositories.impl;

import com.it4045.finalproject.repositories.ICourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.repositories.CourseRepository;


public class ICourseRepositoryImpl implements ICourseRepository{

    CourseRepository courseRepository;

    ICourseRepositoryImpl(CourseRepository courseRepository){
        this.courseRepository = courseRepository;    
    }
    @Override
    public void updateRating(int courseId, double rating) {
        
        Optional<Course> courseReturned = courseRepository.findById(courseId);

        courseReturned.ifPresent(course -> {
            course.setCourseRating(rating);
            courseRepository.save(course);
        });

    }
    @Override
    public List<Course> getCourses(int courseId) {
        List<Course> courseList = new ArrayList();

        var courses = courseRepository.findById(courseId);
    
        courses.ifPresent(course -> {
            courseList.add(course);            
        });
        
        return courseList;
    }
    
}
