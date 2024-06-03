package com.wero.finalProject.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wero.finalProject.dto.response.ListResponseDto;
import com.wero.finalProject.dto.response.feeds.FeedsResponseDto;
import com.wero.finalProject.dto.response.feeds.ListFeedResponseDto;
import com.wero.finalProject.service.MainFeedService;
import com.wero.finalProject.service.SearchService;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/31
 * @파일명:NonUserController
 * @기능:NonUser 컨트롤러
 **/

@RestController
@RequestMapping("/api/v1/nonuser")
public class NonUserController {

    private final MainFeedService mainFeedService;
    private final SearchService searchService;

    public NonUserController(MainFeedService mainFeedService, SearchService searchService) {
        this.mainFeedService = mainFeedService;
        this.searchService = searchService;

    }

    // 비회원 모든 피드 조회
    @GetMapping("/feeds")
    public ResponseEntity<ListResponseDto<FeedsResponseDto>> nonMembergetAllFeeds(
            @RequestParam int page,
            @RequestParam int size) {

        try {
            List<FeedsResponseDto> feeds = mainFeedService.nonMembergetAllFeeds(page, size);
            return ListFeedResponseDto.getFeedsSuccess(feeds);
        } catch (Exception e) {
            return ListFeedResponseDto.getFeesFail();
        }

    }

    // 비회원 일기 내용으로 검색
    @GetMapping("/search/content/{keyword}")
    public ResponseEntity<ListResponseDto<FeedsResponseDto>> nonMemberGetFeedsByContent(
            @PathVariable String keyword,
            @RequestParam int page,
            @RequestParam int size) {

        try {
            List<FeedsResponseDto> dtos = searchService.nonMemberGetFeedsByContent(keyword, page, size);
            return ListFeedResponseDto.getFeedsSuccess(dtos);
        } catch (Exception e) {
            return ListFeedResponseDto.getFeesFail();
        }
    }

    // 비회원 일기 카테고리로 검색
    @GetMapping("/search/category/{keyword}")
    public ResponseEntity<ListResponseDto<FeedsResponseDto>> nonMemberGetFeedsByCategory(
            @PathVariable String keyword,
            @RequestParam int page,
            @RequestParam int size) {

        try {
            List<FeedsResponseDto> dtos = searchService.nonMemberGetFeedsByCategory(keyword, page, size);
            return ListFeedResponseDto.getFeedsSuccess(dtos);
        } catch (Exception e) {
            return ListFeedResponseDto.getFeesFail();
        }
    }

}
