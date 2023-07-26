package org.kcsmini2.ojeommo.board.service;

import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.board.data.MemberDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.bumped.GatherBoardBumpedRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.create.BoardCreateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.create.GatherBoardCreateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.update.BoardUpdateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.update.GatherBoardUpdateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.response.detail.BoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.data.dto.response.detail.GatherBoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.kcsmini2.ojeommo.board.repository.BoardRepository;
import org.kcsmini2.ojeommo.board.repository.GatherBoardRepository;
import org.kcsmini2.ojeommo.board.repository.MemberRepository;
import org.kcsmini2.ojeommo.comment.CommentRepository;
import org.kcsmini2.ojeommo.comment.data.entity.Comment;
import org.kcsmini2.ojeommo.member.data.entity.Member;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class GatherBoardServiceImpl implements GatherBoardService {

    private final BoardRepository boardRepository;
    private final GatherBoardRepository gatherBoardRepository;
    private final MemberRepository memberRepository;
    private final CommentRepository commentRepository;

    // 게시글 생성

    @Override
    @Transactional
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
    @Transactional
    @Override
    public void bumpBoard(GatherBoardBumpedRequestDTO requestDTO, Long boardId, MemberDTO memberDTO){
        GatherBoard gatherBoard = gatherBoardRepository.findById(boardId)
                .orElseThrow(/*() -> new ApplicationException(ErrorCode.INVALID_ARTICLE_ID)*/);

        checkBumped(gatherBoard, requestDTO);
        checkPermission(gatherBoard, memberDTO);

        requestDTO.bumpedEntity(gatherBoard);
    }

    private void checkBumped(GatherBoard gatherBoard, GatherBoardBumpedRequestDTO requestDTO) {
        LocalDateTime beforeBumpedAt = gatherBoard.getBumpedAt();
        LocalDateTime now = requestDTO.getBumpedAt();

        Duration diff = Duration.between(beforeBumpedAt, now);
        long diffMin = diff.toMinutes();

        if(diffMin < 60l){
            throw new RuntimeException("끌올 요청 후 1시간이 지나지 않았습니다.");
        }
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

    // 끌어올리기
    public void bumpedUp(Long boardId, MemberDTO memberDTO, GatherBoardBumpedRequestDTO requestDTO){
        GatherBoard board = gatherBoardRepository.findById(boardId)
                .orElseThrow(/*() -> new ApplicationException(ErrorCode.INVALID_ARTICLE_ID)*/);

        requestDTO.bumpedEntity(board);
    }



    @Override
    @Transactional
    public void updateBoard(Long boardId, BoardUpdateRequestDTO requestDTO, MemberDTO memberDTO) {
        GatherBoard gatherBoard = gatherBoardRepository.findById(boardId).orElseThrow();

        Board board = gatherBoard.getBoard();


        GatherBoardUpdateRequestDTO gatherBoardUpdateRequestDTO = (GatherBoardUpdateRequestDTO)requestDTO;
        gatherBoardUpdateRequestDTO.updateEntity(board);
        gatherBoardUpdateRequestDTO.updateEntity(gatherBoard);
    }
	
	@Override
    @Transactional
    public void deleteBoard(Long boardId, MemberDTO memberDTO) {
        GatherBoard gatherBoard = gatherBoardRepository.findById(boardId).orElseThrow();
        Board board = gatherBoard.getBoard();
        checkPermission(board, memberDTO);

        gatherBoardRepository.delete(gatherBoard);
    }

    private void checkPermission(Board board, MemberDTO memberDTO){
        if (!Objects.equals(board.getAuthor().getId(), memberDTO.getId())) {
            throw new RuntimeException("게시글 소유자가 아닙니다.");
//            throw new ApplicationException(ErrorCode.INVALID_PERMISSION);//Todo : Error코드 추가 후 변경 요망
        }
    }

    private void checkPermission(GatherBoard gatherBoard, MemberDTO memberDTO){
        if (!Objects.equals(gatherBoard.getBoard().getAuthor().getId(), memberDTO.getId())) {
            throw new RuntimeException("게시글 소유자가 아닙니다.");
//            throw new ApplicationException(ErrorCode.INVALID_PERMISSION);//Todo : Error코드 추가 후 변경 요망
        }
    }

}
