package com.it4045.finalproject.services;

import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;
import com.it4045.finalproject.repository.CourseRepository;
import com.it4045.finalproject.repository.UserRepository;
import com.it4045.finalproject.repository.UserCommentRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Service;
import jakarta.persistence.EntityManager;
import java.util.List;
import static java.lang.Math.round;

@AllArgsConstructor
@Data
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

    /*@Override
    public List<Course> searchCourses(int courseID) {
       var resultCourse = courseRepository.findById(courseID);
       var resultList = resultCourse.stream().toList();
        return resultList;
    }*/

    @Override
    public List<Course> searchCourses(String courseNum) {
        List<Course> allCourses = courseRepository.findAll();
        return allCourses.stream().filter(c -> c.getCourseNumber().equals(courseNum)).toList();
    }

    @Override
    public List<Course> getCourses() {
        return courseRepository.findAll();
    }

    @Override
    public Course getCourseById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public void commentOnCourse(String comment, User user, Course course) {
        //int targetCourseID = course.getCourseId();
        //Course targetCourse = entityManager.find(Course.class, targetCourseID);
        //UserComments commentToAdd = new UserComments(0, user, targetCourse, comment);
        UserComments commentToAdd = UserComments.builder().user(user).course(course).comment(comment).build();

        course.addComment(commentToAdd);
        //targetCourse.setRating_count(targetCourse.getRating_count()+1);
        //userCommentRepository.save(commentToAdd);
        courseRepository.save(course);

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
        /*int targetCourseID =course.getCourseId();
        List<UserComments> commentsList = entityManager.find(UserComments.class, course).getCourse().getUserComments();
        return commentsList;*/
        return course.getUserComments();
    }

    @Override
    public int getRating(Course course) {
        double rating = course.getCourseRating();
        return (int) round(rating);
    }

    @Override
    public void calculateRating(Integer courseId, String ratingInput) {
        var course = courseRepository.findById(courseId).orElse(null);
        int rating = Integer.parseInt(ratingInput);

        double currentRating = course.getCourseRating();
        int ratingCount = course.getRating_count();
        double newRating = rating;

        if (currentRating != 0) {
            newRating = ((currentRating * ratingCount) + rating) / (ratingCount + 1);
        }
        course.setCourseRating(newRating);
        course.setRating_count(ratingCount + 1); // increments rating_count since we get a new rating
        courseRepository.save(course);
    }
}



