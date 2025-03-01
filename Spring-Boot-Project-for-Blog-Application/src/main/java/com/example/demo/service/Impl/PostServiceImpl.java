package com.example.demo.service.Impl;

import com.example.demo.entity.Post;

import com.example.demo.repository.PostRepository;
import com.example.demo.service.PostService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//@AllArgsConstructor

public class PostServiceImpl implements PostService {
    private PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(Post post) {
        Post post1 = postRepository.save(post);
        return post1;
    }

    @Override
    public List<Post> getAllPost(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Post> post1 = postRepository.findAll(pageable);
        List<Post> listOfPosts = post1.getContent();

        return listOfPosts;
    }

    public Post getPostById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post is not found"));
        return post;


    }

    @Override
    public Post updatePost(Long id, Post post) {
        Post gotPostById = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post is not found"));
        gotPostById.setTitle(post.getTitle());
        gotPostById.setDescription(post.getDescription());
        gotPostById.setContent(post.getContent());
        Post updatedPost = postRepository.save(gotPostById);
        return updatedPost;
    }

    @Override
    public void deletePost(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Post is not found"));
        postRepository.delete(post);

    }
}
