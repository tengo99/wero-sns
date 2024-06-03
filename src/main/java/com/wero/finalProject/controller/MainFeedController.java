package com.wero.finalProject.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wero.finalProject.dto.request.feeds.CreateFeedsRequestDto;
import com.wero.finalProject.dto.request.feeds.UpdateFeedsRequestDto;
import com.wero.finalProject.dto.response.FindOneResponseDto;
import com.wero.finalProject.dto.response.ListResponseDto;
import com.wero.finalProject.dto.response.ResponseDto;
import com.wero.finalProject.dto.response.feeds.CreateFeedsResponseDto;
import com.wero.finalProject.dto.response.feeds.DeleteFeedsResponseDto;
import com.wero.finalProject.dto.response.feeds.FeedsResponseDto;
import com.wero.finalProject.dto.response.feeds.FindOneFeedsResponse;
import com.wero.finalProject.dto.response.feeds.LikeResponseDto;
import com.wero.finalProject.dto.response.feeds.ListFeedResponseDto;
import com.wero.finalProject.dto.response.feeds.ReportResponseDto;
import com.wero.finalProject.dto.response.feeds.UpdateFeedsResponseDto;
import com.wero.finalProject.service.MainFeedService;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/07
 * @파일명:MainFeedController
 * @기능:MainFeed 컨트롤러
 **/

@RestController
@RequestMapping("/api/v1/user/feeds")
public class MainFeedController {

    private final MainFeedService mainFeedService;

    public MainFeedController(MainFeedService mainFeedService) {
        this.mainFeedService = mainFeedService;

    }

    // 모든 피드 조회
    @GetMapping("")
    public ResponseEntity<ListResponseDto<FeedsResponseDto>> getAllFeeds(@AuthenticationPrincipal String userId,
            @RequestParam int page,
            @RequestParam int size) {

        try {
            List<FeedsResponseDto> feeds = mainFeedService.getAllFeeds(userId, page, size);
            return ListFeedResponseDto.getFeedsSuccess(feeds);
        } catch (Exception e) {
            return ListFeedResponseDto.getFeesFail();
        }

    }

    // 피드 하나 조회
    @GetMapping("/{id}")
    public ResponseEntity<FindOneResponseDto> getOneFeeds(@AuthenticationPrincipal String userId,
            @PathVariable Integer id) {

        try {
            FeedsResponseDto feeds = mainFeedService.getOneFeeds(userId, id);
            return FindOneFeedsResponse.getFeedsSuccess(feeds);
        } catch (Exception e) {
            return FindOneFeedsResponse.getFeesFail();
        }

    }

    // 유저 피드 userId로 찾기
    @GetMapping("/history")
    public ResponseEntity<ListResponseDto<FeedsResponseDto>> getFeedByUserId(@AuthenticationPrincipal String userId,
            @RequestParam int page,
            @RequestParam int size) {

        try {
            List<FeedsResponseDto> feeds = mainFeedService.getFeedByUserId(userId, page, size);
            return ListFeedResponseDto.getFeedsSuccess(feeds);

        } catch (Exception e) {
            return ListFeedResponseDto.getFeesFail();
        }
    }

    // 유저가 좋아요한 피드 목록 조회
    @GetMapping("/likes")
    public ResponseEntity<ListResponseDto<FeedsResponseDto>> getFeedByUserIdAndIsLiked(
            @AuthenticationPrincipal String userId) {
        try {
            List<FeedsResponseDto> feeds = mainFeedService.getFeedByUserIdAndIsLiked(userId);
            return ListFeedResponseDto.getFeedsSuccess(feeds);

        } catch (Exception e) {
            return ListFeedResponseDto.getFeesFail();
        }

    }

    // 메인 피드 생성
    @PostMapping("/feed")
    public ResponseEntity<?> createFeed(@RequestBody CreateFeedsRequestDto requestDto,
            @AuthenticationPrincipal String userId) {
        try {
            mainFeedService.createFeed(userId, requestDto.toEntity());
            return CreateFeedsResponseDto.created();
        } catch (IllegalArgumentException e) {
            return CreateFeedsResponseDto.createFail();
        } catch (Exception e) {
            return ResponseDto.dataBaseError();
        }
    }

    // 메인 피드 여러개 생성
    @PostMapping("/feeds")
    public ResponseEntity<?> createFeeds(@RequestBody CreateFeedsRequestDto requestDto,
            @AuthenticationPrincipal String userId) {
        try {
            mainFeedService.createFeeds(userId, requestDto.toEntities());
            return CreateFeedsResponseDto.created();
        } catch (IllegalArgumentException e) {
            return CreateFeedsResponseDto.createFail();
        } catch (Exception e) {
            return ResponseDto.dataBaseError();
        }
    }

    // 메인 피드 수정
    @PutMapping("/{id}/feed")
    public ResponseEntity<?> updateFeed(@RequestBody UpdateFeedsRequestDto requestDto,
            @AuthenticationPrincipal String userId,
            @PathVariable Integer id) {
        try {
            mainFeedService.updateFeed(id, requestDto.toEntity(), userId);
            return UpdateFeedsResponseDto.update();
        } catch (IllegalArgumentException e) {
            return UpdateFeedsResponseDto.updateFail();
        } catch (Exception e) {
            return ResponseDto.dataBaseError();
        }
    }

    // 메인 피드 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeed(@AuthenticationPrincipal String userId, @PathVariable Integer id) {
        try {
            mainFeedService.deleteFeed(id);
            return DeleteFeedsResponseDto.delete();
        } catch (IllegalArgumentException e) {
            return DeleteFeedsResponseDto.deleteFail();
        } catch (Exception e) {
            return ResponseDto.dataBaseError();
        }
    }

    // 메인 피드 좋아요 추가
    @PostMapping("/{id}/like")
    public ResponseEntity<?> addLikeFeed(@AuthenticationPrincipal String userId, @PathVariable Integer id) {
        try {
            mainFeedService.addLikeFeed(userId, id);
            return LikeResponseDto.addLike();
        } catch (Exception e) {
            return LikeResponseDto.addLikeFail();
        }
    }

    // 메인 피드 좋아요 삭제
    @DeleteMapping("/{id}/like")
    public ResponseEntity<?> deleteLikeFeed(@AuthenticationPrincipal String userId, @PathVariable Integer id) {
        try {
            mainFeedService.deleteLikeFeed(userId, id);
            return LikeResponseDto.deleteLike();
        } catch (Exception e) {
            return LikeResponseDto.deleteLikeFail();
        }
    }

    // 메인 피드 신고 추가
    @PostMapping("/{id}/report")
    public ResponseEntity<?> addReportFeed(@AuthenticationPrincipal String userId, @PathVariable Integer id) {
        try {
            mainFeedService.addReportFeed(userId, id);
            return ReportResponseDto.addReport();
        } catch (Exception e) {
            return ReportResponseDto.addReportFail();
        }
    }

}
