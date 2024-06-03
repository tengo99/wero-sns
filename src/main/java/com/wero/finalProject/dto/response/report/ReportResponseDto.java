package com.wero.finalProject.dto.response.report;

import lombok.Data;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/22
 * @파일명:ReportResponseDto
 * @기능:신고 조회 응답 데이터 전달
 **/

@Data

public class ReportResponseDto {

    private Integer reportId;
    private Integer mainfeedId;
    private String mainfeedUserId;
    private String reportContent;
    private String reportedTime;

    public ReportResponseDto(Integer reportId, Integer mainfeedId, String mainfeedUserId, String reportContent,
            String reportedTime) {
        this.reportId = reportId;
        this.mainfeedId = mainfeedId;
        this.mainfeedUserId = mainfeedUserId;
        this.reportContent = reportContent;
        this.reportedTime = reportedTime;

    }

}
