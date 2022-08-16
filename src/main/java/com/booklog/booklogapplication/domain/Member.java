package com.booklog.booklogapplication.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Member extends Timestamped{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId; // 회원 엔티티의 키

    @Column(nullable = true)
    private String account;  // 회원 아이디

    @Column(nullable = true)
    private String password;  // 회원 비번

    @Column(nullable = true)
    private String nickname;  // 회원 닉네임

    @Column(nullable = true)
    private String imageUrl;  // 회원 프로필 이미지


}
