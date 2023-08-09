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

    @PostMapping("/toUpdateGatherBoardPage")
    public String LinkUpdateGatherBoardPagePost(ModelMap model, Long boardId, @AuthenticationPrincipal MemberDTO memberDTO) {
        GatherBoardDetailResponseDTO dto = gatherBoardService.readBoard(boardId, memberDTO);
        model.addAttribute("gatherDetail", dto);
        return "/fragment/gather_modify";//연수가 update page로 연결시켜야 됨
    }

    @PostMapping("/updateGatherBoardPage")
    public String UpdateGatherBoardPage(@Valid GatherBoardUpdateRequestDTO gatherBoardUpdateRequestDTO,
                                        BindingResult bindingResult,
                                        @AuthenticationPrincipal MemberDTO memberDTO) {
        if(bindingResult.hasErrors()) {
            throw new ApplicationException(ErrorCode.NULL_FIELD);
        }

        System.out.println("처리전");
        gatherBoardService.updateBoard(gatherBoardUpdateRequestDTO, memberDTO);
        System.out.println("처리후");
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
