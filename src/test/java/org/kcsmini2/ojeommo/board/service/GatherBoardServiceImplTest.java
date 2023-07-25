package org.kcsmini2.ojeommo.board.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kcsmini2.ojeommo.board.data.MemberDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.create.BoardCreateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.create.GatherBoardCreateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.response.create.BoardCreateResponseDTO;
import org.kcsmini2.ojeommo.board.data.dto.response.create.GatherBoardCreateResponseDTO;
import org.kcsmini2.ojeommo.board.data.dto.response.detail.BoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.kcsmini2.ojeommo.board.repository.BoardRepository;
import org.kcsmini2.ojeommo.board.repository.GatherBoardRepository;
import org.kcsmini2.ojeommo.board.repository.MemberRepository;
import org.kcsmini2.ojeommo.category.entity.Category;
import org.kcsmini2.ojeommo.member.data.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.stereotype.Component;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

//(classes = {GatherBoardServiceImpl.class}) -> 이거 쓰면은 주입에서 문제터지는데 내일 태민이 햄한테 물어보기
@SpringBootTest
@DisplayName("비즈니스 로직 - 게시글 관리 서비스")
class GatherBoardServiceImplTest {

    @Autowired
    GatherBoardService gatherBoardService;

    @Autowired
    GatherBoardRepository gatherBoardRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    MemberRepository memberRepository;

    @Nested
    @DisplayName("게시글 관리 테스트")
    @Transactional
    //클래스로 테스트하고 싶으면 Nested annotation 쓰면 됨
    class ReadBoard{
        @Test
        @DisplayName("게시글 클릭시 정상 요청이라면 게시글 정보를 반환한다.")
//        @Rollback(value = false)
        void readBoard(){
            //given
            Member member = Member.builder()
                    .nickname("abcd")
                    .email("ab@aba")
                    .name("hong")
                    .pw("qwer1234")
                    .id("abcd1234")
                    .build();

            Member savedMember = memberRepository.save(member);

            Board board = Board.builder()
                    .author(savedMember)
                    .title("만리장성")
                    .createdAt(LocalDateTime.now())
                    .content("aaa")
                    .build();
            Board savedBoard = boardRepository.save(board);

            GatherBoard gatherBoard = GatherBoard.builder()
                    .board(savedBoard)
                    .gatherNumber(6)
                    .isDelivery(true)
                    .initNumber(1)
                    .dinerName("만리장성")
                    .build();
            GatherBoard savedGatherBoard = gatherBoardRepository.save(gatherBoard);
            //when
            BoardDetailResponseDTO boardDetailResponseDTO = gatherBoardService.readBoard(1l, MemberDTO.from(member));
            //then
            assertThat(boardDetailResponseDTO.getId()).isEqualTo(savedGatherBoard.getId());
            assertThat(boardDetailResponseDTO.getTitle()).isEqualTo(savedGatherBoard.getBoard().getTitle());
        }

        @Test
        @DisplayName("게시글 생성 요청시 정상 정보 입력하면 게시글이 생성된다.")
        void createBoard() {
            //given
            GatherBoardCreateRequestDTO gatherBoardCreateRequestDTO = GatherBoardCreateRequestDTO.builder()
                    .category(new Category("중식"))
                    .bumpedAt(LocalDateTime.now())
                    .gatherNumber(6)
                    .dinerName("만리장성")
                    .initNumber(1)
                    .build();

            Member member = Member.builder()
                    .id("abcd")
                    .pw("abcd")
                    .name("hong")
                    .email("aaa")
                    .nickname("hong")
                    .build();
            memberRepository.save(member);

            //when
            Member findMember = memberRepository.findById("abcd").orElseThrow();
            Board newBoard = gatherBoardCreateRequestDTO.toEntity(findMember);
            GatherBoardCreateResponseDTO gatherBoardCreateResponseDTO;
            //then
        }
    }



    @Test
    @DisplayName("게시글 조회 기능")
    void readBoard() {

    }

    @Test
    void bumpedUp() {
    }
}