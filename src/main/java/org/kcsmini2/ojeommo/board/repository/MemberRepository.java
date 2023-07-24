package org.kcsmini2.ojeommo.board.repository;

import org.kcsmini2.ojeommo.member.data.entity.PartyPk;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<PartyPk.Member,String> {
}
