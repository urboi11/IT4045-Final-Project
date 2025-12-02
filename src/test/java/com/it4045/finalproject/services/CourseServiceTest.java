package com.it4045.finalproject.services;

import com.it4045.finalproject.entities.Course;
import com.it4045.finalproject.entities.User;
import com.it4045.finalproject.entities.UserComments;
import com.it4045.finalproject.exceptions.CourseNameExistsException;
import com.it4045.finalproject.exceptions.CourseNumberExistsException;
import com.it4045.finalproject.exceptions.EmptyCommentException;
import com.it4045.finalproject.repository.CourseRepository;
import com.it4045.finalproject.repository.UserCommentRepository;
import com.it4045.finalproject.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CourseService Unit Tests")
public class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;
    @Mock
    private UserCommentRepository userCommentRepository;

    @InjectMocks
    private CourseService courseService;

    private Course testCourse;
    private User testUser;

    @BeforeEach
    void setUp(){
        testCourse = Course.builder()
                .courseId(1)
                .courseNumber("IT4045C")
                .courseName("Enterprise App Dev")
                .university("University of Cincinnati")
                .description("Learn about Java Springboot!")
                .build();

        testUser = User.builder()
                .userid(1)
                .firstname("Jane")
                .lastname("Doe")
                .email("jane.doe@gmail.com")
                .password("pass123")
                .role("User")
                .build();
    }

    @Test
    @DisplayName("Test Creating a Course - Success")
    void testCreateCourse_Success() {
        when(courseRepository.findByUniversityAndCourseName("University of Cincinnati", "Enterprise App Dev"))
                .thenReturn(Collections.emptyList());

        when(courseRepository.findByUniversityAndCourseNumber("University of Cincinnati", "IT4045C"))
                .thenReturn(Collections.emptyList());

        when(courseRepository.save(testCourse)).thenReturn(testCourse);

        Course result = courseService.createCourse(testCourse);
        assertNotNull(result);

        verify(courseRepository, times(1)).save(testCourse);
    }

    @Test
    @DisplayName("Test Creating a Course - Error, Duplicate Name")
    void testCreateCourse_ThrowsDuplicateName() {
        when(courseRepository.findByUniversityAndCourseName("University of Cincinnati", "Enterprise App Dev"))
                .thenReturn(Collections.singletonList(testCourse));

        assertThrows(CourseNameExistsException.class,
                () -> courseService.createCourse(testCourse));
    }

    @Test
    @DisplayName("Test Creating a Course - Error, Duplicate Number")
    void testCreateCourse_ThrowsDuplicateNumber() {
        when(courseRepository.findByUniversityAndCourseName("University of Cincinnati", "Enterprise App Dev"))
                .thenReturn(Collections.emptyList());

        when(courseRepository.findByUniversityAndCourseNumber("University of Cincinnati", "IT4045C"))
                .thenReturn(Collections.singletonList(testCourse));

        assertThrows(CourseNumberExistsException.class,
                () -> courseService.createCourse(testCourse));
    }

    @Test
    @DisplayName("Test Deleting a Course - Success")
    void testDeleteCourse_Success() {
        when(courseRepository.findById(1)).thenReturn(Optional.of(testCourse));
        courseService.deleteCourse(1);
        verify(courseRepository, times(1)).delete(testCourse);
    }

    @Test
    @DisplayName("Test Searching for a Course - Success")
    void testSearchCourse_Success() {
        when(courseRepository.findByCourseNumber("IT4045C")).thenReturn(Collections.singletonList(testCourse));
        var results = courseService.searchCourses("IT4045C");

        assertNotNull(results);
        assertEquals(testCourse, results.get(0));

        verify(courseRepository, times(1)).findByCourseNumber("IT4045C");
    }

    @Test
    @DisplayName("Test Commenting on a Course - Success")
    void testCommentOnCourse_Success() {
        courseService.commentOnCourse("comment", testUser, testCourse);

        assertEquals(1, testCourse.getUserComments().size());
        assertEquals("comment", testCourse.getUserComments().get(0).getComment());

        verify(userCommentRepository, times(1)).save(any(UserComments.class));
    }

    @Test
    @DisplayName("Test Commenting on a Course - Error, Empty Comment")
    void testCommentOnCourse_EmptyCommentThrowsException() {
        assertThrows(EmptyCommentException.class,
                () -> courseService.commentOnCourse("", testUser, testCourse));
    }

    @Test
    @DisplayName("Test Calculating a Rating - Success")
    void testCalculateRating_Success() {
        testCourse.setCourseRating(5);
        testCourse.setRating_count(1);

        when(courseRepository.findById(1)).thenReturn(Optional.of(testCourse));

        courseService.calculateRating(1, 3);

        assertEquals(4, testCourse.getCourseRating());
        assertEquals(2, testCourse.getRating_count());
        verify(courseRepository).save(testCourse);
    }

    @Test
    @DisplayName("Test Calculating a Rating - Success, Initially Zero")
    void testCalculateRating_SuccessInitiallyZero() {
        when(courseRepository.findById(1)).thenReturn(Optional.of(testCourse));

        courseService.calculateRating(1, 4);

        assertEquals(4, testCourse.getCourseRating());
        assertEquals(1, testCourse.getRating_count());
        verify(courseRepository).save(testCourse);
    }

    @Test
    @DisplayName("Test Calculating a Rating - Error, Course Not Found")
    void testCalculateRating_NoCourseFound_ThrowsException() {
        when(courseRepository.findById(99)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class,
                () -> courseService.calculateRating(99, 5));
    }
}

