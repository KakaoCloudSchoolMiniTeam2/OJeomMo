package org.kcsmini2.ojeommo.member.controller;

import org.kcsmini2.ojeommo.member.dto.SignRequest;
import org.kcsmini2.ojeommo.member.dto.SignResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

//    @PostMapping(value = "/login")
//    public String signin(SignRequest request, Model model) throws Exception {
//        model.signService.login(request);
//        return "main";
//    }




//    @RequestMapping("/joinmember.do")
//    public String joinMemberView() {
//        return "/joinMember.html";
//    }
//

//
//    @RequestMapping("/mypage.do")
//    public String myPageView(@AuthenticationPrincipal MemberDetail memberDetail, Model model) {
//        model.addAttribute("data", memberDetail.getMember());
//        return "/mypage.html";
//    }
//

//
//    @PostMapping(value = "/member/join")
//    public String join(@RequestBody SignRequest request,
//                       Model model ) {
//
//        try {
//            SignResponse response = signService.createMember(request);
//            model.addAttribute("data", response);
//            return "/";
//
//        }catch(Exception e) {
//            return "/error.html";
//        }
//    }
}
