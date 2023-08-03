package org.kcsmini2.ojeommo.exception;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    @ExceptionHandler(ApplicationException.class)
    public String commonExHandler(ApplicationException ex, HttpServletResponse response, Model model) throws IOException {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        model.addAttribute("error", ex.getMessage());
        response.setStatus(status.value());

        return "member_error";
    }

    @ExceptionHandler(AuthenticationException.class)
    public HttpEntity<String> jwtExHandler() {
        return new HttpEntity<>("<script>alert('" + "로그인 후 이용해주세요." + "'); history.go(-1);</script>");
    }


}
