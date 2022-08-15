package hanghae8mini.booglogbackend.controller;

import hanghae8mini.booglogbackend.controller.requestDto.LoginRequestDto;
import hanghae8mini.booglogbackend.controller.requestDto.MemberRequestDto;

import hanghae8mini.booglogbackend.controller.responseDto.ResponseDto;
import hanghae8mini.booglogbackend.repository.MemberRepository;
import hanghae8mini.booglogbackend.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/user")
public class MemberController {

    private final MemberService memberService;

    private final MemberRepository memberRepository;

    //회원가입
    @PostMapping("/signup") //테스트 ok
    public ResponseDto<?> signUp(@RequestBody @Valid MemberRequestDto requestDto) {return memberService.signUp(requestDto);}

    //로그인
    @PostMapping("/login") //테스트 ok
    public ResponseDto<?> login(@RequestBody LoginRequestDto requestDto, HttpServletResponse response){
        System.out.println("1");
        return memberService.login(requestDto,response);
    }

    //아이디 중복확인
    @GetMapping("/idCheck/{account}") //테스트 ok
    public ResponseDto<?> idCheck(@PathVariable String account) {
        return memberService.idCheck(account);
    }


    //닉네임 중복확인 //테스트 ok
    @GetMapping("/nicknameCheck/{nickname}") //테스트 ok
    public ResponseDto<?> nicknameCheck(@PathVariable String nickname) {
        return memberService.nicknameCheck(nickname);
    }

    //로그아웃
    @GetMapping("/logout") //테스트 ok
    public ResponseDto<?> logOut(HttpServletRequest request) {return memberService.logout(request);}

//    @GetMapping("/delete")
//    public String del(){
//        memberRepository.deleteAll();
//        return "삭제됨 ㅋ";
//    }




}
