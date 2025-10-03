package com.it4045.finalproject.repositories;

import java.util.List;

import com.it4045.finalproject.entities.Course;

public interface ICourseRepository {
    
    void updateRating(int courseId, double rating); 
    
    List<Course> getCourses(int courseId);

}
