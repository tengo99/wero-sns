package com.wero.finalProject.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.wero.finalProject.domain.UserEntity;
import com.wero.finalProject.dto.response.report.ReportResponseDto;

public interface AdminService {

    Integer getDistinctReportsByMainFeedSize(String user);

    List<ReportResponseDto> getDistinctReportsByMainFeed(String user, int page, int size);

    ResponseEntity<?> userSuspension(String user, String userId);

    Integer getUserSuspensionSize(String user);

    List<UserEntity.UserSuspensionDto> getUserSuspension(String user, int page, int size);

    // void deleteReport(Integer id);
}