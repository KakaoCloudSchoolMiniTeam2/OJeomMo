package org.kcsmini2.ojeommo.board.service;

import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.board.data.dto.request.bumped.GatherBoardBumpedRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.responese.detail.BoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.data.dto.responese.detail.GatherBoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.kcsmini2.ojeommo.board.repository.BoardRepository;
import org.kcsmini2.ojeommo.board.repository.GatherBoardRepository;
import org.kcsmini2.ojeommo.member.data.MemberDTO;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class GatherBoardService implements BoardService {
    final GatherBoardRepository gatherBoardRepository;

    @Override
    public BoardDetailResponseDTO getDetail(Long boardId, MemberDTO memberDTO){
        GatherBoard board = gatherBoardRepository.findById(boardId).orElseThrow(/*() -> new ApplicationException(ErrorCode.INVALID_ARTICLE_ID)*/);//ErrorCode 관련 같은데 나중에 추가해야할듯

        boolean isJoined = getGatherJoinStatus(memberDTO, board);

        return GatherBoardDetailResponseDTO.from(board, isJoined);
    }

    public void bumpedUp(Long boardId, MemberDTO memberDTO, GatherBoardBumpedRequestDTO requestDTO){
        GatherBoard board = gatherBoardRepository.findById(boardId)
                .orElseThrow(/*() -> new ApplicationException(ErrorCode.INVALID_ARTICLE_ID)*/);

        requestDTO.bumpedEntity(board);
    }

    private boolean getGatherJoinStatus(MemberDTO memberDTO, GatherBoard board) { //해당 Member가 Gather에 참여했는지 boolean으로 반환
//      추후 구현
//        if (memberDTO != null) {
//            JoinKey joinKey = JoinKey.builder()
//                    .member(boardRepository.getReferenceById(memberDTO.getId()))
//                    .board(board)
//                    .build();
//            return joinRepository.findByVoteKey(joinKey).isPresent();
//        }
        return false; //로그인하지 않은 익명 사용자의 경우 항상 false를 반환
    }

}
