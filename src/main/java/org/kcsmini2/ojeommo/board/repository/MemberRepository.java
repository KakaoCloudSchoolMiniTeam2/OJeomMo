package org.kcsmini2.ojeommo.board.repository;

import org.kcsmini2.ojeommo.member.data.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member,String> {
}
