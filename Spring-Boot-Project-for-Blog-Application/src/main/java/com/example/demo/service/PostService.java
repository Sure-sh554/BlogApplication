package com.example.demo.service;

import com.example.demo.entity.Post;

import java.util.List;


public interface PostService {

    Post createPost(Post post);

    List<Post> getAllPost(int pageNo,int pageSize);

    Post getPostById(Long id);

    Post updatePost(Long id, Post post);

    void deletePost(Long id);
}
