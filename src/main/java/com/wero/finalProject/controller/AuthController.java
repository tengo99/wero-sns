package com.wero.finalProject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wero.finalProject.dto.request.auth.CheckCertificationRequestDto;
import com.wero.finalProject.dto.request.auth.EmailCertificationRequestDto;
import com.wero.finalProject.dto.request.auth.IdCheckRequestDto;
import com.wero.finalProject.dto.request.auth.RefreshTokenRequestDto;
import com.wero.finalProject.dto.request.auth.RegisterRequestDto;
import com.wero.finalProject.dto.request.auth.SignInRequestDto;
import com.wero.finalProject.dto.response.auth.CheckCertificationResponseDto;
import com.wero.finalProject.dto.response.auth.EmailCertificationResponseDto;
import com.wero.finalProject.dto.response.auth.IdCheckResponseDto;
import com.wero.finalProject.dto.response.auth.RegisterResponseDto;
import com.wero.finalProject.dto.response.auth.SignInResponseDto;
import com.wero.finalProject.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * @작성자:오현암
 * @작성날짜:2024/04/25
 * @파일명:AuthController.class
 * @기능:로그인,회원가입,이메일인증,아이디중복체크_API
 **/
@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/id-check")
    public ResponseEntity<? super IdCheckResponseDto> idCheck(@RequestBody @Valid IdCheckRequestDto requestBody) {
        ResponseEntity<? super IdCheckResponseDto> response = authService.idCheck(requestBody);
        return response;
    }

    @PostMapping("/email-certification")
    public ResponseEntity<? super EmailCertificationResponseDto> emailCertification(
            @RequestBody @Valid EmailCertificationRequestDto requestBody) {
        ResponseEntity<? super EmailCertificationResponseDto> response = authService.emailCertification(requestBody);
        return response;
    }

    @PostMapping("/check-certification")
    public ResponseEntity<? super CheckCertificationResponseDto> checkCertification(
            @RequestBody @Valid CheckCertificationRequestDto requestBody) {
        ResponseEntity<? super CheckCertificationResponseDto> response = authService.checkCertification(requestBody);
        return response;
    }

    @PostMapping("/register")
    public ResponseEntity<? super RegisterResponseDto> register(
            @RequestBody @Valid RegisterRequestDto requestBody) {
        ResponseEntity<? super RegisterResponseDto> response = authService.register(requestBody);
        return response;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<? super SignInResponseDto> signIn(
            @RequestBody @Valid SignInRequestDto requestBody) {
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(
            @RequestBody @Valid RefreshTokenRequestDto requestBody) {
        ResponseEntity<?> response = authService.refreshToken(requestBody);
        return response;
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletResponse response) {
        // 액세스 토큰 쿠키 만료
        Cookie accessTokenCookie = new Cookie("accessToken", null);
        accessTokenCookie.setHttpOnly(true);
        accessTokenCookie.setPath("/");
        accessTokenCookie.setMaxAge(0); // 쿠키를 즉시 만료시킴
        response.addCookie(accessTokenCookie);

        // // 리프레시 토큰 쿠키도 만료
        Cookie refreshTokenCookie = new Cookie("refreshToken", null);
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setPath("/");
        refreshTokenCookie.setMaxAge(0); // 쿠키를 즉시 만료시킴
        response.addCookie(refreshTokenCookie);

        // 로그아웃 성공 응답
        return ResponseEntity.ok("Logged out successfully");
    }

}
