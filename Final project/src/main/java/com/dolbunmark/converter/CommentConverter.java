package com.dolbunmark.converter;

import com.dolbunmark.domain.Comment;
import com.dolbunmark.domain.Ticket;
import com.dolbunmark.domain.User;
import com.dolbunmark.dto.CommentDto;
import org.springframework.stereotype.Component;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Component
public class CommentConverter {

    public List<CommentDto> commentAll(List<Comment> comments) {
        List<CommentDto> list = new ArrayList<>();
        for (Comment comment : comments) {
            CommentDto commentDto = new CommentDto(
                    comment.getText(),
                    new SimpleDateFormat("dd/MM/YYYY").format(comment.getDate()),
                    comment.getUser().getFirst_name()
            );
            list.add(commentDto);
        }
        return list;
    }

    public Comment commentConverter(CommentDto commentDto, User user, Ticket ticket) {
        Comment comment = new Comment(
                user,
                commentDto.getText(),
                new Timestamp(System.currentTimeMillis()),
                ticket
        );
        return comment;
    }
}
