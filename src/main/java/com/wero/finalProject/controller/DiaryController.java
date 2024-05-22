package com.wero.finalProject.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wero.finalProject.dto.request.diary.DiaryRequestDto;
import com.wero.finalProject.dto.request.diary.PatchDiaryRequestDto;
import com.wero.finalProject.dto.response.diary.DeleteDiaryResponseDto;
import com.wero.finalProject.dto.response.diary.DiaryResponseDto;
import com.wero.finalProject.dto.response.diary.GetDiaryListResponseDto;
import com.wero.finalProject.dto.response.diary.GetDiaryResponseDto;
import com.wero.finalProject.dto.response.diary.PatchDiaryResponseDto;
import com.wero.finalProject.dto.response.diary.PutBookMarkResponseDto;
import com.wero.finalProject.service.DiaryService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * @작성자:최기원
 * @작성날짜:2024/05/05
 * @파일명:DiaryController.class
 * @기능:일기 작성, 조회(아이디로), 수정, 삭제
 **/

@RestController
@RequestMapping("api/v1/diary")
@RequiredArgsConstructor
public class DiaryController {

    private final DiaryService diaryService;// final 키워드 붙여서 diaryService를 생성자로 주입받기

    @PostMapping("") // post방식==>생성
    public ResponseEntity<? super DiaryResponseDto> post(@RequestBody @Valid DiaryRequestDto requestBody, // DiaryRequestDto
                                                                                                          // 받기
            @AuthenticationPrincipal String userId) {
        ResponseEntity<? super DiaryResponseDto> response = diaryService.createDiary(requestBody, userId);// 컨트롤러단에서
                                                                                                          // 서비스의 메소드를
                                                                                                          // 호출
        return response;
    }

    // @GetMapping("/selectAll")
    // public ResponseEntity<? super GetPostResponseDto> selectAll
    // (@AuthenticationPrincipal String userId) {
    // ResponseEntity<? super GetPostResponseDto> response=postService.getPostAll();
    // return response;
    // }

    @GetMapping("/{diaryId}") // get방식==>조회(아이디로)
    public ResponseEntity<? super GetDiaryResponseDto> getPost(@PathVariable Integer diaryId, // url경로에 있는 diaryId 받기
            @AuthenticationPrincipal String userId) {
        ResponseEntity<? super GetDiaryResponseDto> response = diaryService.getDiary(diaryId);
        return response;
    }

    @DeleteMapping("/{diaryId}") // delete방식==>삭제
    public ResponseEntity<? super DeleteDiaryResponseDto> deleteDiary(
            @PathVariable("diaryId") Integer diaryId, // url경로에 있는 diaryId 받기
            @AuthenticationPrincipal String userId) {
        ResponseEntity<? super DeleteDiaryResponseDto> response = diaryService.deleteDiary(diaryId, userId);
        return response;
    }

    @PatchMapping("/{diaryId}") // patch방식==>수정
    public ResponseEntity<? super PatchDiaryResponseDto> patchDiary(
            @RequestBody @Valid PatchDiaryRequestDto requestBody, // PatchDiaryRequestDto 받기
            @PathVariable("diaryId") Integer diaryId, // url경로에 있는 diaryId 받기
            @AuthenticationPrincipal String userId) {
        ResponseEntity<? super PatchDiaryResponseDto> response = diaryService.patchDiary(requestBody, diaryId, userId);
        return response;
    }

    @GetMapping("/diaryList") // get방식==>전체 조회
    public ResponseEntity<? super GetDiaryListResponseDto> getDiaryList(
            @AuthenticationPrincipal String userId) {
        ResponseEntity<? super GetDiaryListResponseDto> response = diaryService.getDiaryList();
        return response;
    }

    @PutMapping("/{diaryId}/bookMark")
    public ResponseEntity<? super PutBookMarkResponseDto> putBookMark(
            @PathVariable("diaryId") Integer diaryId,
            @AuthenticationPrincipal String userId) {
        ResponseEntity<? super PutBookMarkResponseDto> response = diaryService.putBookMark(diaryId, userId);
        return response;
    }

}
