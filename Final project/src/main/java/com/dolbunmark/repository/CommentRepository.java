package com.dolbunmark.repository;

import com.dolbunmark.domain.Comment;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
@Transactional
public class CommentRepository {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Comment> getComment(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Comment c WHERE c.ticket.id = :id");
        query.setParameter("id", id);
        return query.list();
    }

    public List<Comment> getCommentFive(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Comment c WHERE c.ticket.id = :id order by c.date desc")
                .setMaxResults(5);
        query.setParameter("id", id);
        return query.list();
    }

    public void addComment(Comment comment) {
        sessionFactory.getCurrentSession().save(comment);
    }
}
