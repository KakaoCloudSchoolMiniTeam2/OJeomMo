package org.kcsmini2.ojeommo.main.controller;

import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.board.data.MemberDTO;
import org.kcsmini2.ojeommo.board.data.dto.response.detail.BoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.service.GatherBoardService;
import org.kcsmini2.ojeommo.board.service.PaginationService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final GatherBoardService gatherBoardService;
    private final PaginationService paginationService;

    @GetMapping()
    public String getIndex(MemberDTO memberDTO,
                           @PageableDefault(size = 4, sort = "bumpedAt", direction = Sort.Direction.DESC) Pageable pageable,
                           ModelMap map){

        Page<BoardDetailResponseDTO> responseDtoPage = gatherBoardService.readBoardPage(pageable, memberDTO);
        List<Integer> pageNumbers = paginationService.getPaginationBar(pageable.getPageNumber(), responseDtoPage.getTotalPages());

        map.addAttribute("articles", responseDtoPage);
        map.addAttribute("pageNumbers", pageNumbers);

        return "main";
    }

    @GetMapping("detail")
    public String detail(){return "fragment/gather_detail";}

    @GetMapping("create")
    public String createhtml(){return "fragment/gather_create";}
}
