package com.wero.finalProject.dto.request.user;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @작성자:오현암
 * @작성날짜:2024/05/03
 * @파일명:UserUpdateRequestDto
 **/
@Getter
@Setter
@NoArgsConstructor
public class UserUpdateRequestDto {

    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@$!%*?&])[A-Za-z0-9@$!%*?&]{8,}$")
    private String password;


    @NotBlank
    private String nickName;

    @NotBlank
    private String gender;


}
