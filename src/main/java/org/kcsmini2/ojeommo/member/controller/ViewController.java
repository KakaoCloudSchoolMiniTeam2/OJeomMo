package org.kcsmini2.ojeommo.member.controller;

import lombok.RequiredArgsConstructor;
import org.kcsmini2.ojeommo.category.entity.Category;
import org.kcsmini2.ojeommo.member.data.dto.MemberDTO;
import org.kcsmini2.ojeommo.category.repository.CategoryRepository;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ViewController {

    private final CategoryRepository categoryRepository;

    @GetMapping("register")
    public String register(Model model) {
        List<Category> categories = categoryRepository.findAll();
        model.addAttribute("categories", categories);

        return "joinMember";
    }

    @GetMapping("login")
    public String loginView() {
        return "login";
    }

    @GetMapping("mypage")
    public String myPageView(@AuthenticationPrincipal MemberDTO memberDTO, Model model) {
        model.addAttribute("data", memberDTO);
        return "mypage";
    }

    @GetMapping("update")
    public String updateView(@AuthenticationPrincipal MemberDTO memberDTO, Model model) {

        model.addAttribute("data", memberDTO);
        return "update";
    }

}
