package com.wero.finalProject.dto.request.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/29
 * @파일명:RefreshTokenRequestDto
 * @기능:리프레시토큰_데이터_전달
 **/

@Getter
@Setter
@NoArgsConstructor
public class RefreshTokenRequestDto {

    private String refreshToken;

}
