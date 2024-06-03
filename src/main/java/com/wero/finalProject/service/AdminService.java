package com.wero.finalProject.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.wero.finalProject.domain.UserEntity;
import com.wero.finalProject.dto.response.report.ReportResponseDto;

public interface AdminService {

    Integer getDistinctReportsByMainFeedSize();

    List<ReportResponseDto> getDistinctReportsByMainFeed(int page, int size);

    ResponseEntity<?> userSuspension(String userId);

    Integer getUserSuspensionSize();

    List<UserEntity.UserSuspensionDto> getUserSuspension(int page, int size);

    // void deleteReport(Integer id);
}