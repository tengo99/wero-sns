package com.wero.finalProject.dto.response.auth;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wero.finalProject.common.ResponseCode;
import com.wero.finalProject.common.ResponseMessage;
import com.wero.finalProject.dto.response.ResponseDto;

import lombok.Getter;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/29
 * @파일명:RefreshTokenResponseDto
 * @기능:리프레시토큰_응답_데이터_전달
 **/

@Getter
public class RefreshTokenResponseDto extends ResponseDto {

    private String token;

    private RefreshTokenResponseDto(String token) {
        this.token = token;

    }

    public static ResponseEntity<RefreshTokenResponseDto> success(String token) {
        RefreshTokenResponseDto responseBody = new RefreshTokenResponseDto(token);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> fail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.VALIDATION_FAIL, ResponseMessage.VALIDATION_FAIL);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
