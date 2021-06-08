package com.dolbunmark.controller;


import com.dolbunmark.domain.History;
import com.dolbunmark.dto.CommentDto;
import com.dolbunmark.dto.HistoryDto;
import com.dolbunmark.service.HistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@RequestMapping(value = "/history")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @GetMapping("/{id}")
    public List<HistoryDto> getAllHistory(@PathVariable Long id) {
        return historyService.getHistory(id);
    }

    @GetMapping(value = "five/{id}")
    public List<HistoryDto> getHistoryFive(@PathVariable Long id) {
        return historyService.getHistoryFive(id);
    }







}
