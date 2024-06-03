package com.wero.finalProject.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wero.finalProject.dto.response.ListResponseDto;
import com.wero.finalProject.dto.response.feeds.FeedsResponseDto;
import com.wero.finalProject.dto.response.feeds.ListFeedResponseDto;
import com.wero.finalProject.service.SearchService;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/07
 * @파일명:SearchController
 **/

@RestController
@RequestMapping("/api/v1/user/search")
public class SearchController {

        @Autowired
        private SearchService searchService;

        // 일기 내용으로 검색
        @GetMapping("/content/{keyword}")
        public ResponseEntity<ListResponseDto<FeedsResponseDto>> getFeedsByContent(
                        @AuthenticationPrincipal String userId, @PathVariable String keyword,
                        @RequestParam int page,
                        @RequestParam int size) {

                try {
                        List<FeedsResponseDto> dtos = searchService.getFeedsByContent(userId, keyword, page, size);
                        return ListFeedResponseDto.getFeedsSuccess(dtos);
                } catch (Exception e) {
                        return ListFeedResponseDto.getFeesFail();
                }
        }

        // 일기 카테고리로 검색
        @GetMapping("/category/{keyword}")
        public ResponseEntity<ListResponseDto<FeedsResponseDto>> getFeedsByCategory(
                        @AuthenticationPrincipal String userId, @PathVariable String keyword,
                        @RequestParam int page,
                        @RequestParam int size) {

                try {
                        List<FeedsResponseDto> dtos = searchService.getFeedsByCategory(userId, keyword, page, size);
                        return ListFeedResponseDto.getFeedsSuccess(dtos);
                } catch (Exception e) {
                        return ListFeedResponseDto.getFeesFail();
                }
        }

}
