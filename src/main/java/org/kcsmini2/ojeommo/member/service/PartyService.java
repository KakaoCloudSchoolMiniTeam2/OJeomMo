package org.kcsmini2.ojeommo.member.service;

import org.kcsmini2.ojeommo.member.data.dto.PartyMemberDetailResponseDTO;

import java.util.List;

public interface PartyService {
    public List<PartyMemberDetailResponseDTO> readParty(Long boardId);
}
