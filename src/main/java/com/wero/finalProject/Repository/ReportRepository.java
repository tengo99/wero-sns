package com.wero.finalProject.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.wero.finalProject.domain.ReportEntity;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/22
 * @파일명:ReportRepository
 * @기능:Report 리포지토리
 **/

@Repository
public interface ReportRepository extends JpaRepository<ReportEntity, Integer> {

    ReportEntity findByReporterIdAndMainfeedId_MainfeedId(String reporterId, Integer mainfeedId);

    Page<ReportEntity> findAllByOrderByReportedTimeAsc(Pageable pageable);

}
