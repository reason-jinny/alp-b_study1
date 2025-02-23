package com.kt.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kt.blog.model.Post;

@Service
public class PostService {

    private List<Post> posts = new ArrayList<>();
    private int nextId = 1;

    public Post addPost(Post post) {
        post.setId(nextId);     // 자동으로 ID 할당
        nextId++;               // 다음 글 ID 증가
        posts.add(post);        // 리스트에 추가
        return post;            // 추가된 글 반환
    }

    public List<Post> getAllPosts() {
        return posts;   // 저장된 모든 글 반환
    }
}
