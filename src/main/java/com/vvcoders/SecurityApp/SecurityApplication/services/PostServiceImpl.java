package com.vvcoders.SecurityApp.SecurityApplication.services;

import com.vvcoders.SecurityApp.SecurityApplication.dto.PostDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service @RequiredArgsConstructor
public class PostServiceImpl implements PostService{


    @Override
    public List<PostDTO> getAllPosts() {
        return List.of();
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        return null;
    }

    @Override
    public PostDTO getPostById(Long postId) {
        return null;
    }

    @Override
    public PostDTO updatePost(PostDTO inputPost, Long postId) {
        return null;
    }
}
