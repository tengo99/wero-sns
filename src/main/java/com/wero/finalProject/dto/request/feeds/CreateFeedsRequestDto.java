package com.wero.finalProject.dto.request.feeds;

import com.wero.finalProject.domain.MainFeedEntity;

import lombok.Data;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/07
 * @파일명: CreateFeedsRequestDto
 * @기능: 피드 생성 요청 데이터 전달
 **/

@Data
public class CreateFeedsRequestDto {

    private String content;
    // private String trackName;
    private String category;
    private String image;

    public MainFeedEntity toEntity() {
        return MainFeedEntity.builder()
                .content(this.content)
                // .trackName(this.trackName)
                .category(this.category)
                .image(this.image)
                .build();
    }

}
