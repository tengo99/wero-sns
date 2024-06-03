package com.wero.finalProject.service.implement;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.wero.finalProject.Repository.LikeRepository;
import com.wero.finalProject.Repository.SearchRepository;
import com.wero.finalProject.domain.LikeEntity;
import com.wero.finalProject.domain.MainFeedEntity;
import com.wero.finalProject.dto.response.feeds.FeedsResponseDto;
import com.wero.finalProject.service.SearchService;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/27
 * @파일명:SearchServiceImpl
 * @기능:Search 서비스 상세
 **/

@Service("SearchServiceImpl")
public class SearchServiceImpl implements SearchService {

        @Autowired
        private SearchRepository searchRepository;

        @Autowired
        private LikeRepository likeRepository;

        // 내용으로 검색
        @Override
        public List<FeedsResponseDto> getFeedsByContent(String userId, String keyword, int page, int size) {

                Page<MainFeedEntity> resultPage = searchRepository.findByContentContaining(keyword,
                                PageRequest.of(page, size));

                List<MainFeedEntity> results = resultPage.getContent();

                List<FeedsResponseDto> responseDtos = results.stream()
                                .map(feed -> {
                                        Optional<LikeEntity> like = likeRepository
                                                        .findIsLikedByUserId_UserIdAndMainfeedId_MainfeedId(userId,
                                                                        feed.getMainfeedId());
                                        boolean isLiked = like.map(LikeEntity::isLiked).orElse(false);
                                        return new FeedsResponseDto(
                                                        feed.getMainfeedId(),
                                                        feed.getContent(),
                                                        feed.getTrackName(),
                                                        feed.getImage(),
                                                        feed.getCreateDate(),
                                                        feed.getModificateDate(),
                                                        feed.getCategory(),
                                                        isLiked,
                                                        feed.getWriter().getUserId());
                                })
                                .collect(Collectors.toList());
                return responseDtos;
        }

        // 비회원 내용으로 검색
        @Override
        public List<FeedsResponseDto> nonMemberGetFeedsByContent(String keyword, int page, int size) {

                Page<MainFeedEntity> resultPage = searchRepository.findByContentContaining(keyword,
                                PageRequest.of(page, size));

                List<MainFeedEntity> results = resultPage.getContent();

                List<FeedsResponseDto> responseDtos = results.stream()
                                .map(feed -> {

                                        return new FeedsResponseDto(
                                                        feed.getMainfeedId(),
                                                        feed.getContent(),
                                                        feed.getTrackName(),
                                                        feed.getImage(),
                                                        feed.getCreateDate(),
                                                        feed.getModificateDate(),
                                                        feed.getCategory(),
                                                        false,
                                                        feed.getWriter().getUserId());
                                })
                                .collect(Collectors.toList());
                return responseDtos;
        }

        // 카테고리로 검색
        @Override
        public List<FeedsResponseDto> getFeedsByCategory(String userId, String keyword, int page, int size) {

                Page<MainFeedEntity> resultPage = searchRepository.findByCategoryContaining(keyword,
                                PageRequest.of(page, size));

                List<MainFeedEntity> results = resultPage.getContent();

                List<FeedsResponseDto> responseDtos = results.stream()
                                .map(feed -> {
                                        Optional<LikeEntity> like = likeRepository
                                                        .findIsLikedByUserId_UserIdAndMainfeedId_MainfeedId(userId,
                                                                        feed.getMainfeedId());
                                        boolean isLiked = like.map(LikeEntity::isLiked).orElse(false);
                                        return new FeedsResponseDto(
                                                        feed.getMainfeedId(),
                                                        feed.getContent(),
                                                        feed.getTrackName(),
                                                        feed.getImage(),
                                                        feed.getCreateDate(),
                                                        feed.getModificateDate(),
                                                        feed.getCategory(),
                                                        isLiked,
                                                        feed.getWriter().getUserId());
                                })
                                .collect(Collectors.toList());
                return responseDtos;
        }

        // 비회원 카테고리로 검색
        @Override
        public List<FeedsResponseDto> nonMemberGetFeedsByCategory(String keyword, int page, int size) {

                Page<MainFeedEntity> resultPage = searchRepository.findByCategoryContaining(keyword,
                                PageRequest.of(page, size));

                List<MainFeedEntity> results = resultPage.getContent();

                List<FeedsResponseDto> responseDtos = results.stream()
                                .map(feed -> {

                                        return new FeedsResponseDto(
                                                        feed.getMainfeedId(),
                                                        feed.getContent(),
                                                        feed.getTrackName(),
                                                        feed.getImage(),
                                                        feed.getCreateDate(),
                                                        feed.getModificateDate(),
                                                        feed.getCategory(),
                                                        false,
                                                        feed.getWriter().getUserId());
                                })
                                .collect(Collectors.toList());
                return responseDtos;
        }

}
