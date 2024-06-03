package com.wero.finalProject.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/22
 * @파일명:ReportEntity
 * @기능:Report 엔티티
 **/

@Entity
@Builder
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "report")
public class ReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id", nullable = false)
    private Integer reportId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mainfeed_id", nullable = false)
    private MainFeedEntity mainfeedId;

    @Column(name = "mainfeed_user_id", nullable = false)
    private String mainfeedUserId;

    @Column(name = "reporter_id", nullable = false)
    private String reporterId;

    @Column(name = "reported_time", nullable = false)
    private String reportedTime;

    @Column(name = "report_content", nullable = false)
    private String reportContent;
}
