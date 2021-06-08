package com.dolbunmark;

import com.dolbunmark.repository.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class Main {

    @Autowired
    private static TicketRepository ticketRepository;


    public static void main(String[] args) {
        System.out.println("Hello World");

    }
}
