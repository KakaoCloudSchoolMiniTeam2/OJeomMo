package org.kcsmini2.ojeommo.member.repository;

import org.kcsmini2.ojeommo.member.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
    Optional<Member> findByEmail(String email);

    boolean findByEmailAndIdNot(String email, String id);

    boolean existsByEmail(String email);

}
