package com.wero.finalProject.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wero.finalProject.common.ResponseCode;
import com.wero.finalProject.common.ResponseMessage;
import com.wero.finalProject.dto.response.ResponseDto;

import lombok.Getter;

/**
 * @작성자:오현암
 * @작성날짜:2024/04/25
 * @파일명:SignInResponseDto
 * @기능:로그인_응답_데이터_전달
 **/
@Getter
public class SignInResponseDto extends ResponseDto {

    private String userId;
    private String token;
    private int expirationTime;

    private SignInResponseDto(String token, String userId) {
        super();
        this.userId = userId;
        this.token = token;
        this.expirationTime = 3600;
    }

    public static ResponseEntity<SignInResponseDto> success(String token, String userId) {
        SignInResponseDto responseBody = new SignInResponseDto(token, userId);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> signInFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.SIGN_IN_FAIL, ResponseMessage.SIGN_IN_FAIL);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> restrictedUser(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.RESTRICTED_USER, ResponseMessage.RESTRICTED_USER);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseBody);
    }
}