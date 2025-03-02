package com.example.demo.service;

import com.example.demo.entity.Comment;

import java.util.List;

public interface CommentService {
    Comment createComment(long postId,Comment comment);

    List<Comment> getAllComments(long postId);

    Comment getCommentById(long postId,long commentId);
    Comment updateComment(long postId,long commentId,Comment commentRequest);
    void deleteComment(long postId,long commentId);
}
