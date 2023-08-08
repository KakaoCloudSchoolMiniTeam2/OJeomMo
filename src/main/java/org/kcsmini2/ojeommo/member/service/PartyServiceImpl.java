package org.kcsmini2.ojeommo.member.service;

import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.board.repository.GatherBoardRepository;
import org.kcsmini2.ojeommo.member.data.dto.PartyMemberDetailResponseDTO;
import org.kcsmini2.ojeommo.member.data.entity.Member;
import org.kcsmini2.ojeommo.member.data.entity.Party;
import org.kcsmini2.ojeommo.member.repository.MemberRepository;
import org.kcsmini2.ojeommo.member.repository.PartyRepository;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@EnableWebSecurity
public class PartyServiceImpl implements PartyService {
    private final PartyRepository partyRepository;
    private final MemberRepository memberRepository;
    private final GatherBoardRepository gatherBoardRepository;

    public List<PartyMemberDetailResponseDTO> readParty(Long boardId) {

        List<Party> party = partyRepository.findAllByBoardId(boardId);
        List<Member> members = new ArrayList<>();
        party.forEach(person -> {
            members.add(memberRepository.findById(person.getMemberId()).orElseThrow());
        });
        List<PartyMemberDetailResponseDTO> returnValue = new ArrayList<>();
        members.
                forEach(member -> {
                    returnValue.add(PartyMemberDetailResponseDTO.from(member));
                });
        return returnValue;
    }

}
