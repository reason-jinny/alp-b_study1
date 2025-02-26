package com.kt.blog.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity // JPA 엔티티 선언
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동 증가 ID
    private Long id; // int -> Long으로 수정: JPA의 @GeneratedValue는 기본적으로 Long을 선호

    private String title;
    private String content;
    private String author;

    public Post() { // JPA에서는 기본 생성자 필수 - 엔터티 만들 떄 필요
    }

    public Post(String title, String content, String author) {
        // this.id = id; // ID가 자동 생성되므로 직접 설정하면 안 됨.
        this.title = title;
        this.content = content;
        this.author = author;
    }
}
