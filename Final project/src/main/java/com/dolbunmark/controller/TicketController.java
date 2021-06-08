package com.dolbunmark.controller;

import com.dolbunmark.converter.TicketConverter;
import com.dolbunmark.domain.Category;
import com.dolbunmark.dto.TicketDto;

import com.dolbunmark.enums.Urgency;
import com.dolbunmark.repository.TicketRepository;
import com.dolbunmark.service.HistoryService;
import com.dolbunmark.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;


@RestController
@Transactional
@RequestMapping("/ticket")
public class TicketController {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    TicketRepository ticketRepository;

    @Autowired
    TicketConverter ticketConverter;

    @GetMapping
    public List<TicketDto> getAllTickets(Authentication authentication) {
        return ticketService.getAllTickets(authentication);
    }

    @PostMapping(value = "/createTicket") // создание Ticket
    public ResponseEntity createTicket(@RequestBody TicketDto ticketDto, Authentication authentication) {
        ticketService.addTicket(ticketDto, authentication);
        return ResponseEntity.ok("Ok") ;
    }

    @GetMapping(value = "/urgency") // получение Urgency
    public List<Urgency> urgencies() {
        return ticketConverter.urgency();
    }

    @GetMapping(value = "/category") // получение Category
    public List<Category> categories() {
        return ticketService.getCategory();
    }

    @GetMapping(value = "/{id}")
    public TicketDto ticketId(@PathVariable long id) {
        return ticketService.ticketView(id);
    }
}

