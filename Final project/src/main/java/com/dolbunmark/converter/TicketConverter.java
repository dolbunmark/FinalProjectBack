package com.dolbunmark.converter;

import com.dolbunmark.domain.Category;
import com.dolbunmark.domain.Ticket;
import com.dolbunmark.domain.User;
import com.dolbunmark.dto.TicketDto;
import com.dolbunmark.enums.State;
import com.dolbunmark.enums.Urgency;
import com.dolbunmark.repository.CategoryRepository;
import com.dolbunmark.repository.TicketRepository;
import com.dolbunmark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Component
public class TicketConverter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public TicketConverter() {
    }

    public List<TicketDto> ticketsAll(List<Ticket> lists) { // allTicket
        List<TicketDto> list = new ArrayList<>();
        for (Ticket ticket : lists) {
            TicketDto ticketDto = new TicketDto(
                    ticket.getId(),
                    ticket.getName(),
                    new SimpleDateFormat("dd/MM/YYYY").format(ticket.getDesired_resolution_date()),
                    ticket.getState(),
                    ticket.getUrgency());
            list.add(ticketDto);
        }
        return list;
    }

//    public List<TicketDto> ticketsCatUr(List<Ticket> lists) { // category and urgency
//        List<TicketDto> list = new ArrayList<>();
//        for (Ticket ticket : lists) {
//            TicketDto ticketDto = new TicketDto(
//                    ticket.getCategory(),
//                    ticket.getUrgency());
//            list.add(ticketDto);
//        }
//        return list;
//    }

    public Ticket converterToTicket(TicketDto ticketDto, Authentication authentication) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Timestamp timestamp;
        Date date = null;
        try {
            date = format.parse(ticketDto.getDesired_resolution_date());
            timestamp = new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        User user = userRepository.getUser(authentication.getName());
        Category category = categoryRepository.getCategory(ticketDto.getCategory());
        Ticket ticket = new Ticket(
                ticketDto.getName(),
                ticketDto.getDescription(),
                new java.sql.Date(System.currentTimeMillis()),
                new java.sql.Date(date.getTime()),
                user,
                State.New,
                category,
                ticketDto.getUrgency()
        );
        return ticket;
    }

    public List<Urgency> urgency() {
        List<Urgency> urgencies = Arrays.asList(Urgency.values());
        return urgencies;
    }

    public List<TicketDto> category() {
        List<TicketDto> categories = new ArrayList<>();
        categories.forEach(ticketDto -> {
            ticketDto.getCategory();
        });
        return categories;
    }

    public TicketDto ticketView(Ticket ticket) {
        User user = new User("","","");


        if (ticket.getApprover() == null) {
            ticket.setApprover(user);
        }

        if (ticket.getAssignee() == null) {
            ticket.setAssignee(user);
        }

        TicketDto dto = new TicketDto(
                ticket.getId(),
                ticket.getName(),
                ticket.getDescription(),
                new SimpleDateFormat("dd/MM/yyyy").format(ticket.getDesired_resolution_date()),
                ticket.getOwner().getFirst_name(),
                ticket.getState(),
                ticket.getCategory().getName(),
                ticket.getUrgency(),
                ticket.getApprover().getFirst_name(),
                ticket.getAssignee().getFirst_name(),
                new SimpleDateFormat("dd/MM/yyyy").format(ticket.getCreated_on())
        );
        return dto;
    }


//    public List<TicketDto> ticketsCatUr(List<Ticket> lists) { // category and urgency
//        List<TicketDto> list = new ArrayList<>();
//        for (Ticket ticket : lists) {
//            TicketDto ticketDto = new TicketDto(
//                    ticket.getCategory(),
//                    ticket.getUrgency());
//            list.add(ticketDto);
//        }
//        return list;
//    }
}