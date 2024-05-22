package com.wero.finalProject.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wero.finalProject.domain.LikeEntity;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/09
 * @파일명:LikeEntityRepository
 * @기능:LikeEntity 레포지토리
 **/

@Repository
public interface LikeRepository extends JpaRepository<LikeEntity, Integer> {
    Optional<LikeEntity> findIsLikedByUserId_UserIdAndMainfeedId_MainfeedId(String userId, Integer mainfeedId);

    Optional<LikeEntity> findLikeIdByUserId_UserIdAndMainfeedId_MainfeedId(String userId, Integer mainfeedId);

    List<LikeEntity> findMainfeedIdByUserId_UserId(String userId);

    List<LikeEntity> findLikeIdByMainfeedId_MainfeedId(Integer mainfeedId);

    void deleteAllByMainfeedId_MainfeedId(Integer mainfeedId);
}
