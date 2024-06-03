package com.wero.finalProject.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.wero.finalProject.domain.UserEntity;
import com.wero.finalProject.dto.request.user.UserDeleteRequestDto;
import com.wero.finalProject.dto.request.user.UserPostPictureRequestDto;
import com.wero.finalProject.dto.request.user.UserUpdateEmailRequestDto;
import com.wero.finalProject.dto.request.user.UserUpdateRequestDto;
import com.wero.finalProject.dto.response.user.UserDeleteResponseDto;
import com.wero.finalProject.dto.response.user.UserResponseDto;
import com.wero.finalProject.dto.response.user.UserUpdateResponseDto;

/**
 * @작성자:오현암
 * @작성날짜:2024/05/03
 * @파일명:UserService.Interface
 * @기능:유저_수정_서비스_로직_생성
 **/
public interface UserService {
    ResponseEntity<? super UserUpdateResponseDto> userUpdate(UserUpdateRequestDto dto, String userId);

    ResponseEntity<? super UserUpdateResponseDto> userUpdateEmail(UserUpdateEmailRequestDto dto, String userId);

    ResponseEntity<? super UserUpdateResponseDto> userPicture(UserPostPictureRequestDto dto, String userId);

    ResponseEntity<? super UserDeleteResponseDto> userDelete(UserDeleteRequestDto dto, String userId);

    UserEntity findUserById(String userId);

    UserResponseDto findByUserId(String userId);

    List<String> findUserPicture(String userId);

}
