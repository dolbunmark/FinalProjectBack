package com.dolbunmark.controller;


import com.dolbunmark.dto.TicketDto;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@Transactional
@RequestMapping(value = "/test")
public class LoginController {


    @GetMapping()
    public String getAllProducts() {
        return "123";
    }



}

