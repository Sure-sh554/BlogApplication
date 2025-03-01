package com.example.demo.service.Impl;

import com.example.demo.entity.Comment;
import com.example.demo.entity.Post;
import com.example.demo.exception.BlogAPIException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.repository.CommentRepository;
import com.example.demo.repository.PostRepository;
import com.example.demo.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;

    public CommentServiceImpl(PostRepository postRepository, CommentRepository commentRepository) {
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
    }

    @Override
    public Comment createComment(long postId, Comment comment) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException
                        ("Post id is not found with the given id", "id", postId)
        );
        //set post to comment entity
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);

        return savedComment;
    }

    @Override
    public List<Comment> getAllComments(long postId) {
        // reterive comment by postId
        List<Comment> comments = commentRepository.findByPostId(postId);
        return comments;
    }

    @Override
    public Comment getCommentById(long postId, long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(
                () -> new ResourceNotFoundException
                        ("Post id is not found with the given id", "id", postId)
        );
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException
                        ("Comment id is not found with the given id", "id", commentId));
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }

        return comment;
    }

    @Override
    public Comment updateComment(long postId, long commentId, Comment commentRequest) {
        Post post = postRepository.findById(postId).orElseThrow(() ->
                new ResourceNotFoundException("Post id is not found with the given id",
                        "id", postId));
        Comment updateComment = commentRepository.findById(commentId).orElseThrow(
                () -> new ResourceNotFoundException
                        ("Comment id is not found with the given id", "id", commentId));
        if (!commentRequest.getPost().getId().equals(post.getId())) {
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "Comment does not belong to post");
        }
        updateComment.setName(commentRequest.getName());
        updateComment.setEmail(commentRequest.getEmail());
        updateComment.setBody(commentRequest.getBody());
        Comment updatedSavedComment = commentRepository.save(updateComment);
        return updatedSavedComment;
    }
}
