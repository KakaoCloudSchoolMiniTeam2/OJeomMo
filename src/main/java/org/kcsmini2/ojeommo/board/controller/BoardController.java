package org.kcsmini2.ojeommo.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.board.data.dto.request.create.GatherBoardCreateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.request.create.JoinPartyRequestDto;
import org.kcsmini2.ojeommo.board.data.dto.request.delete.QuitPartyRequestDto;
import org.kcsmini2.ojeommo.board.data.dto.request.update.GatherBoardUpdateRequestDTO;
import org.kcsmini2.ojeommo.board.data.dto.response.detail.CategoryResponseDto;
import org.kcsmini2.ojeommo.board.data.dto.response.detail.GatherBoardDetailResponseDTO;
import org.kcsmini2.ojeommo.board.service.GatherBoardService;
import org.kcsmini2.ojeommo.comment.data.dto.request.CommentCreateRequestDTO;
import org.kcsmini2.ojeommo.comment.data.dto.request.CommentUpdateRequestDTO;
import org.kcsmini2.ojeommo.comment.data.dto.response.CommentDetailResponseDTO;
import org.kcsmini2.ojeommo.comment.service.CommentService;
import org.kcsmini2.ojeommo.exception.ApplicationException;
import org.kcsmini2.ojeommo.exception.ErrorCode;
import org.kcsmini2.ojeommo.member.data.dto.MemberDTO;
import org.kcsmini2.ojeommo.member.data.dto.PartyMemberDetailResponseDTO;
import org.kcsmini2.ojeommo.member.service.PartyService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
@RequestMapping("board")
@RequiredArgsConstructor
public class BoardController {

    private final GatherBoardService gatherBoardService;
    private final PartyService partyService;
    private final CommentService commentService;


    @PostMapping("/createGatherBoard")
    public String CreateGatherBoardPOST(@Valid GatherBoardCreateRequestDTO requestDTO,
                                        BindingResult bindingResult,
                                        @AuthenticationPrincipal MemberDTO memberDTO
    ) throws Exception {

        if (bindingResult.hasErrors()) {
            throw new ApplicationException(ErrorCode.NULL_FIELD);
        }

        gatherBoardService.createBoard(requestDTO, memberDTO);
        return "redirect:/";
    }

    @GetMapping("/readCreatePage")
    public String ReadCreatePage(Model model) {
        CategoryResponseDto categories = gatherBoardService.getCategory();
        model.addAttribute("categories", categories);
        return "fragment/gather_create";
    }

    @GetMapping("/readGatherBoard/{id}")
    public String ReadGatherBoardGET(Model model, @PathVariable("id") Long boardId, @AuthenticationPrincipal MemberDTO memberDTO, Pageable pageable) {
        GatherBoardDetailResponseDTO dto = gatherBoardService.readBoard(boardId, memberDTO);
        List<PartyMemberDetailResponseDTO> partyDTO = partyService.readParty(boardId);
        Page<CommentDetailResponseDTO> commentDTO = commentService.readComments(boardId, pageable, memberDTO);


        if (memberDTO != null) {
            model.addAttribute("member", memberDTO);
        }
        if (partyDTO != null) {
            model.addAttribute("party", partyDTO);
        }
        model.addAttribute("gatherDetail", dto);

        if (commentDTO != null) {
            model.addAttribute("comment", commentDTO);
        }

        return "fragment/gather_detail";
    }

    @GetMapping("/toUpdateGatherBoardPage/{id}")
    public String LinkUpdateGatherBoardPagePost(@PathVariable("id") Long boardId, Model model, @AuthenticationPrincipal MemberDTO memberDTO) {
        gatherBoardService.checkPermission(boardId, memberDTO);
        GatherBoardDetailResponseDTO dto = gatherBoardService.readBoard(boardId, memberDTO);
        model.addAttribute("gatherDetail", dto);

        CategoryResponseDto categories = gatherBoardService.getCategory();
        model.addAttribute("categories", categories);
        return "fragment/gather_modify";
    }

    @PostMapping("/updateGatherBoardPage")
    public String UpdateGatherBoardPage(@Valid GatherBoardUpdateRequestDTO gatherBoardUpdateRequestDTO,
                                        BindingResult bindingResult,
                                        @AuthenticationPrincipal MemberDTO memberDTO) {
        if (bindingResult.hasErrors()) {
            throw new ApplicationException(ErrorCode.NULL_FIELD);
        }
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
        return "redirect:/";
    }

    @PostMapping("/joinParty")
    public String JoinParty(JoinPartyRequestDto requestDto, @AuthenticationPrincipal MemberDTO memberDTO) {
        partyService.joinParty(requestDto.getBoardId(), memberDTO);

        return "redirect:/";
    }

    @PostMapping("/quitParty")
    public String QuitParty(QuitPartyRequestDto requestDto, @AuthenticationPrincipal MemberDTO memberDTO) {
        partyService.quitParty(requestDto, memberDTO);

        return "redirect:/";
    }

    // 댓글 등록
    @PostMapping("/createComment")
    public HttpEntity<Boolean> createCommentPOST(@RequestParam("commentContent") String content, @AuthenticationPrincipal MemberDTO memberDTO, Long boardId, Model model){
        CommentCreateRequestDTO requestDTO = new CommentCreateRequestDTO();
        requestDTO.setContent(content);
        requestDTO.setBoardId(boardId);
        commentService.createComment(requestDTO, memberDTO);

        return new HttpEntity<>(true);
//        return "fragment/gather_detail";
//        return "/board/readGatherBoard/" + boardId;
//        return "redirect:/";
    }

    // 댓글 수정
    @PostMapping("/updateComment")
    public String updateCommentPOST(@RequestParam("memberId") String memberId, @RequestParam("commentId") Long commentId) {
        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberId);
        CommentUpdateRequestDTO requestDTO = new CommentUpdateRequestDTO();
        requestDTO.setCommentId(commentId);

        commentService.updateComment(requestDTO, memberDTO);
        return "redirect:/";
    }

    @PostMapping("/deleteComment")
    public String deleteCommentPOST(@RequestParam("memberId") String memberId, @RequestParam("commentId") Long commentId) {

        MemberDTO memberDTO = new MemberDTO();
        memberDTO.setId(memberId);

        commentService.deleteComment(commentId, memberDTO);
        return "redirect:/";
    }

}
