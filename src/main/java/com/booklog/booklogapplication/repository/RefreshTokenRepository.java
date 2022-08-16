package com.booklog.booklogapplication.repository;

import com.booklog.booklogapplication.domain.Member;
import com.booklog.booklogapplication.domain.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
  Optional<RefreshToken> findByMember(Member member);
}
