package org.kcsmini2.ojeommo.member.controller;

import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.config.jwt.MemberDetail;
import org.kcsmini2.ojeommo.member.data.dto.SignRequest;
import org.kcsmini2.ojeommo.member.data.dto.SignResponse;
import org.kcsmini2.ojeommo.member.repository.MemberRepository;
import org.kcsmini2.ojeommo.member.service.SignService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * 작성자: 김준연
 *
 * 설명: member 컨트롤러 작성
 *
 * 최종 수정 일자: 2023/07/24
 */
@Controller
@RequiredArgsConstructor
public class MemberController {

    private final MemberRepository memberRepository;
    private final SignService signService;

    @PostMapping("/register")
    public String signup(@ModelAttribute SignRequest request) throws Exception {
        signService.register(request);
        return "login";
    }

    @PostMapping(value = "/login")
    public String login(@ModelAttribute SignRequest request, Model model) throws Exception {
        SignResponse response = signService.login(request);
        System.out.println("provided token is " + response.getToken());
        if (response.getToken() != null) {
            model.addAttribute("data", response);
            return "main";
        }
        return "login";
    }

//    @PostMapping("/update")
//    public String update() {
//
//    }


}
