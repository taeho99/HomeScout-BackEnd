package com.ssafy.homescout.user.controller;

import com.ssafy.homescout.user.dto.*;
import com.ssafy.homescout.user.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
public class UserController {

    private final UserService userService;

    @PostMapping
    public ResponseEntity<?> signUp(@Valid @RequestBody SignupRequestDto signupRequestDto) {
        userService.signUp(signupRequestDto);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDto loginRequestDto, HttpSession session) {
        userService.login(loginRequestDto, session);
        return ResponseEntity.ok("로그인에 성공했습니다.");
    }

    @GetMapping
    public ResponseEntity<?> getUserInfo() {
        return ResponseEntity.ok(userService.getUserInfo());
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request) {

        //기존 세션 가져옴. 없으면 null
        HttpSession session = request.getSession(false);

        if(session != null && session.getAttribute("loginUser") != null) {
            session.invalidate();
            System.out.println("로그아웃 : session 삭제");
            return ResponseEntity.ok("로그아웃에 성공했습니다.");
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("이미 로그아웃 상태입니다.");
        }
    }

    // 마이페이지
    @GetMapping("/mypage")
    public ResponseEntity<?> getMyPage() {
        return ResponseEntity.ok(userService.getMyPage());
    }

    // 회원정보 수정
    @PutMapping
    public ResponseEntity<?> editUser(@Valid @RequestBody EditUserRequestDto editUserRequestDto) {
        userService.editUser(editUserRequestDto);
        return ResponseEntity.ok().build();
    }

    // 비밀번호 찾기 (임시 비밀번호 전송)
    @PutMapping("/password")
    public ResponseEntity<?> findPassword(@Valid @RequestBody FindPasswordRequestDto findPasswordRequestDto) {
        userService.findPassword(findPasswordRequestDto);
        return ResponseEntity.ok().build();
    }

}
