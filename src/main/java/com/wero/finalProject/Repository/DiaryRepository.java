package com.wero.finalProject.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wero.finalProject.domain.DiaryEntity;

/**
 * @작성자:최기원
 * @작성날짜:2024/05/05
 * @파일명:DiaryRepository.interface
 * @기능:diaryRepository<->JPA
 **/

@Repository
public interface DiaryRepository extends JpaRepository<DiaryEntity, Integer> {

    List<DiaryEntity> findByWriter_UserId(String userId);
}
