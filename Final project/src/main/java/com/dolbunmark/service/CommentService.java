package com.dolbunmark.service;

import com.dolbunmark.converter.CommentConverter;
import com.dolbunmark.converter.TicketConverter;
import com.dolbunmark.domain.Comment;
import com.dolbunmark.domain.Ticket;
import com.dolbunmark.domain.User;
import com.dolbunmark.dto.CommentDto;
import com.dolbunmark.dto.TicketDto;
import com.dolbunmark.repository.CategoryRepository;
import com.dolbunmark.repository.CommentRepository;
import com.dolbunmark.repository.TicketRepository;
import com.dolbunmark.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentConverter commentConverter;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TicketRepository ticketRepository;

    public List<CommentDto> getComment(Long id) {
        List<Comment> comments = commentRepository.getComment(id);
        List<CommentDto> commentDto = commentConverter.commentAll(comments);
        return commentDto;
    }

    public List<CommentDto> getCommentFive(Long id) {
        List<Comment> comments = commentRepository.getCommentFive(id);
        List<CommentDto> commentDto = commentConverter.commentAll(comments);
        return commentDto;
    }

    public void addComment(CommentDto commentDto, Authentication authentication) {
        User user = userRepository.getUser(authentication.getName());
        Ticket ticket = ticketRepository.getTicketById(commentDto.getId());
        Comment comment = commentConverter.commentConverter(commentDto, user, ticket );
        commentRepository.addComment(comment);
    }
}
