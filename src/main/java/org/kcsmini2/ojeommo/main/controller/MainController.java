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
public class MainController {

    private final GatherBoardService gatherBoardService;
    private final PaginationService paginationService;

    @GetMapping("main")
    public String getIndex(@AuthenticationPrincipal MemberDTO memberDTO,
                           @PageableDefault(size = 4, sort = "bumpedAt", direction = Sort.Direction.DESC) Pageable pageable,
                           ModelMap map) {

        Page<BoardDetailResponseDTO> responseDtoPage = gatherBoardService.readBoardPage(pageable, memberDTO);
        List<Integer> pageNumbers = paginationService.getPaginationBar(pageable.getPageNumber(), responseDtoPage.getTotalPages());

        if (memberDTO != null) {
            map.addAttribute("profile", memberDTO);
            map.addAttribute("loginCheck", 1);
        } else {
            map.addAttribute("loginCheck", 0);
        }

        map.addAttribute("articles", responseDtoPage);
        map.addAttribute("pageNumbers", pageNumbers);
//        System.out.println(pageNumbers);
        return "main";
    }

    @GetMapping("my-board")
    public String getMyBoard(@AuthenticationPrincipal MemberDTO memberDTO,
                           @PageableDefault(size = 4, sort = "bumpedAt", direction = Sort.Direction.DESC) Pageable pageable,
                           ModelMap map) {

        Page<BoardDetailResponseDTO> responseDtoPage = gatherBoardService.readMyBoardPage(pageable, memberDTO);
        List<Integer> pageNumbers = paginationService.getPaginationBar(pageable.getPageNumber(), responseDtoPage.getTotalPages());

        if (memberDTO != null) {
            map.addAttribute("profile", memberDTO);
        }

        map.addAttribute("articles", responseDtoPage);
        map.addAttribute("pageNumbers", pageNumbers);

        return "myBoard";
    }

    @GetMapping("detail")
    public String detail(){return "fragment/gather_detail";}

    @GetMapping("create")
    public String createhtml(){return "fragment/gather_create";}

    @GetMapping("total")
    public String Total() {
        return "total";
    }

    @GetMapping("review")
    public String Review() {
        return "review";
    }

    @GetMapping("roulette")
    public String Roulette() {
        return "roulette";
    }
}
