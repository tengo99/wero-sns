package com.wero.finalProject.dto.response.user;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/17
 * @파일명:UserResponseDto
 * @기능: 유저 정보 반환
 **/

@Data
@AllArgsConstructor
public class UserResponseDto {

    private String userId;
    private String email;
    private String nickName;
    private String type;
    private String gender;
    private String password;

}
