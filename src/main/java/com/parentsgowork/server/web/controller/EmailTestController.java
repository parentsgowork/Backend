package com.parentsgowork.server.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class EmailTestController {

    @GetMapping("/email-auth")
    public String emailAuthPage(Model model) {
        model.addAttribute("authenticationCode", "123456"); // 테스트용 인증코드
        return "email-authentication"; // 확장자(.html)는 생략
    }

}
