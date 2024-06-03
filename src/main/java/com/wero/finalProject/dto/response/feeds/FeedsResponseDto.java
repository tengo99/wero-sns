package com.wero.finalProject.dto.response.feeds;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/08
 * @파일명:FeedsResponseDto
 * @기능:피드 조회 응답 데이터 전달
 **/

@Data
@AllArgsConstructor
public class FeedsResponseDto {

    private Integer mainfeed_id;
    private String content;
    private String trackName;
    private String image;
    private String create_date;
    private String modificate_date;
    private String category;
    private boolean isLiked;
    private String user_id;

}
