package com.example.test.service;

import com.example.test.dto.PrincipalDetail;
import com.example.test.model.User;
import com.example.test.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PrincipalDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    // LoginForm에서 action="/loginProc" 되면
    // 스프링 필터 체인이 낚아채서 loadUserByUsername함수를 호출한다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User principal = userRepository.findByUsername(username)
                .orElseThrow(()->{
                    return new UsernameNotFoundException("해당 사용자를 찾을 수 없습니다. : "+username);
                });

        return new PrincipalDetail(principal); //세션에 유저 정보가 저장된다.
    }
}