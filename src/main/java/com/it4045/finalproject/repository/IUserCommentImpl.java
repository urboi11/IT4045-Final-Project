 package com.it4045.finalproject.repository;

 import jakarta.persistence.EntityManager;
 import jakarta.persistence.PersistenceContext;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.stereotype.Repository;
 import java.util.List;


 @Repository
 public class IUserCommentImpl implements IUserCommentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List getCourseComments(int courseId) {
        List result = em.createQuery(String.format("SELECT usercomments.course_id, usercomments.comment FROM usercomments INNER JOIN courses ON usercomments.course_id = courses.course_id WHERE usercomments.course_id = :id"))
                .setParameter("id", courseId)
                .getResultList();
        return result;

    }


}
