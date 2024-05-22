package com.wero.finalProject.dto.request.auth;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @작성자:오현암
 * @작성날짜:2024/04/25
 * @파일명:RegisterRequestDto
 * @기능:회원_가입_요청_데이터_전달
 **/

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequestDto {

    @NotBlank
    String id;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$")
    private String password;

    @NotBlank
    private String nickName;

    @NotBlank
    private String gender;

    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String certificationNumber;
}
