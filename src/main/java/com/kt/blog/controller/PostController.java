package com.kt.blog.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kt.blog.model.Post;
import com.kt.blog.service.PostService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    // 생성자를 이용한 의존성 주입
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping // 글 추가 API
    public Post addPost(@RequestBody Post post) { // @RequestBody Post post: 사용자가 보낸 JSON 데이터를 Post 객체로 변환
        return postService.addPost(post); // 서비스 호출하여 글 저장
    }

    @GetMapping // 글 목록 조회 API
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/{id}") // 특정 id의 글 조회 API
    public Post getPostById(@PathVariable int id) { // @PathVariable int id: URL에서 {id} 값을 가져와서 id 변수에 저장
        for (Post post : postService.getAllPosts()) {
            if (post.getId() == id) {
                return post;
            }
        }
        return null; // 글이 없으면 null 반환
    }

    @PutMapping("/{id}") // 특정 id의 글 수정 API
    public Post updatePost(@PathVariable int id, @RequestBody Post updatedPost) {
        for (Post post : postService.getAllPosts()) {
            if (post.getId() == id) {
                post.setTitle(updatedPost.getTitle());
                post.setContent(updatedPost.getContent());
                post.setAuthor(updatedPost.getAuthor());
                return post;
            }
        }
        return null; // 글이 없으면 null 반환
    }

    @DeleteMapping("/{id}") // 특정 id의 글 삭제 API
    public String deletePost(@PathVariable int id) {
        List<Post> posts = postService.getAllPosts();
        for (Post post : posts) {
            if (post.getId() == id) {
                posts.remove(post);
                return "삭제 완료";
            }
        }
        return "삭제할 글을 찾을 수 없습니다.";
    }
}