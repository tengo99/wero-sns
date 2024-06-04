package com.wero.finalProject.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.wero.finalProject.Repository.ReportRepository;
import com.wero.finalProject.Repository.UserRepository;
import com.wero.finalProject.domain.ReportEntity;
import com.wero.finalProject.domain.UserEntity;
import com.wero.finalProject.dto.response.ResponseDto;
import com.wero.finalProject.dto.response.report.ReportResponseDto;
import com.wero.finalProject.dto.response.user.UserUpdateResponseDto;
import com.wero.finalProject.service.AdminService;
import com.wero.finalProject.service.UserService;

import jakarta.persistence.EntityNotFoundException;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/22
 * @파일명:AdminServiceImpl
 * @기능:Admin 서비스 상세
 **/

@Service("AdminServiceImpl")
public class AdminServiceImpl implements AdminService {

        @Autowired
        private ReportRepository reportRepository;

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private UserService userService;

        // 신고된 피드 길이 조회
        @Override
        public Integer getDistinctReportsByMainFeedSize(String user) {
                if (user == null) {
                        // userId가 null인 경우 예외 던지기
                        throw new IllegalArgumentException("User ID cannot be null");
                }
                Integer allReports = reportRepository.findAll().size();
                return allReports;
        }

        // 신고된 피드 조회
        @Override
        public List<ReportResponseDto> getDistinctReportsByMainFeed(String user, int page, int size) {

                if (user == null) {
                        // userId가 null인 경우 예외 던지기
                        throw new IllegalArgumentException("User ID cannot be null");
                }

                Page<ReportEntity> allReportsPage = reportRepository
                                .findAllByOrderByReportedTimeAsc(PageRequest.of(page, size));
                List<ReportEntity> allReports = allReportsPage.getContent();

                Map<Integer, ReportEntity> distinctReportsMap = allReports.stream()
                                .collect(Collectors.toMap(
                                                report -> report.getMainfeedId().getMainfeedId(),
                                                report -> report,
                                                (existing, replacement) -> existing));

                List<ReportResponseDto> reportResponseDtos = distinctReportsMap.values().stream()
                                .map(report -> {
                                        return new ReportResponseDto(
                                                        report.getReportId(),
                                                        report.getMainfeedId().getMainfeedId(),
                                                        report.getMainfeedId().getWriter().getUserId(),
                                                        report.getMainfeedId().getContent(),
                                                        report.getReportedTime());
                                })
                                .collect(Collectors.toList());

                return reportResponseDtos;
        }

        // 유저 정지
        @Override
        public ResponseEntity<?> userSuspension(String user, String userId) {
                try {
                        if (user == null) {
                                throw new EntityNotFoundException();
                        }
                        UserEntity reportUser = userService.findUserById(userId);

                        boolean newRestrictionStatus = !reportUser.isRestriction();

                        if (reportUser.isRestriction() == false) {
                                reportUser.suspensionUserEntity(newRestrictionStatus);
                        }

                        if (reportUser.isRestriction() == true) {
                                reportUser.suspensionUserEntity(newRestrictionStatus);
                        }
                        userRepository.save(reportUser);
                        return UserUpdateResponseDto.success();

                } catch (Exception e) {
                        e.printStackTrace();
                        return ResponseDto.dataBaseError();
                }
        }

        // 정지된 유저 리스트 길이 조회
        @Override
        public Integer getUserSuspensionSize(String user) {
                if (user == null) {
                        throw new EntityNotFoundException();
                }
                Integer allUsers = userRepository.findByRestriction(true).size();
                return allUsers;
        }

        // 정지 유저 조회
        @Override
        public List<UserEntity.UserSuspensionDto> getUserSuspension(String user, int page, int size) {
                if (user == null) {
                        throw new EntityNotFoundException();
                }

                Page<UserEntity> allSuspensionUser = userRepository.findByRestriction(true,
                                PageRequest.of(page, size));
                List<UserEntity.UserSuspensionDto> suspensionDtoList = new ArrayList<>();

                for (UserEntity reportUser : allSuspensionUser) {
                        suspensionDtoList.add(reportUser.toSuspensionDto());
                }

                return suspensionDtoList;

        }

}
