package com.wero.finalProject.domain;

import java.util.HashSet;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @작성자:김선규
 * @작성날짜:2024/05/07
 * @파일명:MainFeedEntity
 * @기능:MainFeed 엔티티
 **/

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "mainfeed")
public class MainFeedEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mainfeed_id", nullable = false)
    private Integer mainfeedId;

    @Column(name = "content", nullable = false)
    private String content;

    @Builder.Default
    @Column(name = "trackname", nullable = false)
    private String trackName = "";

    @Column(name = "image", nullable = true)
    private String image;

    @Column(name = "create_date", nullable = false)
    private String createDate;

    @Column(name = "modificate_date", nullable = true)
    private String modificateDate;

    @Column(name = "category", nullable = false)
    private String category;

    @Column(name = "report_status", nullable = false)
    @Builder.Default
    private Boolean reportStatus = false;

    @OneToMany(mappedBy = "mainfeedId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<LikeEntity> likes = new HashSet<>();

    @OneToMany(mappedBy = "mainfeedId", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @Builder.Default
    private Set<ReportEntity> reports = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private UserEntity writer;
}
