package com.it4045.finalproject.repositories;

public interface IUserCommentRepository{
    
    void deleteUserComment(int userCommentId);
    
    void getCourseComments(int courseId);
}
