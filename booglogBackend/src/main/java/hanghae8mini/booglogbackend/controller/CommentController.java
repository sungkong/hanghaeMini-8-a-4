package hanghae8mini.booglogbackend.controller;


import hanghae8mini.booglogbackend.controller.request.CommentRequestDto;
import hanghae8mini.booglogbackend.controller.response.ResponseDto;
import hanghae8mini.booglogbackend.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @RequestMapping(value = "/post/{postId}/comment", method = RequestMethod.POST)
    public ResponseDto<?> createComment(@RequestBody CommentRequestDto requestDto, HttpServletRequest request) {
        return commentService.createComment(requestDto, request);
    }

    // 댓글 수정
    @RequestMapping(value = "/post/{postId}/{commentId}", method = RequestMethod.PUT)
    public ResponseDto<?> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto, HttpServletRequest request) {
        return commentService.updateComment(id, requestDto, request);
    }

    // 댓글 삭제
    @RequestMapping(value = "/post/{postId}/{commentId}", method = RequestMethod.DELETE)
    public ResponseDto<?> deleteComment(@PathVariable Long id, HttpServletRequest request) {
        return commentService.deleteComment(id, request);
    }

}
