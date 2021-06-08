package com.dolbunmark.converter;

import com.dolbunmark.domain.History;
import com.dolbunmark.domain.Ticket;
import com.dolbunmark.domain.User;
import com.dolbunmark.dto.HistoryDto;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class HistoryConverter {

    public List<HistoryDto> historyAll(List<History> histories) {
        List<HistoryDto> list = new ArrayList<>();
        for (History history : histories) {
            list.add(
                    new HistoryDto(
                            new SimpleDateFormat("dd/MM/YYYY").format(history.getDate()),
                            history.getUser_id().getFirst_name(),
                            history.getAction(),
                            history.getDescription()
                    )
            );
        }
        return list;
    }

    public History AddHistory(HistoryDto historyDto, User user, Ticket ticket) {
        History history = new History(
                ticket,
                new Timestamp(System.currentTimeMillis()),
                historyDto.getAction(),
                user,
                historyDto.getDescription()
        );
        return history;
    }
}
