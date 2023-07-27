package org.kcsmini2.ojeommo.board.controller;

import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.board.data.MemberDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.bumped.GatherBoardBumpedRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.create.BoardCreateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.response.detail.BoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.service.GatherBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * 작성자: 김연수, 김종민
 * <p>
 * 설명: 게시판 컨트롤러
 * <p>
 * 최종 수정 일자: 2023.07.22
 */
@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final GatherBoardService gatherBoardService;

    @PostMapping("/createGatherBoard")
    public String CreateGatherBoardPOST(BoardCreateRequestDTO requestDTO, MemberDTO memberDTO) throws Exception {
        gatherBoardService.createBoard(requestDTO, memberDTO);
        return "redirect:/main";
    }

    @GetMapping("/readGatherBoard/{id}")
    public String ReadGatherBoardGET(Model model, @PathVariable("id") Long boardId, MemberDTO memberDTO) {
        BoardDetailResponseDTO dto = gatherBoardService.readBoard(boardId, memberDTO);
        model.addAttribute("gatherDetail", dto);
        return "board/views";
    }

    @PostMapping("/deleteGatherBoard/{id}")
    public String DeleteGatherBoardPost(@PathVariable("id") Long boardId, MemberDTO memberDTO) {
        gatherBoardService.deleteBoard(boardId, memberDTO);
        return "redirect:/main";
    }

    @PostMapping("/bumpGatherBoard/{id}")
    public String BumpedGatherBoardPost(GatherBoardBumpedRequestDTO gatherBoardBumpedRequestDTO, @PathVariable("id") Long boardId, MemberDTO memberDTO) {
        gatherBoardService.bumpBoard(gatherBoardBumpedRequestDTO, boardId, memberDTO);
        return "redirect:/readGatherBoard/{id}";
    }
}