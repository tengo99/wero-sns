package com.wero.finalProject.service;

import org.springframework.http.ResponseEntity;

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

/**
 * @작성자:오현암
 * @작성날짜:2024/04/25
 * @파일명:AuthService.interface
 * @기능:인증처리
 **/
public interface AuthService {
    ResponseEntity<? super IdCheckResponseDto> idCheck(IdCheckRequestDto dto);

    ResponseEntity<? super EmailCertificationResponseDto> emailCertification(EmailCertificationRequestDto dto);

    ResponseEntity<? super CheckCertificationResponseDto> checkCertification(CheckCertificationRequestDto dto);

    ResponseEntity<? super RegisterResponseDto> register(RegisterRequestDto dto);

    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);

    ResponseEntity<?> refreshToken(RefreshTokenRequestDto refreshTokenRequest);

}
