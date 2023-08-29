package org.kcsmini2.ojeommo.member.service;

import org.kcsmini2.ojeommo.board.data.dto.request.delete.QuitPartyRequestDto;
import org.kcsmini2.ojeommo.member.data.dto.MemberDTO;
import org.kcsmini2.ojeommo.member.data.dto.PartyMemberDetailResponseDTO;

import java.time.LocalDateTime;
import java.util.List;

public interface PartyService {
    public List<PartyMemberDetailResponseDTO> readParty(Long boardId);

    public boolean joinParty(Long boardId, MemberDTO memberDTO);

    public boolean quitParty(QuitPartyRequestDto requestDto, MemberDTO memberDTO);
    public boolean joinCheck(String memberId);
}
