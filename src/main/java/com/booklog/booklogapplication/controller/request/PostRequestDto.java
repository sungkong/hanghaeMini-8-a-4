package com.booklog.booklogapplication.controller.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PostRequestDto {
    private String title;  // 게시글 제목
    private String nickname;  // 게시글 작성자
    private String content;  // 게시글 본문
    private String bookTitle;  // 책 제목
    private String author;  // 책 저자
    private String imageUrl;  // 이미지 url
    // 책 카테고리 -- enum 어케 처리할지 생각하긔
}
