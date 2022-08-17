package hanghae8mini.booglogbackend.controller;

import hanghae8mini.booglogbackend.controller.request.PostRequestDto;
import hanghae8mini.booglogbackend.controller.response.ResponseDto;
import hanghae8mini.booglogbackend.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class PostController {

    private final PostService postService;

    // 생성
    @RequestMapping(value = "/api/auth/post", method = RequestMethod.POST)
    public ResponseDto<?> createPost(@ModelAttribute PostRequestDto requestDto,
                                     HttpServletRequest request) throws IOException {
        return postService.createPost(requestDto, request);
    }
    // 게시글 상세조회
    @GetMapping(value = "/api/post/{postId}")
    public ResponseDto<?> createPost(@PathVariable Long postId,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {
        postService.updateView(postId, request, response); // 조회수 증가
        return postService.getPost(postId);
    }

    // 수정 페이지 불러오기
    @GetMapping(value = "/api/updatePostPage/{postId}")
    public ResponseDto<?> getUpdatePostPage(@PathVariable Long postId) {
        return postService.getPost(postId);
    }


    // 메인페이지 게시글 조회
    @GetMapping(value = "/api/post/{lastPostId}/{size}")
    public ResponseDto<?> getAllPost(@PathVariable Long lastPostId, @PathVariable int size) {
        return postService.getAllPost(lastPostId, size);
    }

    // 내가 작성한 글목록 조회 (보류)
    @GetMapping(value = "/api/auth/post")
    public ResponseDto<?> getAllPostByMember(HttpServletRequest request) {
        return postService.getAllPostByMember(request);
    }

    // 게시글 수정
    @PutMapping(value = "/api/auth/post/{postId}")
    public ResponseDto<?> updatePost(@PathVariable Long postId ,@ModelAttribute PostRequestDto requestDto,
                                     HttpServletRequest request) throws IOException {
        return postService.updatePost(postId, requestDto, request);
    }

    @DeleteMapping(value = "/api/auth/post/{postId}")
    public ResponseDto<?> deletePost(@PathVariable Long postId, HttpServletRequest request){
        return postService.deletePost(postId, request);
    }

}
