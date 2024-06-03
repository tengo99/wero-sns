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
        public Integer getDistinctReportsByMainFeedSize() {
                Integer allReports = reportRepository.findAll().size();
                return allReports;
        }

        // 신고된 피드 조회
        @Override
        public List<ReportResponseDto> getDistinctReportsByMainFeed(int page, int size) {

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
        public ResponseEntity<?> userSuspension(String userId) {
                try {
                        UserEntity user = userService.findUserById(userId);

                        if (user == null) {
                                throw new EntityNotFoundException();
                        }

                        boolean newRestrictionStatus = !user.isRestriction();

                        if (user.isRestriction() == false) {
                                user.suspensionUserEntity(newRestrictionStatus);
                        }

                        if (user.isRestriction() == true) {
                                user.suspensionUserEntity(newRestrictionStatus);
                        }
                        userRepository.save(user);
                        return UserUpdateResponseDto.success();

                } catch (Exception e) {
                        e.printStackTrace();
                        return ResponseDto.dataBaseError();
                }
        }

        // 정지된 유저 리스트 길이 조회
        @Override
        public Integer getUserSuspensionSize() {
                Integer allUsers = userRepository.findByRestriction(true).size();
                return allUsers;
        }

        // 정지 유저 조회
        @Override
        public List<UserEntity.UserSuspensionDto> getUserSuspension(int page, int size) {

                Page<UserEntity> allSuspensionUser = userRepository.findByRestriction(true,
                                PageRequest.of(page, size));
                List<UserEntity.UserSuspensionDto> suspensionDtoList = new ArrayList<>();

                for (UserEntity user : allSuspensionUser) {
                        suspensionDtoList.add(user.toSuspensionDto());
                }

                return suspensionDtoList;

        }

}
