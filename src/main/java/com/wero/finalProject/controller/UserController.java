package com.wero.finalProject.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wero.finalProject.dto.request.user.UserDeleteRequestDto;
import com.wero.finalProject.dto.request.user.UserPostPictureRequestDto;
import com.wero.finalProject.dto.request.user.UserUpdateEmailRequestDto;
import com.wero.finalProject.dto.request.user.UserUpdateRequestDto;
import com.wero.finalProject.dto.response.user.FindUserResponseDto;
import com.wero.finalProject.dto.response.user.UserDeleteResponseDto;
import com.wero.finalProject.dto.response.user.UserResponseDto;
import com.wero.finalProject.dto.response.user.UserUpdateResponseDto;
import com.wero.finalProject.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * @작성자:오현암
 * @작성날짜:2024/05/03
 * @파일명:UserController.class
 * @기능:유저_수정_삭제API
 *
 **/

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PatchMapping("/update")
    public ResponseEntity<? super UserUpdateResponseDto> updateReport(
            @RequestBody @Valid UserUpdateRequestDto requestBody,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<? super UserUpdateResponseDto> response = userService.userUpdate(requestBody, userId);
        return response;
    }

    @PatchMapping("/update/email")
    public ResponseEntity<? super UserUpdateResponseDto> updateEmail(
            @RequestBody @Valid UserUpdateEmailRequestDto requestBody,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<? super UserUpdateResponseDto> response = userService.userUpdateEmail(requestBody, userId);
        return response;
    }

    @PostMapping(value = "/update/prof")
    public ResponseEntity<? super UserUpdateResponseDto> registerPic(
            @ModelAttribute UserPostPictureRequestDto requestBody,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<? super UserUpdateResponseDto> response = userService.userPicture(requestBody, userId);
        return response;
    }

    @GetMapping(value = "/find/prof")
    public ResponseEntity<List<String>> findPic(@AuthenticationPrincipal String userId) {
        List<String> response = userService.findUserPicture(userId);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping(value = "/delete")
    public ResponseEntity<? super UserDeleteResponseDto> delUser(@RequestBody @Valid UserDeleteRequestDto requestBody,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<? super UserDeleteResponseDto> response = userService.userDelete(requestBody, userId);
        return response;
    }

    @GetMapping("/profile")
    public ResponseEntity<?> getUser(@AuthenticationPrincipal String userId) {
        try {
            UserResponseDto response = userService.findByUserId(userId);

            return FindUserResponseDto.getUserSuccess(response);

        } catch (Exception e) {
            return FindUserResponseDto.getUserFail();
        }
    }

    // @PatchMapping(value ="/update/prof")
    // public ResponseEntity<?super UserUpdateResponseDto> updatePic
    // (@ModelAttribute UserPostPictureRequestDto requestBody)
}
