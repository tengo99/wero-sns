package com.wero.finalProject.dto.request.diary;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @작성자:최기원
 * @작성날짜:2024/05/04
 * @파일명:PatchDiaryRequestDto.class
 * @기능:일기 수정 request dto
 **/

@Getter
@Setter
@NoArgsConstructor
public class PatchDiaryRequestDto {
    @NotBlank
    private String diaryContent;
    @NotBlank
    private String emotion;
    @NotBlank
    private String song;

    private String image;
}
