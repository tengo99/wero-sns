package com.wero.finalProject.service;

import java.util.List;

import com.wero.finalProject.dto.response.feeds.FeedsResponseDto;

public interface SearchService {

    List<FeedsResponseDto> getFeedsByContent(String userId, String keyword, int page, int size);

    List<FeedsResponseDto> nonMemberGetFeedsByContent(String keyword, int page, int size);

    List<FeedsResponseDto> getFeedsByCategory(String userId, String keyword, int page, int size);

    List<FeedsResponseDto> nonMemberGetFeedsByCategory(String keyword, int page, int size);

}
