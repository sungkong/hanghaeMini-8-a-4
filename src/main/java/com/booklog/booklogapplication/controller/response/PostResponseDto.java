package com.booklog.booklogapplication.controller.response;

import com.booklog.booklogapplication.domain.Post;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class PostResponseDto {
    
    private Long id;  // pk
    private String title;  // 제목
    private String content;  // 내용
    private String nickname;  // 게시글 작성자
    private String bookTitle;  // 책 제목
    private String author;  // 작가
    private List<CommentResponseDto> commentResponseDtoList; //댓글 목록
    private String imageUrl;  // 업로드 이미지
    // category
    private LocalDateTime createdAt;
    private LocalDateTime modifiedAt;

    public PostResponseDto(Post entity) {
        this.id = entity.getPostId();
        this.title = entity.getTitle();
        this.content = entity.getContent();
        this.nickname = entity.getNickname();
        this.bookTitle = entity.getBookTitle();
        this.author = entity.getAuthor();
    }

}


