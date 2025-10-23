package com.it4045.finalproject.services;

import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;
import com.it4045.finalproject.repository.CourseRepository;
import com.it4045.finalproject.repository.UserRepository;
import com.it4045.finalproject.repository.UserCommentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;

import java.util.List;

import static java.lang.Math.round;

@AllArgsConstructor
@Service
public class CourseService implements ICourseService{
private final CourseRepository courseRepository;
private final UserRepository userRepository;
private final UserCommentRepository userCommentRepository;
private final EntityManager entityManager;


    @Override
    public Course createCourse(Course course) {
        courseRepository.save(course);
        return course;
    }

    @Override
    public List<Course> searchCourses(int courseID) {
       var resultCourse = courseRepository.findById(courseID);
       var resultList = resultCourse.stream().toList();
        return resultList;
    }



    @Override
    public void commentOnCourse(String comment, User user, Course course) {
       int targetCourseID = course.getCourseId();
         Course targetCourse = entityManager.find(Course.class, targetCourseID);
         UserComments commentToAdd = new UserComments(0, user, targetCourse, comment);
         targetCourse.addComment(commentToAdd);
         targetCourse.setRating_count(targetCourse.getRating_count()+1);
         userCommentRepository.save(commentToAdd);
        courseRepository.save(targetCourse);

    }

    @Override
    public void deleteComment(int userCommentId) {
        UserComments targetComment = entityManager.find(UserComments.class, userCommentId);
        Course targetCourse = targetComment.getCourse();
        userCommentRepository.deleteById(userCommentId);
        targetCourse.setRating_count(targetCourse.getRating_count()-1);

    }


    @Override
    public List<UserComments> getCommentsForCourse(Course course) {
        int targetCourseID =course.getCourseId();
        List<UserComments> commentsList = entityManager.find(UserComments.class, course).getCourse().getUserComments();
        return commentsList;

    }

    @Override
    public int getRating(Course course) {
        double rating = course.getCourseRating();
        return (int) round(rating);
    }

    @Override
    public void calculateRating(Course course, int rating) {
        double currentRating = course.getCourseRating();
        int ratingCount = course.getRating_count();
        double newRating = (currentRating + rating) / ratingCount;
        course.setCourseRating(newRating);
        courseRepository.save(course);
    }
}



