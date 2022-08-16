package hanghae8mini.booglogbackend.repository;

import hanghae8mini.booglogbackend.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

































    // ----------손성우----------

    // 닉네임 검사
    Optional<Member> findByNickname(String nickname);
    // 아이디 검사
    Optional<Member> findByMemberId(Long memberId);


}
