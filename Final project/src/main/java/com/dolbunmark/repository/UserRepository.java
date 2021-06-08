package com.dolbunmark.repository;

import com.dolbunmark.domain.User;
import com.dolbunmark.enums.Role;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
public class UserRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public UserRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public User getUser(String email) {
        Query query = sessionFactory.getCurrentSession().createQuery("from User p where p.email = :email");
        query.setParameter("email", email);
        return (User) query.getSingleResult();
    }

    @PostConstruct
    public void init() {
        List<User> userList = Stream.of(
                new User(Role.Employee,"user@gmail.com","1Asdf!", "User"),
                new User(Role.Manager,"manager@gmail.com","1Asdf!", "Manager"),
                new User(Role.Engineer,"engineer@gmail.com","1Asdf!","Engineer")
        ).collect(Collectors.toList());
        Session session = sessionFactory.openSession();
        userList.forEach(user -> session.save(user));
        session.close();
    }
}
