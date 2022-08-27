package com.example.test.controller;

import com.example.test.model.RoleType;
import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.List;

@org.springframework.web.bind.annotation.RestController
@RequestMapping("/test")
public class UserController {


    @Autowired
    private UserRepository userRepository;


    @ApiOperation(value = "회원가입")
    @PostMapping("/join")
    public String join(@RequestBody User user){
        user.setRole(RoleType.USER);
        userRepository.save(user);
        return user.getUsername()+"님! 회원가입을 환영합니다";
    }


    @ApiOperation(value = "유저 조회")
    @GetMapping("/user/{id}")
    public User UserDetail(@PathVariable int id){

        return userRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("id가 "+id+"인 사용자는 존재하지 않습니다."));
    }


    @GetMapping("/user/list")
    public List<User> userList() {
        return userRepository.findAll();
    }

    @GetMapping("/user/page")
    public Page<User> pageList(@PageableDefault(size = 2, sort = "id",
            direction = Sort.Direction.ASC) Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @ApiOperation(value = "유저 업데이트")
    @Transactional
    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable int id, @RequestBody User reqUser) {

        User user = userRepository.findById(id).orElseThrow(() -> { // 영속성 컨텍스트에 있는 객체 들고오는 것
            return new IllegalArgumentException("id가 "+id+"인 사용자는 존재하지 않습니다.");
        });

        user.setPassword(reqUser.getPassword());
        user.setEmail(reqUser.getEmail());

        return user;
    }

    @ApiOperation(value = "유저 삭제")
    @DeleteMapping("/user/{id}")
    public String delete(@PathVariable int id) {

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            return id + "에 해당하는 유저는 존재하지 않습니다.";
        }
        return id+"님의 탈퇴가 완료되었습니다.";
    }



}
