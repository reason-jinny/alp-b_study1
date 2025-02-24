package com.kt.blog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kt.blog.model.Post;

@Service
public class PostService {

    private List<Post> posts = new ArrayList<>();
    private int nextId = 1;

    // 글 작성(추가)
    public Post addPost(Post post) {
        // post.setId(nextId);     // 자동으로 ID 할당
        // nextId++;               // 다음 글 ID 증가
        post.setId(nextId++);   // 자동으로 ID 할당 후 증가 (합침)
        posts.add(post);        // 리스트에 추가
        return post;            // 추가된 글 반환
    }

    // 모든 글 조회
    public List<Post> getAllPosts() {
        return posts;   // 저장된 모든 글 반환
    }

    // 특정 ID의 글 조회
    public Post getPostById(int id) {
        for (Post post : posts) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null; // 글이 없으면 null 반환
    }   

    // 특정 ID의 글 수정
    public Post updatePost(int id, Post updatedPost) {
        for (Post post : posts) {
            if (post.getId() == id) {
                post.setTitle(updatedPost.getTitle());
                post.setContent(updatedPost.getContent());
                post.setAuthor(updatedPost.getAuthor());
                return post;
            }
        }
        return null; // 글이 없으면 null 반환
    }

    // 특정 ID의 글 삭제
    public boolean deletePost(int id) {
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getId() == id) {
                posts.remove(i);
                return true;
            }
        }
        return false;    
    }
}
