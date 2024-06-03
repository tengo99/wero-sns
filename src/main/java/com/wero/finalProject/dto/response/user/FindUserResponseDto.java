package com.wero.finalProject.dto.response.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wero.finalProject.common.ResponseCode;
import com.wero.finalProject.common.ResponseMessage;
import com.wero.finalProject.dto.response.FindOneUserResponseDto;
import com.wero.finalProject.dto.response.ResponseDto;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/17
 * @파일명:FindUserResponseDto
 * @기능: 유저 정보 반환
 **/
public class FindUserResponseDto extends ResponseDto {

    private FindUserResponseDto() {
        super();
    }

    public static ResponseEntity<FindOneUserResponseDto> getUserSuccess(UserResponseDto data) {
        FindOneUserResponseDto responseBody = new FindOneUserResponseDto(data);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<FindOneUserResponseDto> getUserFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.UPDATE_FAIL,
                ResponseMessage.UPDATE_FAIL);
        FindOneUserResponseDto errorResponse = new FindOneUserResponseDto(responseBody.getCode(), responseBody.getMsg(),
                null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
