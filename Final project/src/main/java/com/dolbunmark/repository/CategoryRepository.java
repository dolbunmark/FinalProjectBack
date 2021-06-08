package com.dolbunmark.repository;


import com.dolbunmark.domain.Category;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@Transactional
public class CategoryRepository {

    @Autowired
    UserRepository userRepository;
    @Autowired
    private SessionFactory sessionFactory;

    private List<Category> categoryList = Stream.of(
            new Category("Application & Services"),
            new Category("Benefits & Paper Work"),
            new Category("Hardware & Software"),
            new Category("People Management"),
            new Category("Security & Access"),
            new Category("Workplaces & Facilities")
    ).collect(Collectors.toList());

    @PostConstruct
    public void init() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        categoryList.forEach(category -> session.save(category));
        session.getTransaction().commit();
        session.close();
    }

    public List<Category> getCategoryes() {  // все категории
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Category", Category.class);
        List<Category> categoryList = query.list();
        session.close();
        return categoryList;
    }

    public Category getCategory(String name) { // одну категорию
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("from Category p where p.name = :name", Category.class);
        query.setParameter("name",name);
        Category category = (Category) query.getSingleResult();
        session.close();
        return category;
    }
}
