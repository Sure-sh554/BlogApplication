package com.example.demo.controller;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.repository.CommentRepository;
import com.example.demo.service.CommentService;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
    private CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/posts/{postId}/comments")
    public ResponseEntity<Comment> createComment(@PathVariable(value = "postId")
                                                 long postId, @RequestBody Comment comment) {
        Comment comment1 = commentService.createComment(postId, comment);
        return new ResponseEntity<>(comment1, HttpStatus.CREATED);

    }

    @GetMapping("/posts/{postId}/comments")
    public List<Comment> getCommentsByPostId(@PathVariable
                                             Long postId) {
        List<Comment> comment = commentService.getAllComments(postId);
        return comment;


    }

    @GetMapping("/posts/{postId}/comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable(value =
            "postId") Long postId, @PathVariable(value = "id") Long commentId) {
        Comment comment = commentService.getCommentById(postId, commentId);
        return new ResponseEntity<>(comment, HttpStatus.OK);

    }

    @PutMapping("/post/{postId}/comment/{Id}")
    public ResponseEntity<Comment> updateComment(@PathVariable(value = "postId") Long postId,
                                                 @PathVariable(value = "Id") long commentId,
                                                 @RequestBody Comment commentRequest) {
        Comment updatedComment = commentService.updateComment(postId, commentId, commentRequest);
        return new ResponseEntity<>(updatedComment, HttpStatus.OK);

    }

    @DeleteMapping("/post/{postId}/comment/{Id}")

    public ResponseEntity<String> deleteComment(@PathVariable(value = "postId") Long postId
            , @PathVariable(value = "Id") Long commentId) {
        commentService.deleteComment(postId, commentId);
        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
    }

}
