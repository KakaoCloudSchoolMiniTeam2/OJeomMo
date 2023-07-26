package org.kcsmini2.ojeommo.board.service;

import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.board.data.MemberDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.bumped.GatherBoardBumpedRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.create.BoardCreateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.create.GatherBoardCreateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.response.detail.BoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.data.dto.response.detail.GatherBoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.kcsmini2.ojeommo.board.repository.BoardRepository;
import org.kcsmini2.ojeommo.board.repository.GatherBoardRepository;
import org.kcsmini2.ojeommo.board.repository.MemberRepository;
import org.kcsmini2.ojeommo.member.data.PartyRepository;
import org.kcsmini2.ojeommo.member.data.entity.Member;
import org.kcsmini2.ojeommo.member.data.entity.Party;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class GatherBoardServiceImpl implements GatherBoardService {

    private final BoardRepository boardRepository;
    private final GatherBoardRepository gatherBoardRepository;
    private final MemberRepository memberRepository;
    private final PartyRepository partyRepository;

    // 게시글 생성
    @Override
    public void createBoard(BoardCreateRequestDTO requestDTO, MemberDTO memberDTO){
        Member author = memberRepository.findById(memberDTO.getId()).orElseThrow();
        Board board = requestDTO.toEntity(author);

        GatherBoardCreateRequestDTO gatherBoardCreateRequestDTO = (GatherBoardCreateRequestDTO)requestDTO;
        GatherBoard gatherBoard = gatherBoardCreateRequestDTO.toEntity(board);
        gatherBoardRepository.save(gatherBoard);
    }

    // 게시글 조회
    @Override
    public BoardDetailResponseDTO readBoard(Long boardId, MemberDTO memberDTO){
        GatherBoard board = gatherBoardRepository.findById(boardId).orElseThrow(/*() -> new ApplicationException(ErrorCode.INVALID_ARTICLE_ID)*/);//ErrorCode 관련 같은데 나중에 추가해야할듯

        boolean isJoined = getGatherJoinStatus(memberDTO, board);

        return GatherBoardDetailResponseDTO.from(board, isJoined);
    }

    // 끌어올리기
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

    @Transactional
    public boolean joinParty(Long boardId, MemberDTO memberDTO){
        //멤버엔티티를 불러옴
        Member partyMember = memberRepository.getReferenceById(memberDTO.getId());

        //보드엔티티를 불러옴
        Board board = boardRepository.getReferenceById(boardId);

        //파티엔티티를 만들어줌
        Party party = Party.builder()
                .member(partyMember)
                .board(board)
                .joinedAt(LocalDateTime.now())
                .build();

        //파티엔티티를 저장함
        partyRepository.save(party);

        return true;
    }

    @Override
    public Page<BoardDetailResponseDTO> readBoardPage(Pageable pageable, MemberDTO memberDTO) {
        //현재 페이지에 포함된 게시글들을 가져온다
        Page<GatherBoard> gatherBoardPage = gatherBoardRepository.findAllBy(pageable);
        //엔티티를 Dto로 변환하고 반환한다
        return gatherBoardPage
                .map(gatherBoard -> {
            boolean isJoined = getGatherJoinStatus(memberDTO, gatherBoard);
            return GatherBoardDetailResponseDTO.from(gatherBoard, isJoined);
        });
    }


}
