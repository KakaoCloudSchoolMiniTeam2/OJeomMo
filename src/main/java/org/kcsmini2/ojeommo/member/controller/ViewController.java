package org.kcsmini2.ojeommo.member.controller;

import org.springframework.stereotype.Controller;
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
