package org.kcsmini2.ojeommo.member.repository;

import org.kcsmini2.ojeommo.member.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByEmail(String email);

    Boolean existsByEmailAndIdNot(String email, String id);

    Boolean existsByEmail(String email);

}
