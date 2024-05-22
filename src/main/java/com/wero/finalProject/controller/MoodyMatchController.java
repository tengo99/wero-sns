package com.wero.finalProject.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wero.finalProject.dto.response.ListResponseDto;
import com.wero.finalProject.dto.response.feeds.FeedsResponseDto;
import com.wero.finalProject.dto.response.feeds.ListFeedResponseDto;
import com.wero.finalProject.service.MoodyMatchService;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/12
 * @파일명:MoodyMatchController
 * @기능:MoodyMatch 컨트롤러
 **/

@RestController
@RequestMapping("/api/v1/user/feeds")
public class MoodyMatchController {

    private final MoodyMatchService moodyMatchService;

    public MoodyMatchController(MoodyMatchService moodyMatchService) {
        this.moodyMatchService = moodyMatchService;

    }

    // 유저가 좋아요 하지 않은 피드 조회
    @GetMapping("/{userId}/moody-match")
    public ResponseEntity<ListResponseDto<FeedsResponseDto>> getNotLikeFeeds(@PathVariable String userId) {
        try {
            List<FeedsResponseDto> feeds = moodyMatchService.getNotLikeFeeds(userId);
            return ListFeedResponseDto.getFeedsSuccess(feeds);

        } catch (Exception e) {
            return ListFeedResponseDto.getFeesFail();
        }
    }

}
