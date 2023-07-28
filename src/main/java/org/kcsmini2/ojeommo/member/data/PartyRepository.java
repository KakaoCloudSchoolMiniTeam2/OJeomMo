package org.kcsmini2.ojeommo.member.data;

import org.kcsmini2.ojeommo.member.data.entity.Member;
import org.kcsmini2.ojeommo.member.data.entity.Party;
import org.kcsmini2.ojeommo.member.data.entity.PartyPk;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface PartyRepository extends JpaRepository<Party, PartyPk> {


    Party findByMemberIdAndBoardId(String memberId, Long boardId);
    Integer countByBoardId(Long boardId);
    Optional<Party> findPartyByMemberIdAndJoinedAtAfter(String memberId, LocalDateTime today);

}
