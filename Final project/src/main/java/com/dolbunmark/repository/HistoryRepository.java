package com.dolbunmark.repository;

import com.dolbunmark.domain.Comment;
import com.dolbunmark.domain.History;
import com.dolbunmark.dto.HistoryDto;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public class HistoryRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public List<History> getHistory(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM History h WHERE h.ticket_id.id = :id");
        query.setParameter("id", id);
        return query.list();
    }

    public List<History> getHistoryFive(Long id) {
        Query query = sessionFactory.getCurrentSession().createQuery("FROM History h WHERE h.ticket_id.id = :id order by h.date desc")
                .setMaxResults(5);
        query.setParameter("id", id);
        return query.list();
    }

    public void addHistory(History history) { // добавить историю
        sessionFactory.getCurrentSession().save(history);

    }
}
