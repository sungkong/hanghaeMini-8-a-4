package com.booklog.booklogapplication.controller.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CommentRequestDto {
    private Long postId;
    private String nickname;  // 작성자
    private String content;
}
