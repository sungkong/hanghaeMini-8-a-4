package com.booklog.booklogapplication.service;

import com.booklog.booklogapplication.controller.request.PostRequestDto;
import com.booklog.booklogapplication.controller.response.CommentResponseDto;
import com.booklog.booklogapplication.controller.response.PostResponseDto;
import com.booklog.booklogapplication.controller.response.ResponseDto;
import com.booklog.booklogapplication.domain.Comment;
import com.booklog.booklogapplication.domain.Post;
import com.booklog.booklogapplication.repository.CommentRepository;
import com.booklog.booklogapplication.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;
    private final CommentRepository commentRepository;

//    private final TokenProvider tokenProvider;

    // 게시글 작성
    @Transactional
    public ResponseDto<?> createPost(PostRequestDto requestDto) {  // 게시글 작성에서는 따로 comment 뽑을 필요 x

        Post post = Post.builder()
                .title(requestDto.getTitle())  // 제목
                .content(requestDto.getContent())  // 내용
                .nickname(requestDto.getNickname())  // 작성자
                .bookTitle(requestDto.getBookTitle())  // 책제목
                .author(requestDto.getAuthor())  // 책 저자
                .imageUrl(requestDto.getImageUrl())
                .build();
        postRepository.save(post);
        return ResponseDto.success(
                PostResponseDto.builder()
                        .id(post.getPostId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .nickname(post.getNickname())
                        .bookTitle(post.getBookTitle())
                        .author(post.getAuthor())
                        .imageUrl(post.getImageUrl())
                        .createdAt(post.getCreatedAt())
                        .modifiedAt(post.getModifiedAt())
                        .build()
        );
    }

    // 게시글 전체 조회
    // postId, title, nickname, content(일부), bookTitle, author, *category
    @Transactional(readOnly = true)
    public ResponseDto<?>  getAllPosts() {
        return ResponseDto.success(postRepository.findAllByOrderByModifiedAtDesc().stream().map(PostResponseDto::new).collect(Collectors.toList()));
    }

    // 특정 게시글 조회
    @Transactional(readOnly = true)
    public ResponseDto<?> getPost(Long id) {  // param: 게시글 id
        Post post = isPresentPost(id);  
        if (null == post) {
            return ResponseDto.fail("NOT_FOUND", "존재하지 않는 게시글 id 입니다.");
        }

        List<Comment> commentList = commentRepository.findAllByPost(post);  // comment List 데려오기
        List<CommentResponseDto> commentResponseDtoList = new ArrayList<>();  // 최종 보여줄 댓글 꾸러미

        for (Comment comment : commentList) {
            commentResponseDtoList.add(
                    CommentResponseDto.builder()
                            .commentId(comment.getCommentId())
                            .nickname(comment.getNickname())
                            .content(comment.getContent())
                            .createdAt(comment.getCreatedAt())
                            .modifiedAt(comment.getModifiedAt())
                            .build()
            );
        }

        return ResponseDto.success(
                PostResponseDto.builder()
                        .id(post.getPostId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .nickname(post.getNickname())
                        .bookTitle(post.getBookTitle())
                        .author(post.getAuthor())
                        .commentResponseDtoList(commentResponseDtoList)
                        // category
                        .createdAt(post.getCreatedAt())
                        .modifiedAt(post.getModifiedAt())
                        .build()
        );
    }
    
    @Transactional(readOnly = true)
    public Post isPresentPost(Long id) {  // 게시글 존재 여부 검증 로직
        Optional<Post> optionalPost = postRepository.findById(id);
        return optionalPost.orElse(null);
    }
}

