package org.kcsmini2.ojeommo.exception;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;

@Slf4j
@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    @ExceptionHandler(ApplicationException.class)
    public String commonExHandler(ApplicationException ex, HttpServletResponse response, Model model) {

        model.addAttribute("error", ex.getMessage());
        response.setStatus(ex.getErrorCode().getStatus().value());

        return "member_error";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String validExHandler(MethodArgumentNotValidException ex, HttpServletResponse response, Model model) {
        model.addAttribute("error", ex.getBindingResult().getFieldError().getDefaultMessage());
        response.setStatus(HttpStatus.BAD_REQUEST.value());

        return "member_error";
    }

    @ExceptionHandler(AuthenticationException.class)
    public void jwtExHandler(HttpServletResponse response) {

        // JWT 토큰 쿠키 삭제
        Cookie authCookie = new Cookie("Authorization", "");
        authCookie.setMaxAge(0);
        authCookie.setPath("/");
        response.addCookie(authCookie);

        // 로그인 페이지로 리다이렉트
        try {
            response.sendRedirect("/login");
        } catch (IOException e) {
            log.info(e.getMessage());
        }
    }


}
