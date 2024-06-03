package com.wero.finalProject.dto.response.diary;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wero.finalProject.common.ResponseCode;
import com.wero.finalProject.common.ResponseMessage;
import com.wero.finalProject.domain.DiaryEntity;
import com.wero.finalProject.dto.response.ResponseDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @작성자:최기원
 * @작성날짜:2024/05/04
 * @파일명:GetDiaryResponseDto.class
 * @기능:일기 조회 dto
 **/

@Getter
@NoArgsConstructor
@Setter
public class GetDiaryResponseDto extends ResponseDto {

    private int diaryId;
    private String diaryContent;
    private String emotion;
    private String song;
    private String userId;
    private String image;

    private GetDiaryResponseDto(DiaryEntity diaryEntity) {// 엔티티를 받는 생성자
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);// 부모로부터 상속받은 프로퍼티들 채워주고
        this.diaryId = diaryEntity.getDiaryId();// 전달받은 엔티티의 아이디 받기
        this.diaryContent = diaryEntity.getDiaryContent();// 전달받은 엔티티의 콘텐츠 받기
        this.emotion = diaryEntity.getEmotion();// 전달받은 엔티티의 감정 받기
        this.song = diaryEntity.getSong();// 전달받은 엔티티의 노래 받기
        this.userId = diaryEntity.getWriter().getUserId();// 전달받은 엔티티의 작성자 받기
        this.image = diaryEntity.getImage();
    }

    public static ResponseEntity<GetDiaryResponseDto> success(DiaryEntity diaryEntity) {// serviceImpl로 부터 diaryEntity를
                                                                                        // 받아서
        GetDiaryResponseDto result = new GetDiaryResponseDto(diaryEntity);// GetDiaryResponseDto를 생성한다. 다른 응답 dto들과는 다르게
                                                                          // 엔티티를 넣어서 생성자 호출한다
        return ResponseEntity.status(HttpStatus.OK).body(result);// ResponseEntity에 상태코드와 GetDiaryResponseDto객체 넣어서 리턴한다
    }

    public static ResponseEntity<ResponseDto> notExistDiary() {
        ResponseDto result = new ResponseDto(ResponseCode.NOT_EXIST_DIARY, ResponseMessage.NOT_EXIST_DIARY);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(result);

    }
}
