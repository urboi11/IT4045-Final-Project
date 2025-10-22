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

import java.util.List;

@AllArgsConstructor
@Service
public class CourseService implements ICourseService{
private final CourseRepository courseRepository;
private final UserRepository userRepository;
private final UserCommentRepository userCommentRepository;
private final EntityManager entityManager;


    @Override
    public Course createCourse(Course course) {
        return null;
    }

    @Override
    public List<Course> searchCourses(int courseID) {
        return List.of();
    }

    @Override
    public void commentOnCourse(String comment, Course course) {

    }

    @Override
    public List<UserComments> deleteComment(int userCommentId) {
        return List.of();
    }

    @Override
    public void getCommentsForCourse(Course course) {

    }

    @Override
    public int getRating(Course course) {
        return 0;
    }

    @Override
    public void calculateRating(Course course, int rating) {

    }
}



