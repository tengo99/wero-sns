package com.wero.finalProject.dto.object;

import java.util.ArrayList;
import java.util.List;

import com.wero.finalProject.domain.DiaryEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @작성자:최기원
 * @작성날짜:2024/05/09
 * @파일명:DiaryListItem.class
 * @기능:일기 리스트에 담아서 반환
 **/

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DiaryListItem {
    private int diaryId;
    private String diaryContent;
    private String emotion;
    private String song;
    private String userId;
    private Integer isBookmarked;
    private String image;

    public DiaryListItem(DiaryEntity diaryEntity) {// 일기 엔티티를 받아서
        this.diaryId = diaryEntity.getDiaryId();// 속성들에다가 넣어준다
        this.diaryContent = diaryEntity.getDiaryContent();
        this.emotion = diaryEntity.getEmotion();
        this.song = diaryEntity.getSong();
        this.userId = diaryEntity.getWriter().getUserId();
        this.isBookmarked = diaryEntity.getBookMarkCount();
        this.image = diaryEntity.getImage();
    }

    public static List<DiaryListItem> getList(List<DiaryEntity> diaryEntities) {// 일기 리스트를 받는다
        List<DiaryListItem> list = new ArrayList<>();// DiaryListItem 엔티티들을 담을 리스트 생성
        for (DiaryEntity diaryListViewEntity : diaryEntities) {// 일기 리스트에 있는 일기들을 하나씩 꺼내서
            DiaryListItem diaryListItem = new DiaryListItem(diaryListViewEntity);// DiaryListItem에다가 각각의 일기의 내용들을 넣어주고
            list.add(diaryListItem);// DiaryListItem 엔티티들을 담을 리스트에다가 저장해준다
        }
        return list;// DiaryListItem 엔티티들이 담긴 리스트를 반환
    }
}
