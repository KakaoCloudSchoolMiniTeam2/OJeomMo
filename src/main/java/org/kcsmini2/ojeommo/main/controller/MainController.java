package org.kcsmini2.ojeommo.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping()
    public String getIndex(){
        return "redirect:/main";
    }

    @GetMapping("/main")
    public String getMain(){
        return "main";
    }
}
