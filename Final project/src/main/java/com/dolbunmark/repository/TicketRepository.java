package com.dolbunmark.repository;


import com.dolbunmark.domain.Category;
import com.dolbunmark.domain.Ticket;
import com.dolbunmark.domain.User;
import com.dolbunmark.enums.Role;
import com.dolbunmark.enums.State;
import com.dolbunmark.enums.Urgency;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.sql.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Repository
@Transactional
public class TicketRepository {

    @Autowired
    UserRepository userRepository;

    private SessionFactory sessionFactory;

    private  @Autowired CategoryRepository categoryRepository;

    @Autowired
    public TicketRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public List<Ticket> getAllTicket() {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Ticket", Ticket.class);
        return query.list();
    }

    public Ticket getTicketById(long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Ticket p WHERE p.id = :id");
        query.setParameter("id", id);
        return (Ticket) query.getSingleResult();
    }

    public List<Ticket> getAllTicketEmployee(User user) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM Ticket t WHERE t.owner = :owner");
        query.setParameter("owner", user);
        return query.list();
    }

    public List<Ticket> getAllTicketManager(User user) {
        Query query = sessionFactory.getCurrentSession().createQuery("From Ticket t where  t.state = :state or t.approver = :approver or t.owner = :owner");
        query.setParameter("owner", user);
        query.setParameter("state", State.New);
        query.setParameter("approver", user);
        return query.list();
    }

    public List<Ticket> getAllTicketEngineer(User user) {
        Query query = sessionFactory.getCurrentSession().createQuery("From Ticket t where t.assignee = :user or t.state = :state");
        query.setParameter("user", user);
        query.setParameter("state", State.Approved);
        return query.list();
    }



    @PostConstruct
    public void init() {
        Category category = categoryRepository.getCategory("Application & Services");
        User user = new User("q@mail.ru", "1Asdfgg@","markus");
        List<Ticket> ticket = Stream.of(
                new Ticket("Mark", "Test", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis() - 86400000), user, user, State.Approved, category, Urgency.High, user),
                new Ticket("Pavel", "Test", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), user, user, State.Approved, category, Urgency.Critical, user),
                new Ticket("Nik", "Test", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), user, user, State.Approved, category, Urgency.Low, user),
                new Ticket("Jon", "Test", new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), user, user, State.Approved, category, Urgency.Average, user)
        ).collect(Collectors.toList());
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        ticket.forEach(ticket1 -> addTicket(ticket1));
        session.getTransaction().commit();
        session.close();
    }

    public void addTicket(Ticket ticket) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(ticket);
        session.getTransaction().commit();
        session.close();
    }
}
