package com.wero.finalProject.dto.request.diary;

import org.springframework.stereotype.Service;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @작성자:최기원
 * @작성날짜:2024/05/04
 * @파일명:DiaryRequstDto.class
 * @기능:일기 request dto
 **/

@Getter
@Service
@NoArgsConstructor
public class DiaryRequestDto {
    @NotBlank
    private String diaryContent;

    @NotBlank
    private String emotion;

    @NotBlank
    private String song;

    private String image;

    // @NotNull
    // private List<String> postImageList;
}
