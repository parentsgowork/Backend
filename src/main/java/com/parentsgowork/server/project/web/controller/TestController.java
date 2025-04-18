package com.parentsgowork.server.project.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.stereotype.Controller;

@Controller
public class TestController {

    @GetMapping("/")
    @ResponseBody
    public String hello() {
        return "hello";
    }
}
