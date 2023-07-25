package org.kcsmini2.ojeommo.board.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.kcsmini2.ojeommo.board.data.MemberDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.bumped.GatherBoardBumpedRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.create.GatherBoardCreateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.response.detail.BoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.data.entity.Board;
import org.kcsmini2.ojeommo.board.data.entity.GatherBoard;
import org.kcsmini2.ojeommo.board.repository.BoardRepository;
import org.kcsmini2.ojeommo.board.repository.CategoryRepository;
import org.kcsmini2.ojeommo.board.repository.GatherBoardRepository;
import org.kcsmini2.ojeommo.board.repository.MemberRepository;
import org.kcsmini2.ojeommo.category.entity.Category;
import org.kcsmini2.ojeommo.member.data.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

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
    @Autowired
    CategoryRepository categoryRepository;

    //클래스로 테스트하고 싶으면 Nested annotation 쓰면 됨
    @Nested
    @DisplayName("게시글 관리 테스트")
    @Transactional
    @DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
    class ManageBoard {
        @Test
        @DisplayName("게시글 생성 요청시 정상 정보 입력하면 게시글이 생성된다.")
        void createBoard() {
            //given
            GatherBoardCreateRequestDTO requestDTO = GatherBoardCreateRequestDTO.builder()
                    .title("만리장성")
                    .content("인원모집")
                    .createdAt(LocalDateTime.now())
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
            gatherBoardService.createBoard(requestDTO, MemberDTO.from(member));

            //then

            Board board = boardRepository.findById(1l).get();
            GatherBoard gatherBoard = gatherBoardRepository.findById(1l).get();
            assertThat(board.getTitle()).isEqualTo(requestDTO.getTitle());
            assertThat(board.getContent()).isEqualTo(requestDTO.getContent());
            assertThat(board.getCreatedAt()).isEqualTo(requestDTO.getCreatedAt());
            assertThat(board.getAuthor().getId()).isEqualTo(member.getId());
            assertThat(gatherBoard.getGatherNumber()).isEqualTo(requestDTO.getGatherNumber());
            assertThat(gatherBoard.getDinerName()).isEqualTo(requestDTO.getDinerName());
            assertThat(gatherBoard.getBumpedAt()).isEqualTo(requestDTO.getBumpedAt());
            assertThat(gatherBoard.getIsDelivery()).isEqualTo(requestDTO.getIsDelivery());
            assertThat(gatherBoard.getInitNumber()).isEqualTo(requestDTO.getInitNumber());
            assertThat(gatherBoard.getCategory()).isEqualTo(requestDTO.getCategory());
        }

        @Test
        @DisplayName("게시글 클릭시 정상 요청이라면 게시글 정보를 반환한다.")
        @Rollback
        void readBoard() {
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
            BoardDetailResponseDTO boardDetailResponseDTO = gatherBoardService.readBoard(savedGatherBoard.getId(), MemberDTO.from(member));
            //then
            assertThat(boardDetailResponseDTO.getId()).isEqualTo(savedGatherBoard.getId());
            assertThat(boardDetailResponseDTO.getTitle()).isEqualTo(savedGatherBoard.getBoard().getTitle());
        }

        @Test
        @DisplayName("게시글 삭제 요청시 게시글을 삭제한다.")
        @Rollback
        void deleteBoard() {
            //given
            Category category = categoryRepository.save(Category.builder().category("중식").build());
            GatherBoardCreateRequestDTO requestDTO = GatherBoardCreateRequestDTO.builder()
                    .title("만리장성")
                    .content("인원모집")
                    .createdAt(LocalDateTime.now())
                    .category(category)
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
            gatherBoardService.createBoard(requestDTO, MemberDTO.from(member));
            assertThat(gatherBoardRepository.count()).isEqualTo(1);
            //when
            gatherBoardService.deleteBoard(1l, MemberDTO.from(member));
            //then
            assertThat(gatherBoardRepository.count()).isEqualTo(0);

        }

        @Test
        @DisplayName("게시글 끌올 요청시 1시간이 지나지 않아 실패한다")
        @Rollback
        void failBumpBoard() throws InterruptedException {
            //given
            Category category = categoryRepository.save(Category.builder().category("중식").build());
            GatherBoardCreateRequestDTO requestDTO = GatherBoardCreateRequestDTO.builder()
                    .title("만리장성")
                    .content("인원모집")
                    .createdAt(LocalDateTime.now())
                    .category(category)
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
            gatherBoardService.createBoard(requestDTO, MemberDTO.from(member));

            int year = 2023, month = 7, dayOfMonth = 25, hour = 20, minute = 40, second = 44;
            LocalDateTime of = LocalDateTime.of(year, month, dayOfMonth, hour, minute, second);
            Thread.sleep(1000);
            GatherBoardBumpedRequestDTO gatherBoardBumpedRequestDTO = new GatherBoardBumpedRequestDTO(LocalDateTime.now());
            //when
            Throwable throwable = catchThrowable(
                    () -> gatherBoardService.bumpBoard(gatherBoardBumpedRequestDTO, 1l, MemberDTO.from(member)));

            //then
            assertThat(throwable)
                    .hasMessage("끌올 요청 후 1시간이 지나지 않았습니다.");
        }

        @Test
        @DisplayName("게시글 끌올 요청시 끌올 시간을 수정한다")
        @Rollback
        void successBumpBoard(){
            //given
            Category category = categoryRepository.save(Category.builder().category("중식").build());
            GatherBoardCreateRequestDTO requestDTO = GatherBoardCreateRequestDTO.builder()
                    .title("만리장성")
                    .content("인원모집")
                    .createdAt(LocalDateTime.now())
                    .category(category)
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
            gatherBoardService.createBoard(requestDTO, MemberDTO.from(member));

            LocalDateTime hoursLate = LocalDateTime.now().plusHours(1);
            hoursLate.plusMinutes(1);
            GatherBoardBumpedRequestDTO gatherBoardBumpedRequestDTO = new GatherBoardBumpedRequestDTO(hoursLate);
            //when
            gatherBoardService.bumpBoard(gatherBoardBumpedRequestDTO, 1l, MemberDTO.from(member));

            //then
            GatherBoard findBoard = gatherBoardRepository.findById(1l).get();
            assertThat(findBoard.getBumpedAt())
                    .isEqualTo(hoursLate);
        }
    }
}