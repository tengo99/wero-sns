package com.wero.finalProject.service;

import java.util.List;

import com.wero.finalProject.domain.LikeEntity;
import com.wero.finalProject.domain.MainFeedEntity;
import com.wero.finalProject.dto.response.feeds.FeedsResponseDto;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/07
 * @파일명:MainFeedService
 * @기능:MainFeed 서비스
 **/

public interface MainFeedService {

    List<FeedsResponseDto> getAllFeeds(String userId);

    FeedsResponseDto getOneFeeds(String userId, Integer id);

    List<FeedsResponseDto> getFeedByUserId(String userId);

    List<FeedsResponseDto> getFeedByUserIdAndIsLiked(String userId);

    MainFeedEntity createFeed(String userId, MainFeedEntity mainFeed);

    MainFeedEntity updateFeed(Integer id, MainFeedEntity mainFeed, String userId);

    void deleteFeed(Integer id);

    LikeEntity addLikeFeed(String userId, Integer Id);

    void deleteLikeFeed(String userId, Integer id);

}
