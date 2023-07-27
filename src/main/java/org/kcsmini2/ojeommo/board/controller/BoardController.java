package org.kcsmini2.ojeommo.board.controller;

import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.member.data.dto.MemberDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.bumped.GatherBoardBumpedRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.create.GatherBoardCreateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.update.GatherBoardUpdateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.response.detail.GatherBoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.service.GatherBoardService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


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
    public String CreateGatherBoardPOST(GatherBoardCreateRequestDTO requestDTO, MemberDTO memberDTO) throws Exception {
        gatherBoardService.createBoard(requestDTO, memberDTO);
        return "redirect:/";
    }

    @GetMapping("/readGatherBoard/{id}")
    public String ReadGatherBoardGET(Model model, @PathVariable("id") Long boardId, MemberDTO memberDTO) {
        GatherBoardDetailResponseDTO dto = gatherBoardService.readBoard(boardId, memberDTO);
        model.addAttribute("gatherDetail", dto);
        return "/fragment/gather_detail";
    }

    @PostMapping("/toUpdateGatherBoardPage")
    public String LinkUpdateGatherBoardPagePost(ModelMap model, @PathVariable("id") Long boardId, MemberDTO memberDTO){
        GatherBoardDetailResponseDTO dto = gatherBoardService.readBoard(boardId, memberDTO);
        model.addAttribute("contents", dto);
        return "/fragment/gather_modify";//연수가 update page로 연결시켜야 됨
    }

    @PostMapping("/updateGatherBoardPage")
    public String UpdateGatherBoardPage(GatherBoardUpdateRequestDTO gatherBoardUpdateRequestDTO, @PathVariable("id") Long boardId, MemberDTO memberDTO){
        gatherBoardService.updateBoard(boardId, gatherBoardUpdateRequestDTO, memberDTO);
        return "redirect:/";
    }

    @PostMapping("/deleteGatherBoard")
    public String DeleteGatherBoardPost(@PathVariable("id") Long boardId, MemberDTO memberDTO) {
        gatherBoardService.deleteBoard(boardId, memberDTO);
        return "redirect:/";
    }

    @PostMapping("/bumpGatherBoard")
    public String BumpedGatherBoardPost(GatherBoardBumpedRequestDTO gatherBoardBumpedRequestDTO, @PathVariable("id") Long boardId, MemberDTO memberDTO) {
        gatherBoardService.bumpBoard(gatherBoardBumpedRequestDTO, boardId, memberDTO);
        return "redirect:/readGatherBoard/{id}";
    }

}
