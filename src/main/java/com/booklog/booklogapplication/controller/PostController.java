package com.booklog.booklogapplication.controller;

import com.booklog.booklogapplication.controller.request.PostRequestDto;
import com.booklog.booklogapplication.controller.response.ResponseDto;
import com.booklog.booklogapplication.domain.Post;
import com.booklog.booklogapplication.repository.PostRepository;
import com.booklog.booklogapplication.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    private final PostRepository postRepository;

    // 게시글 작성
    @RequestMapping(value = "api/post", method = RequestMethod.POST)
    public ResponseDto<?> createPost(@RequestBody PostRequestDto requestDto) {
        return postService.createPost(requestDto);
    }

    // 전체 게시글 조회
    @RequestMapping(value="/api/post", method = RequestMethod.GET)
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // 개별 게시글 조회 by postId
    @RequestMapping(value = "api/post/{id}", method = RequestMethod.GET)
    public ResponseDto<?> getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }
}
