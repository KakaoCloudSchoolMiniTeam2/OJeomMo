package org.kcsmini2.ojeommo.member.controller;

import org.kcsmini2.ojeommo.member.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 작성자: 김준연
 *
 * 설명: member 컨트롤러 작성
 *
 * 최종 수정 일자: 2023/07/22
 */
@Controller
public class MemberController {

    @Autowired
    MemberService memberService;

    @RequestMapping("/member/join")
    public String join() {

    }

}
