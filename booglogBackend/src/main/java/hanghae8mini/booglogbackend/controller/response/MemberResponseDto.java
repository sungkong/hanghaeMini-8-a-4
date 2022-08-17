package hanghae8mini.booglogbackend.controller.responseDto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class MemberResponseDto {
    private String account;
    private String nickname;

    private String accessToken;
    private String refreshToken;
    private String imageUrl;
}
