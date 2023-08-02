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

    @Query(value = "SELECT * FROM MEMBER WHERE (id != :id) and (email = :email)", nativeQuery = true)
    Optional<Member> findByEmailWithOutSelf(@Param("id") String id, @Param("email") String email);
}
