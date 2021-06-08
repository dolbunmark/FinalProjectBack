package com.dolbunmark.service;

import com.dolbunmark.converter.HistoryConverter;
import com.dolbunmark.domain.History;
import com.dolbunmark.domain.Ticket;
import com.dolbunmark.domain.User;
import com.dolbunmark.dto.HistoryDto;
import com.dolbunmark.repository.HistoryRepository;
import com.dolbunmark.repository.TicketRepository;
import com.dolbunmark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class HistoryService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private HistoryConverter historyConverter;


    public void addHistory(String action, String description, long id) { // проверить
        Ticket ticket = ticketRepository.getTicketById(id);
        History history = new History(ticket,new Timestamp(System.currentTimeMillis()),action, ticket.getOwner(),description);
        historyRepository.addHistory(history);
    }


    public List<HistoryDto> getHistory(long id) {
        List<History> histories = historyRepository.getHistory(id);
        List<HistoryDto> historyDto = historyConverter.historyAll(histories);
        return historyDto;
    }

    public List<HistoryDto> getHistoryFive(long id) {
        List<History> histories = historyRepository.getHistoryFive(id);
        List<HistoryDto> historyDto = historyConverter.historyAll(histories);
        return historyDto;
    }
}
