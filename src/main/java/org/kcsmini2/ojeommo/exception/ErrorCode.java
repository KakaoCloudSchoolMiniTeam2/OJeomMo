package org.kcsmini2.ojeommo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_ID(HttpStatus.BAD_REQUEST, "중복된 회원 아이디 입니다."),
    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "중복된 이메일 입니다."),
    NULL_FIELD(HttpStatus.BAD_REQUEST, "모든 입력란을 작성해주세요."),
    NONEXISTENT_ID(HttpStatus.BAD_REQUEST, "존재하지않는 아이디입니다."),
    UNCORRECTED_PW(HttpStatus.BAD_REQUEST, "잘못된 비밀번호입니다."),
    TOKEN_INVALID(HttpStatus.UNAUTHORIZED, "로그인 후 이용가능합니다."),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, "게시글 소유자가 아닙니다."),
    INVALID_BUMP_TIME(HttpStatus.FORBIDDEN, "끌올 요청 후 1시간이 지나지 않았습니다."),
    INVALID_BUMP_DAY(HttpStatus.FORBIDDEN, "당일 작성 된 게시글이 아니면 끌올이 불가능합니다."),
    INVALID_JOIN(HttpStatus.FORBIDDEN, "내가 쓴 글은 조인이 불가능합니다."),
    NONEXISTENT_BOARD(HttpStatus.NOT_FOUND, "게시글이 존재하지 않습니다."),
    NONEXISTENT_CATEGORY(HttpStatus.NOT_FOUND, "카테고리가 유효하지 않습니다.");

    private final HttpStatus status;
    private final String message;
}
