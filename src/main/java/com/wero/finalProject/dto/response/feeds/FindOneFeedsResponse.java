package com.wero.finalProject.dto.response.feeds;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wero.finalProject.common.ResponseCode;
import com.wero.finalProject.common.ResponseMessage;
import com.wero.finalProject.dto.response.FindOneResponseDto;
import com.wero.finalProject.dto.response.ResponseDto;

import lombok.Getter;

@Getter
public class FindOneFeedsResponse extends ResponseDto {

    private FindOneFeedsResponse() {
        super();
    }

    public static ResponseEntity<FindOneResponseDto> getFeedsSuccess(FeedsResponseDto data) {
        FindOneResponseDto responseBody = new FindOneResponseDto(data);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

    public static ResponseEntity<FindOneResponseDto> getFeesFail() {
        ResponseDto responseBody = new ResponseDto(ResponseCode.UPDATE_FAIL,
                ResponseMessage.UPDATE_FAIL);
        FindOneResponseDto errorResponse = new FindOneResponseDto(responseBody.getCode(), responseBody.getMsg(),
                null);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorResponse);
    }

}
