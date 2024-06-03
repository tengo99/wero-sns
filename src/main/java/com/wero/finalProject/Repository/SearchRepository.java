package com.wero.finalProject.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wero.finalProject.domain.MainFeedEntity;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/07
 * @파일명:SearchDiaryRepository
 **/
@Repository
public interface SearchRepository extends JpaRepository<MainFeedEntity, Integer> {

    Page<MainFeedEntity> findByContentContaining(String keyword, Pageable pageable);

    Page<MainFeedEntity> findByCategoryContaining(String keyword, Pageable pageable);
}
