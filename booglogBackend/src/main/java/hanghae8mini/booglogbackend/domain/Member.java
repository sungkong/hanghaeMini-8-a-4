package hanghae8mini.booglogbackend.domain;

import hanghae8mini.booglogbackend.controller.requestDto.MemberRequestDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Entity
public class Member extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @Column(nullable = false)
    private String account;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String nickname;

    @Column(nullable = true)
    private String imageUrl;

    public Member(MemberRequestDto requestDto) {
        this.account = requestDto.getAccount();
        this.password = requestDto.getPassword();
        this.nickname = requestDto.getNickname();
        this.imageUrl = requestDto.getImageUrl();
    }



}
