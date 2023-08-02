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
    TOKEN_INVALID(HttpStatus.BAD_REQUEST, "로그인 후 이용가능합니다."),
    NONEXISTENT_ID(HttpStatus.BAD_REQUEST, "존재하지않는 아이디입니다."),
    UNCORRECTED_PW(HttpStatus.BAD_REQUEST, "잘못된 비밀번호입니다.");

    private final HttpStatus status;
    private final String message;
}
