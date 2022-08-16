package com.booklog.booklogapplication.controller;


import com.booklog.booklogapplication.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RequiredArgsConstructor
@RestController
public class MemberController {

//    private final MemberService memberService;
    private final S3Service s3Service;

    // 회원가입 & 프로필사진 업로드
    @RequestMapping(value = "/api/image", method = RequestMethod.POST)
    public String uploadProfileImg(@RequestPart(required = false, value = "file") MultipartFile multipartFile) throws IOException {
        return s3Service.uploadFile(multipartFile);
    }

//    @RequestMapping(value = "/api/user/signup", method = RequestMethod.POST)
//    public ResponseDto<?> signup(@RequestBody @Valid MemberRequestDto requestDto,
//                                 @RequestPart(required = false, value = "file") MultipartFile multipartFile) {
//        return memberService.createMember(requestDto);
//    }
}
