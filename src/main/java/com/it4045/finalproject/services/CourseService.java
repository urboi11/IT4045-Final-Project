package com.it4045.finalproject.services;

import com.it4045.finalproject.services.IUserService;
import com.it4045.finalproject.services.ICourseService;
import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;
import com.it4045.finalproject.repository.CourseRepository;
import com.it4045.finalproject.repository.UserRepository;
import com.it4045.finalproject.repository.UserCommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import jakarta.persistence.EntityManager;

@AllArgsConstructor
@Service
public class CourseService implements ICourseService{
private final CourseRepository courseRepository;
private final UserRepository userRepository;
private final UserCommentRepository userCommentRepository;
private final EntityManager entityManager;

@Transactional
public Course createCourse(String FirstName, String LastName, String Email, String Password, Boolean IsAdmin){
    Course course = new Course();
    course.setCourseName(FirstName);
    course.se
    courseRepository.save(course);
}


}
