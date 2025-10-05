package com.it4045.finalproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.it4045.finalproject.entities.UserComments;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

@NoRepositoryBean
@Repository
public interface UserCommentRepository extends JpaRepository<UserComments, Integer>, IUserCommentRepository {
    
}


