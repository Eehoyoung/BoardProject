package com.example.test.controller;

import com.example.test.dto.ResponseDto;
import com.example.test.model.RoleType;
import com.example.test.model.User;
import com.example.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
public class UserApiController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession httpSession;

    @PostMapping("/api/user")
    public ResponseDto<Integer> save(@RequestBody User user) {
        System.out.println("UserApiController 호출!");
        user.setRole(RoleType.USER);
        int result = userService.saveUser(user);
        return new ResponseDto<>(HttpStatus.OK.value(), result);
    }

    @PostMapping("/api/user/login")
    public ResponseDto<Integer> login(@RequestBody User user) {
        System.out.println("login 호출");

        User principal = userService.login(user);
        //principal - 접근 주체
        if(principal != null) {
            httpSession.setAttribute("principle", principal);
            System.out.println("세션 정보가 저장되었습니다.");
        }
        return new ResponseDto<>(HttpStatus.OK.value(), 1);
    }

    @PostMapping("/auth/joinProc")
    public ResponseDto<Integer> saveUser(User user) {
        int result = userService.saveUser(user);
        return new ResponseDto<>(HttpStatus.OK.value(), result);
    }
}