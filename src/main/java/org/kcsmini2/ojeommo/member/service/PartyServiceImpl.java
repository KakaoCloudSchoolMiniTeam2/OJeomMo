package org.kcsmini2.ojeommo.member.service;

import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.board.data.dto.request.delete.QuitPartyRequestDto;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.kcsmini2.ojeommo.board.repository.GatherBoardRepository;
import org.kcsmini2.ojeommo.exception.ApplicationException;
import org.kcsmini2.ojeommo.exception.ErrorCode;
import org.kcsmini2.ojeommo.member.data.dto.MemberDTO;
import org.kcsmini2.ojeommo.member.data.dto.PartyMemberDetailResponseDTO;
import org.kcsmini2.ojeommo.member.data.entity.Member;
import org.kcsmini2.ojeommo.member.data.entity.Party;
import org.kcsmini2.ojeommo.member.repository.MemberRepository;
import org.kcsmini2.ojeommo.member.repository.PartyRepository;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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
        return party.stream()
                .map(person -> memberRepository.findById(person.getMemberId()))
                .map(foundMember -> foundMember.orElseThrow(() -> new ApplicationException(ErrorCode.ID_NOT_FOUND)))
                .map(PartyMemberDetailResponseDTO::from)
                .toList();
    }


    @Transactional
    public boolean joinParty(Long boardId, MemberDTO memberDTO) {
        //보드엔티티를 불러옴
        GatherBoard board = gatherBoardRepository.findById(boardId)
                .orElseThrow(() -> new ApplicationException(ErrorCode.BOARD_NOT_FOUND));

        if (board.isPartyFull()) {
            throw new ApplicationException(ErrorCode.PARTY_ALREADY_FULL);
        }


        //작성자와 요청자가 같다면 예외 반환
        if (board.isSameMember(memberDTO)) {
            throw new ApplicationException(ErrorCode.INVALID_JOIN);
        }

        //멤버엔티티를 불러옴
        Member partyMember = memberRepository.getReferenceById(memberDTO.getId());

        //파티엔티티를 만들어줌
        Party party = Party.builder()
                .member(partyMember)
                .board(board)
                .joinedAt(LocalDateTime.now())
                .build();

        //파티 엔티티를 저장함
        partyRepository.save(party);

        return true;
    }

    @Override
    public boolean quitParty(QuitPartyRequestDto requestDto, MemberDTO memberDTO) {

        // 기존 파티 엔티티 불러옴
        Party foundParty = partyRepository.findByMemberIdAndBoardId(memberDTO.getId(), requestDto.getBoardId());
        if (foundParty == null) {
            throw new RuntimeException("참여하지 않은 파티입니다.");
        }

        // 파티 탈퇴 처리
        partyRepository.delete(foundParty);

        return true;
    }
}
