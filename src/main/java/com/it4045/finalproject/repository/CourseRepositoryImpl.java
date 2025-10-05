package com.it4045.finalproject.repository;


import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class CourseRepositoryImpl implements ICourseRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void updateRating(int courseId, double rating) {
        em.createQuery(String.format("UPDATE courses SET course_rating = :rating WHERE course_id = :course_id;"))
                .setParameter("rating", rating)
                .setParameter("course_id", courseId)
                .executeUpdate();
    }
}
