package org.kcsmini2.ojeommo.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.board.data.dto.request.create.GatherBoardCreateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.create.JoinPartyRequestDto;
import org.kcsmini2.ojeommo.board.data.dto.request.update.GatherBoardUpdateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.response.detail.GatherBoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.service.GatherBoardService;
import org.kcsmini2.ojeommo.exception.ApplicationException;
import org.kcsmini2.ojeommo.exception.ErrorCode;
import org.kcsmini2.ojeommo.member.data.dto.MemberDTO;
import org.kcsmini2.ojeommo.member.data.dto.PartyMemberDetailResponseDTO;
import org.kcsmini2.ojeommo.member.service.PartyService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


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
    private final PartyService partyService;

    @PostMapping("/createGatherBoard")
    public String CreateGatherBoardPOST(@Valid GatherBoardCreateRequestDTO requestDTO,
                                        BindingResult bindingResult,
                                        @AuthenticationPrincipal MemberDTO memberDTO
                                        ) throws Exception {

        if(bindingResult.hasErrors()) {
            throw new ApplicationException(ErrorCode.NULL_FIELD);
        }

        gatherBoardService.createBoard(requestDTO, memberDTO);
        return "redirect:/";
    }

    @GetMapping("/readCreatePage")
    public String ReadCreatePage(@AuthenticationPrincipal MemberDTO memberDTO) throws Exception{
        return "/fragment/gather_create";
    }

    @GetMapping("/readGatherBoard/{id}")
    public String ReadGatherBoardGET(Model model, @PathVariable("id") Long boardId, @AuthenticationPrincipal MemberDTO memberDTO) {
        GatherBoardDetailResponseDTO dto = gatherBoardService.readBoard(boardId, memberDTO);
        List<PartyMemberDetailResponseDTO> partyDTO = partyService.readParty(boardId);

        if (memberDTO != null) {
            model.addAttribute("member", memberDTO);
        }
        if (partyDTO != null) {
            model.addAttribute("party", partyDTO);
        }
        model.addAttribute("gatherDetail", dto);
        return "/fragment/gather_detail";
    }

    @GetMapping("/toUpdateGatherBoardPage/{id}")
    public String LinkUpdateGatherBoardPagePost(@PathVariable("id") Long boardId, Model model, @AuthenticationPrincipal MemberDTO memberDTO) {
        gatherBoardService.checkPermission(boardId, memberDTO);
        GatherBoardDetailResponseDTO dto = gatherBoardService.readBoard(boardId, memberDTO);
        model.addAttribute("gatherDetail", dto);
        return "/fragment/gather_modify";
    }

    @PostMapping("/updateGatherBoardPage")
    public String UpdateGatherBoardPage(GatherBoardUpdateRequestDTO gatherBoardUpdateRequestDTO, @AuthenticationPrincipal MemberDTO memberDTO) {
        gatherBoardService.updateBoard(gatherBoardUpdateRequestDTO, memberDTO);
        return "redirect:/";
    }

    @PostMapping("/deleteGatherBoard")
    public String DeleteGatherBoardPost(Long boardId, @AuthenticationPrincipal MemberDTO memberDTO) {
        gatherBoardService.deleteBoard(boardId, memberDTO);
        return "redirect:/";
    }

    @PostMapping("/bumpGatherBoard")
    public String BumpedGatherBoardPost(Long boardId, @AuthenticationPrincipal MemberDTO memberDTO) {
        gatherBoardService.bumpBoard(boardId, memberDTO);
        return "redirect:/board/readGatherBoard/" + boardId;
    }

    @PostMapping("/joinParty")
    public String JoinParty(JoinPartyRequestDto requestDto, @AuthenticationPrincipal MemberDTO memberDTO) {
        gatherBoardService.joinParty(requestDto.getBoardId(), memberDTO);

        return "redirect:/";
    }

}
