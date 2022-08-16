package com.booklog.booklogapplication.controller;


import com.booklog.booklogapplication.controller.request.CommentRequestDto;
import com.booklog.booklogapplication.controller.response.ResponseDto;
import com.booklog.booklogapplication.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class CommentController {

    private final CommentService commentService;

    // 댓글 작성
    @RequestMapping(value = "/api/comment", method = RequestMethod.POST)
    public ResponseDto<?> createComment(@RequestBody CommentRequestDto requestDto) {
        return commentService.createComment(requestDto);
    }

    // 댓글 수정
    @RequestMapping(value = "/api/post/{id}", method = RequestMethod.PUT)
    public ResponseDto<?> updateComment(@PathVariable Long id, @RequestBody CommentRequestDto requestDto) {
        return commentService.updateComment(id, requestDto);
    }

    // 댓글 삭제
    @RequestMapping(value = "/api/post/{id}", method = RequestMethod.DELETE)
    public ResponseDto<?> deleteComment(@PathVariable Long id) {
        return commentService.deleteComment(id);
    }

}
