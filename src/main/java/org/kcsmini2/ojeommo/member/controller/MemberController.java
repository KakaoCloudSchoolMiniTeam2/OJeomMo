package org.kcsmini2.ojeommo.member.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.member.data.dto.MemberDTO;
import org.kcsmini2.ojeommo.member.data.dto.SignRequest;
import org.kcsmini2.ojeommo.member.data.dto.SignResponse;
import org.kcsmini2.ojeommo.member.service.SignService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;

/**
 * 작성자: 김준연
 *
 * 설명: member 컨트롤러 작성
 *
 * 최종 수정 일자: 2023/07/31
 */
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final SignService signService;

    @PostMapping("/register")
    public String signup(@ModelAttribute SignRequest request,
                         @RequestParam(name = "categoryId", required = false) String[] categoryIds,
                         HttpServletResponse response) throws Exception {
        if(categoryIds == null) categoryIds = new String[0];
        request.setCategoryIds(categoryIds);

        if(signService.register(request)) return "login";

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + "중복된 아이디입니다." + "'); history.go(-1); </script> ");
        out.flush();

        return "register";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute SignRequest request, Model model) throws Exception {
        SignResponse response = signService.login(request);
        System.out.println("provided token is " + response.getToken());
        if (response.getToken() != null) {
            model.addAttribute("data", response);
            return "memberLogin";
        }
        return "login";
    }

    @PostMapping("/update")
    public String update(@AuthenticationPrincipal MemberDTO memberDTO, @ModelAttribute SignRequest request) {
        String id = memberDTO.getId();
        request.setId(id);
        if (signService.update(request)) return "redirect:/main";
        else return "error";
    }

    @PostMapping("/delete")
    public String delete(@AuthenticationPrincipal MemberDTO memberDTO) {

        if(signService.delete(memberDTO.getId())) return "memberDeleted";
        return "error";
    }

}
