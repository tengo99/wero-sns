package com.wero.finalProject.dto.request.search;

import lombok.Getter;
import lombok.Setter;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/07
 * @파일명:SearchDiaryRequestDto
 **/
@Getter
@Setter
public class SearchRequestDto {

    private Integer mainfeed_id;
    private String content;
    private String category;
    private String nickName;
    private String image;

    public SearchRequestDto(Integer mainfeed_id, String content, String category, String nickName, String image) {
        this.mainfeed_id = mainfeed_id;
        this.content = content;
        this.category = category;
        this.nickName = nickName;
        this.image = image;
    }

}
