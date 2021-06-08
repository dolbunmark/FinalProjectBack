package com.dolbunmark.controller;

import com.dolbunmark.converter.CommentConverter;
import com.dolbunmark.dto.CommentDto;
import com.dolbunmark.repository.CommentRepository;
import com.dolbunmark.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@RestController
@Transactional
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private CommentConverter commentConverter;

    @GetMapping(value = "/{id}")
    public List<CommentDto> getAllComments(@PathVariable Long id) {
        return commentService.getComment(id);
    }

    @GetMapping(value = "/five/{id}")
    public List<CommentDto> getCommentFive(@PathVariable Long id) {
        return commentService.getCommentFive(id);
    }

    @PostMapping()
    public ResponseEntity newComment (@RequestBody CommentDto commentDto, Authentication authentication) {
        commentService.addComment(commentDto, authentication);
        return ResponseEntity.ok().build();
    }
}
