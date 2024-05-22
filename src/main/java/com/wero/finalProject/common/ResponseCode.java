package com.wero.finalProject.common;

/**
 * @작성자:오현암
 * @작성날짜:2024/04/25
 * @파일명:ResponseCode.interface
 * @기능:에러,성공코드_기능
 **/
public interface ResponseCode {

    // TODO: 200
    String SUCCESS = "SU"; // HTTP STATUS: 200 성공

    // TODO: 201
    String CREATED = "CA";

    // TODO: 400
    String VALIDATION_FAIL = "VF"; // HTTP STATUS: 400 검증 실패
    String DUPLICATE_ID = "DI"; // HTTP STATUS: 400 중복되는 아이디
    String NOT_EXIST_USER = "NU"; // HTTP STATUS: 400 존재하지 않는 유저
    String NOT_EXIST_BOARD = "NB"; // HTTP STATUS: 400 존재하지 않는 게시글
    String MAIL_FAIL = "MF"; // 이메일 오류
    String DUPLICATE_EMAIL = "DE";
    String NO_EXIST_REPORT = "NER";
    String UPDATE_FAIL = "UF";
    String NOT_EXIST_DIARY = "ND";
    String DUPLICATE_NICKNAME = "DN";
    String DELETE_FAIL = "DF";

    String RESTRICTED_USER = "RU";
    // TODO: 400 추가
    String CREATE_FAIL = "CAF";// 생성 실패
    String DELET_FAIL = "DF";

    // TODO: 401
    String SIGN_IN_FAIL = "SF"; // HTTP STATUS: 401 로그인 실패
    String CERTIFICATION_FAIL = "CF"; // HTTP STATUS: 401 검증 실패

    // TODO:403
    String NO_PERMISSION = "NP";// HTTP STATUS: 403 권한 없음

    // TODO: 500
    String DATABASE_ERROR = "DBE"; // HTTP STATUS: 500 디비 오류

}
