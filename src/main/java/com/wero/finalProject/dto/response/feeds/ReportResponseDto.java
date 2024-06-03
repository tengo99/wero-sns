package com.wero.finalProject.dto.response.feeds;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wero.finalProject.common.ResponseCode;
import com.wero.finalProject.common.ResponseMessage;
import com.wero.finalProject.dto.response.ResponseDto;

import lombok.Getter;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/13
 * @파일명: LikeResponse
 * @기능: 피드 라이크 추가/삭제 응답 데이터 전달
 **/

@Getter
public class ReportResponseDto extends ResponseDto {

    private ReportResponseDto() {
        super();
    }

    public static ResponseEntity<ResponseDto> addReport() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.CREATED, ResponseMessage.CREATED);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> alreadyReport() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.ALREADY_REPORTED, ResponseMessage.ALREADY_REPORTED);
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseBody);
    }

    public static ResponseEntity<ResponseDto> addReportFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.CREATE_FAIL, ResponseMessage.CREATE_FAIL);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseBody);
    }

}
