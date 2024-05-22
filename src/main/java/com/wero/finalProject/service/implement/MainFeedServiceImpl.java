package com.wero.finalProject.service.implement;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.wero.finalProject.Repository.LikeRepository;
import com.wero.finalProject.Repository.MainFeedRepository;
import com.wero.finalProject.domain.LikeEntity;
import com.wero.finalProject.domain.MainFeedEntity;
import com.wero.finalProject.domain.UserEntity;
import com.wero.finalProject.dto.response.feeds.FeedsResponseDto;
import com.wero.finalProject.service.MainFeedService;
import com.wero.finalProject.service.UserService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/07
 * @파일명:MainFeedServiceImpl
 * @기능:MainFeed 서비스 상세
 **/

@Service("MainFeedServiceImpl")
public class MainFeedServiceImpl implements MainFeedService {

    @Autowired
    private MainFeedRepository mainFeedRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private LikeRepository likeRepository;

    @PersistenceContext
    private EntityManager entityManager;

    // 메인 피드 전체 조회
    @Override
    public List<FeedsResponseDto> getAllFeeds(String userId) {

        try {
            List<MainFeedEntity> feeds = mainFeedRepository.findAll();
            List<FeedsResponseDto> responseDtos = feeds.stream()
                    .map(feed -> {
                        Optional<LikeEntity> like = likeRepository
                                .findIsLikedByUserId_UserIdAndMainfeedId_MainfeedId(userId, feed.getMainfeedId());
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 피드 id로 피드 하나 찾기
    @Override
    public FeedsResponseDto getOneFeeds(String userId, Integer id) {
        try {
            MainFeedEntity feed = mainFeedRepository.findByMainfeedId(id);

            Optional<LikeEntity> like = likeRepository
                    .findIsLikedByUserId_UserIdAndMainfeedId_MainfeedId(userId, id);
            boolean isLiked = like.map(LikeEntity::isLiked).orElse(false);
            FeedsResponseDto responseDtos = new FeedsResponseDto(
                    feed.getMainfeedId(),
                    feed.getContent(),
                    feed.getTrackName(),
                    feed.getImage(),
                    feed.getCreateDate(),
                    feed.getModificateDate(),
                    feed.getCategory(),
                    isLiked,
                    feed.getWriter().getUserId());

            return responseDtos;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    // 유저 피드 userId로 찾기
    @Override
    public List<FeedsResponseDto> getFeedByUserId(String userId) {
        List<MainFeedEntity> feeds = mainFeedRepository.findByWriter_UserId(userId);

        try {
            List<FeedsResponseDto> responseDtos = feeds.stream()
                    .map(feed -> {
                        Optional<LikeEntity> like = likeRepository
                                .findIsLikedByUserId_UserIdAndMainfeedId_MainfeedId(userId, feed.getMainfeedId());
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // 유저가 좋아요한 피드 목록 조회
    @Override
    public List<FeedsResponseDto> getFeedByUserIdAndIsLiked(String userId) {

        // 사용자 ID로 Like엔티티 가져오기
        List<LikeEntity> likeEntities = likeRepository.findMainfeedIdByUserId_UserId(userId);

        try {
            // 좋아요 한 피드 아이디를 담기 위해 빈 배열 생성
            List<Integer> mainfeedIds = new ArrayList<>();

            // 좋아요 한 피드 아이디 리스트 생성
            likeEntities.forEach(like -> mainfeedIds.add(like.getMainfeedId().getMainfeedId()));

            List<MainFeedEntity> feeds = mainFeedRepository.findByMainfeedIdIn(mainfeedIds);

            List<FeedsResponseDto> responseDtos = feeds.stream()
                    .map(feed -> {
                        Optional<LikeEntity> like = likeRepository
                                .findIsLikedByUserId_UserIdAndMainfeedId_MainfeedId(userId, feed.getMainfeedId());
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
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    // 메인 피드 생성
    @Override
    public MainFeedEntity createFeed(String userId, MainFeedEntity mainFeedEntity) {
        try {
            UserEntity user = userService.findUserById(userId);
            MainFeedEntity feed = MainFeedEntity.builder()
                    .content(mainFeedEntity.getContent())
                    .trackName(mainFeedEntity.getTrackName())
                    .category(mainFeedEntity.getCategory())
                    .image(mainFeedEntity.getImage())
                    .createDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                    .writer(user)
                    .build();

            return mainFeedRepository.save(feed);
        } catch (Exception e) {
            throw new RuntimeException("Failed to create feed", e);
        }
    }

    // 메인 피드 수정
    @Override
    public MainFeedEntity updateFeed(Integer id, MainFeedEntity feedDetails, String userId) {
        MainFeedEntity mainFeedEntity = mainFeedRepository.findById(id).orElse(null);

        try {
            UserEntity user = userService.findUserById(userId);
            if (mainFeedEntity == null || mainFeedEntity.getMainfeedId() == null) {

                throw new EntityNotFoundException();
            } else {

                MainFeedEntity updateFeed = MainFeedEntity.builder()
                        .mainfeedId(mainFeedEntity.getMainfeedId())
                        .content(feedDetails != null ? feedDetails.getContent() : mainFeedEntity.getContent())
                        .trackName(feedDetails != null ? feedDetails.getTrackName() : mainFeedEntity.getTrackName())
                        .category(feedDetails != null ? feedDetails.getCategory() : mainFeedEntity.getCategory())
                        .image(feedDetails != null ? feedDetails.getImage() : mainFeedEntity.getImage())
                        .createDate(mainFeedEntity.getCreateDate())
                        .modificateDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
                        .writer(user)
                        .build();
                return mainFeedRepository.save(updateFeed);
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to update feed", e);
        }

    }

    // 메인 피드 삭제
    @Override
    public void deleteFeed(Integer id) {

        try {
            MainFeedEntity mainFeedEntity = mainFeedRepository.findById(id).orElse(null);

            if (mainFeedEntity == null || mainFeedEntity.getMainfeedId() == null) {
                throw new EntityNotFoundException();
            }

            List<LikeEntity> likeEntityList = likeRepository.findLikeIdByMainfeedId_MainfeedId(id);

            if (likeEntityList == null || likeEntityList.size() == 0) {
                throw new EntityNotFoundException();
            }

            List<Integer> likeEntityIds = new ArrayList<>();

            // LikeEntity에서 id를 추출하여 Integer타입의 리스트에 추가
            for (LikeEntity likeEntity : likeEntityList) {
                likeEntityIds.add(likeEntity.getLikeId());
            }

            // LikeEntity 삭제
            for (Integer likeId : likeEntityIds) {
                likeRepository.deleteById(likeId);
            }

            mainFeedRepository.deleteById(id);

        } catch (Exception e) {
            throw new RuntimeException("Failed to delete feed", e);
        }

    }

    // 인증된 사용자가 있는 경우 userId값 받아오기
    public String getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            UserEntity user = (UserEntity) authentication.getPrincipal();

            return user.getUserId();
        }
        return null;
    }

    // 좋아요 추가
    @Override
    public LikeEntity addLikeFeed(String userId, Integer id) {

        Optional<LikeEntity> like = likeRepository.findLikeIdByUserId_UserIdAndMainfeedId_MainfeedId(userId, id);

        if (like.isPresent()) {
            return like.get();
        }
        try {

            UserEntity userReference = entityManager.getReference(UserEntity.class, userId);
            MainFeedEntity feedReference = entityManager.getReference(MainFeedEntity.class, id);
            LikeEntity likeFeed = LikeEntity.builder()
                    .userId(userReference)
                    .mainfeedId(feedReference)
                    .isLiked(true)
                    .build();
            return likeRepository.save(likeFeed);
        } catch (Exception e) {
            throw new RuntimeException("Failed to add Like", e);
        }
    }

    // 좋아요 삭제
    @Override
    public void deleteLikeFeed(String userId, Integer id) {

        Optional<LikeEntity> like = likeRepository.findLikeIdByUserId_UserIdAndMainfeedId_MainfeedId(userId, id);

        if (like == null || id == null) {
            throw new EntityNotFoundException();
        }
        try {
            System.out.println(like);
            likeRepository.deleteById(like.get().getLikeId());
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete Like", e);
        }
    }

}
