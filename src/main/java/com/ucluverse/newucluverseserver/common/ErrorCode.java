package com.ucluverse.newucluverseserver.common;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    /* 400 BAD_REQUEST : 잘못된 요청 */
    ALREADY_SIGNED_UP(BAD_REQUEST, "이미 가입된 이메일입니다"),
    INVALID_EMAIL_DOMAIN(BAD_REQUEST, "아주대 이메일이 아닙니다"),

    /* 401 UNAUTHORIZED : 인증되지 않은 사용자 */
    INVALID_MEMBER_UPDATE_POSTING(UNAUTHORIZED, "해당 게시글에 대한 권한이 없는 사용자입니다"),
//    UNAUTHORIZED_MEMBER(UNAUTHORIZED, "현재 내 계정 정보가 존재하지 않습니다"),

    /* 404 NOT_FOUND : Resource 를 찾을 수 없음 */
    MEMBER_NOT_FOUND(NOT_FOUND, "유저 정보를 찾을 수 없습니다"),
    DEPARTMENT_NOT_FOUND(NOT_FOUND, "학과 정보를 찾을 수 없습니다"),
    CLUB_NOT_FOUND(NOT_FOUND, "동아리 정보를 찾을 수 없습니다"),
    CLUB_POSTING_NOT_FOUND(NOT_FOUND, "동아리 게시판 정보를 찾을 수 없습니다"),
    POSTING_NOT_FOUND(NOT_FOUND, "게시글 정보를 찾을 수 없습니다");

    /* 409 CONFLICT : Resource 의 현재 상태와 충돌. 보통 중복된 데이터 존재 */
//    DUPLICATE_RESOURCE(CONFLICT, "데이터가 이미 존재합니다"),

    private final HttpStatus httpStatus;
    private final String detail;
}
