package com.example.test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/auth")
public class UserServiceController {

    // 로그인 화면
    @GetMapping("/login")
    public String login() {
        return "/login";
    }

    // 회원가입 화면
    @GetMapping("/join")
    public String join() {
        return "/join";
    }

    // 에러
    @GetMapping("/error")
    public String error() {
        return "/error";
    }

}

