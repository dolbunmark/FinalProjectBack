package com.dolbunmark.repository;

import com.dolbunmark.domain.Category;
import com.dolbunmark.domain.Ticket;
import com.dolbunmark.domain.User;
import com.dolbunmark.enums.State;
import com.dolbunmark.enums.Urgency;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
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
public class CreateTicketRepository {

    @Autowired
    UserRepository userRepository;

    private SessionFactory sessionFactory;

    public CreateTicketRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @PostConstruct
    public void addCreatedTicket() {
        User user = new User("e@mail.ru", "1Asdfgg@", "kir");
        List<Ticket> ticket = Stream.of(
                new Ticket("Mark", "Test",
                        new Date(System.currentTimeMillis()),
                        new Date(System.currentTimeMillis()),
                        user, user, State.Approved,
                        new Category("Test"),
                        Urgency.Average, user)
        ).collect(Collectors.toList());
    }

    public void createTicket(Ticket ticket) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(ticket);
        session.getTransaction().commit();
        session.close();
    }
}
