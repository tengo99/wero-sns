package com.wero.finalProject.common;

/**
 * @작성자:오현암
 * @작성날짜:2024/04/25
 * @파일명:ResponseMessage.interface
 * @기능:에러,성공메세지_생성
 **/
// TODO: # 100: 데이터의 일부를 서버가 받은 상태(처리중인 상태)
// # 200: OK 에러없이 정상처리
// # 204: 정상처리 되었으나, 서버에 보낼 데이터가 없음
// # 301: 요청한 URL이 새로 변경되었음.
// # 304: 기존의 데이터와 변경된것이 없음
// # 400: 요청에 문제가 있기때문에 서버에서 인식할 수 없음
// # 403: 서버에서 허락되지않음
// # 404: 요청 URL을 찾을 수 없음
// # 406: 전송 방식이 허락되지 않음(REST방식에서 자주 나타나는 상태코드)
// # 500: 서버에서 처리시 문제가 발생(프로그램 내부적인 오류)
// # 502: 게이트웨이, 프록시 상태의 문제(과부하)
// # 503: 일시적인 서비스 중단 상태
// # 504: 지정된 처리시간이 지나서 처리되지 못하는 경우
public interface ResponseMessage {

    // TODO: HTTP200
    String SUCCESS = "success";

    // TODO : HTTP201
    String CREATED = "Created";

    // TODO: HTTP400
    String VALIDATION_FAIL = "validation failed";
    String DUPLICATE_ID = "duplicate id";
    String NOT_EXIST_USER = "Not Exist User";
    String NOT_EXIST_BOARD = "Not Exist Board";
    String MAIL_FAIL = "Mail Send Failed";
    String DUPLICATE_EMAIL = "Duplicate Email";
    String DUPLICATE_NICKNAME = "Duplicate NickName";
    String UPDATE_FAIL = "Update Fail";
    String NOT_EXIST_DIARY = "Not Exist Diary";
    String DELETE_FAIL = "Delete Fail";

    // TODO: HTTP400 추가
    String CREATE_FAIL = "Create Fail";
    String DELET_FAIL = "Delete Fail";

    // TODO: HTTP401
    String SIGN_IN_FAIL = "sing in fail";
    String CERTIFICATION_FAIL = "certification fail";

    // TODO:403
    String NO_PERMISSION = "No Permission";

    String RESTRICTED_USER = "불건전 행위로 인한 정지 유저 입니다.";

    // TODO: HTTP500
    String DATABASE_ERROR = "database_error";

}
