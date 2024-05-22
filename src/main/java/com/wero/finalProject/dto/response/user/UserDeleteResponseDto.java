package com.wero.finalProject.dto.response.user;

import com.wero.finalProject.common.ResponseCode;
import com.wero.finalProject.common.ResponseMessage;
import com.wero.finalProject.dto.response.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @작성자:오현암
 * @작성날짜:2024/05/13
 * @파일명:UserDeleteResponseDto.class
 * @기능:유저삭제요청응답
 **/
public class UserDeleteResponseDto extends ResponseDto {

    public static ResponseEntity<UserDeleteResponseDto> success(){
        UserDeleteResponseDto responseBody = new UserDeleteResponseDto();
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notAuthorized(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.DELETE_FAIL, ResponseMessage.DELETE_FAIL);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> notExistUser(){
        ResponseDto responseBody = new ResponseDto(ResponseCode.NOT_EXIST_USER, ResponseCode.NOT_EXIST_USER);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseBody);
    }

}
