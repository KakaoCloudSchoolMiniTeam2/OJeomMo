package org.kcsmini2.ojeommo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_ID(HttpStatus.BAD_REQUEST, "중복된 회원 아이디 입니다."),
    DUPLICATED_EMAIL(HttpStatus.BAD_REQUEST, "중복된 이메일 입니다."),
    NULL_FIELD(HttpStatus.BAD_REQUEST, "모든 입력란을 작성해주세요.");

    private final HttpStatus status;
    private final String message;
}
