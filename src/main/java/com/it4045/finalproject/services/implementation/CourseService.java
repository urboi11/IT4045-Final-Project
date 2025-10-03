package com.it4045.finalproject.services.implementation;

import com.it4045.finalproject.data.CourseDTO;
import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.mappers.CourseMapper;
import com.it4045.finalproject.repositories.CourseRepository;
import com.it4045.finalproject.services.ICourseService;


public class CourseService implements ICourseService {

    private CourseRepository courseRepository;

    private CourseMapper courseMapper;

    public CourseService(CourseRepository courseRepository, CourseMapper courseMapper ){
        this.courseRepository = courseRepository;
        this.courseMapper = courseMapper;
    }
    
    
    @Override
    public CourseDTO createCourse(Course course) {
        Course courseReturned = courseRepository.save(course);
        CourseDTO courseDto = courseMapper.courseDTOConverter(courseReturned);
        return courseDto;
    }

    @Override
    public CourseDTO searchCourses(int courseId) {

        //I may need to change this. 
        Course course= courseRepository.getReferenceById(courseId);

        CourseDTO courseDto = courseMapper.courseDTOConverter(course);

        return courseDto;

    }

    
}
