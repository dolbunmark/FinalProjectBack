package com.dolbunmark.service;

import com.dolbunmark.converter.TicketConverter;
import com.dolbunmark.domain.Category;
import com.dolbunmark.domain.Ticket;
import com.dolbunmark.domain.User;
import com.dolbunmark.dto.TicketDto;
import com.dolbunmark.enums.Role;
import com.dolbunmark.exeception.InvalidArgumentException;
import com.dolbunmark.repository.CategoryRepository;
import com.dolbunmark.repository.TicketRepository;
import com.dolbunmark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class TicketService {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private TicketConverter ticketConverter;

    @Autowired
    private CategoryRepository categoryRepository;


    public List<TicketDto> getAllTickets(Authentication authentication) {
        User user = userRepository.getUser(authentication.getName());
        if (user.getRole() == Role.Employee) {
            List<Ticket> tickets = ticketRepository.getAllTicketEmployee(user);
            List<TicketDto> ticketDto = ticketConverter.ticketsAll(tickets);
        return ticketDto;

        } else if (user.getRole() == Role.Manager) {
            List<Ticket> tickets = ticketRepository.getAllTicketManager(user);
            List<TicketDto> ticketDto = ticketConverter.ticketsAll(tickets);
            return ticketDto;
        } else if (user.getRole() == Role.Engineer) {
            List<Ticket> tickets = ticketRepository.getAllTicketEngineer(user);
            List<TicketDto> ticketDto = ticketConverter.ticketsAll(tickets);
            return ticketDto;
        }
        return Collections.emptyList();
//        List<Ticket> tickets = ticketRepository.getAllTicket();
//        List<TicketDto> ticketDto = ticketConverter.ticketsAll(tickets);
//        return ticketDto;
    }


    public void addTicket(TicketDto ticketDto, Authentication authentication) {
        Ticket ticket = ticketConverter.converterToTicket(ticketDto, authentication);
        ticketRepository.addTicket(ticket);
        historyService.addHistory("Ticket is created", "Ticket is created",ticket.getId());
    }

    public List<Category> getCategory() { // НЕ РАБОТАЕТ !!!
        List<Category> categories = categoryRepository.getCategoryes();
        return categories;
    }

    public TicketDto ticketView(long id) {
        if (id == 0) {
            throw new InvalidArgumentException("Arguments cant be null");
        }
        Ticket ticket = ticketRepository.getTicketById(id);
        TicketDto ticketDto = ticketConverter.ticketView(ticket);

        return ticketDto;
    }





//    public List<TicketDto> getCatUr() {
//        List<Ticket> tickets = ticketRepository.getAllTicket();
//        List<TicketDto> ticketDto = ticketConverter.ticketsCatUr(tickets);
//        return ticketDto;
//    }

//    public TicketDto getById(int id) {
//        Ticket ticket = ticketRepository.getTicketById(id);
//        return ticketConverter.toDto(ticket);
//    }
}
