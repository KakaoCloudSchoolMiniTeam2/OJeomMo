package org.kcsmini2.ojeommo.member.controller;

import org.kcsmini2.ojeommo.config.jwt.MemberDetail;
import org.kcsmini2.ojeommo.member.data.dto.MemberDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    @GetMapping("/register")
    public String register() {
        return "joinMember";
    }

    @GetMapping("/login")
    public String loginView() {
        return "login";
    }

    @GetMapping("/main")
    public String mainView() { return "main"; }

    @GetMapping("/mypage")
    public String myPageView(@AuthenticationPrincipal MemberDetail memberDetail, Model model) {
        model.addAttribute("data", memberDetail.getMember());
        return "mypage";
    }

    @GetMapping("/update")
    public String updateView(@AuthenticationPrincipal MemberDetail memberDetail, Model model) {

        model.addAttribute("data", memberDetail.getMember());
        return "update";
    }

}
