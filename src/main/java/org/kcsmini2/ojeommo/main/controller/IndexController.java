package org.kcsmini2.ojeommo.main.controller;

import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.board.data.dto.response.detail.BoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.service.GatherBoardService;
import org.kcsmini2.ojeommo.board.service.PaginationService;
import org.kcsmini2.ojeommo.member.data.dto.MemberDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;


@Controller()
@RequiredArgsConstructor
public class IndexController {

    @GetMapping()
    public String redirectToMain() {
        return "redirect:/main";
    }

}
