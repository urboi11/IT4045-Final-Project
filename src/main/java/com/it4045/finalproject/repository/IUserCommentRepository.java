package com.it4045.finalproject.repository;

import org.springframework.stereotype.Repository;

import java.util.List;


public interface IUserCommentRepository{
    
    List getCourseComments(int courseId);
}
