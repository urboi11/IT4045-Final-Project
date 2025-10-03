package com.it4045.finalproject.repositories.impl;

import java.util.Optional;

import com.it4045.finalproject.entities.UserComments;
import com.it4045.finalproject.repositories.IUserCommentRepository;
import com.it4045.finalproject.repositories.UserCommentRepository;

public class IUserCommentImpl implements IUserCommentRepository {

    UserCommentRepository userCommentRepository;

    IUserCommentImpl(UserCommentRepository userCommentRepository){
        this.userCommentRepository = userCommentRepository;
    }
    
    @Override
    public void deleteUserComment(int userCommentId) {
        userCommentRepository.deleteById(userCommentId);
    }

    @Override
    public void getCourseComments(int courseId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCourseComments'");
    }
    
}
