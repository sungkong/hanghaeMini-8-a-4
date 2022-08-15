package hanghae8mini.booglogbackend.aop;

import lombok.RequiredArgsConstructor;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@RequiredArgsConstructor
@Component
public class LoginCheckAop {
/*

    private final TokenProvider tokenProvider;

    @Before("@annotation(com.example.intermediate.annotation.LoginCheck)")
    public ResponseDto<?> loginCheck(){

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
                .getRequestAttributes()).getRequest();

        String message = "";
        String code = "";
        if (null == request.getHeader("Refresh-Token")) {
            message = "MEMBER_NOT_FOUND";
            code = "로그인이 필요합니다!.";
        }

        if (null == request.getHeader("Authorization")) {
            message = "MEMBER_NOT_FOUND";
            code = "로그인이 필요합니다!.";
        }

        return ResponseDto.fail(code,message);
    }

    @Transactional
    public Member validateMember(HttpServletRequest request) {
        if (!tokenProvider.validateToken(request.getHeader("Refresh-Token"))) {
            return null;
        }
        return tokenProvider.getMemberFromAuthentication();
    }
*/


}
