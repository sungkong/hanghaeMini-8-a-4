package com.booklog.booklogapplication.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Post extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @Column(nullable = false)  // 게시글 제목
    private String title;

    @Column(nullable = false)  // 게시글 내용
    private String content;

//    @JoinColumn(name = "member_id", nullable = false)
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Member member;  // 게시글 작성자 정보를 담고 있는 회원객체

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = false)  // 책 제목
    private String bookTitle;

    @Column(nullable = false)  // 책 작가
    private String author;

    @Column(nullable = true)  // 게시글 업로드 이미지
    private String imageUrl;

    @Column(nullable = true)  // default value
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)  // 게시글이 지워질 때, 게시글 아래 모든 댓글은 자동 삭제
    private List<Comment> commentList;  // 게시글을 데려올 때 댓글 전체를 리스트로 데려옴

//    @OneToOne
//    private Category category;
}
