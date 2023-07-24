package org.kcsmini2.ojeommo.board.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.kcsmini2.ojeommo.board.data.dto.responese.detail.BoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.service.BoardService;
import org.kcsmini2.ojeommo.board.service.GatherBoardService;
import org.kcsmini2.ojeommo.member.data.MemberDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {
    //여기 다시해야댐
    @Autowired
    private final GatherBoardService boardService;

    @GetMapping("/view/{id}")
    public String readDetail(Model model,@PathVariable Long boardId, MemberDTO memberDTO) {
        BoardDetailResponseDTO dto = boardService.getDetail(boardId, memberDTO);
        model.addAttribute("view", dto);

        return "board/views";
    }
}
