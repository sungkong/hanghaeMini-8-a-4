package com.booklog.booklogapplication.domain;

import com.booklog.booklogapplication.controller.request.CommentRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Comment extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;  // 댓글 엔티티의 키
    
    @Column(nullable = false)
    private String content;  // 댓글 내용
    
//    @JoinColumn(name ="member_id", nullable = false)
//    @ManyToOne(fetch = FetchType.LAZY)
//    private Member member;  // 댓글을 작성한 회원
    @Column
    private String nickname;

    @JoinColumn(name = "post_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Post post;  // 댓글이 바라보는 게시글


    public void update(CommentRequestDto commentRequestDto) {
        this.content = commentRequestDto.getContent();
    }
//    public boolean validateMember(Member member) {
//        return !this.member.equals(member);
//    }
    
}
