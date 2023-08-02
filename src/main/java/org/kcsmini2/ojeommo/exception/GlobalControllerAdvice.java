package org.kcsmini2.ojeommo.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpResponse;
import java.security.SignatureException;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice{

    @ExceptionHandler
    public void commonExHandler(ApplicationException ex, HttpServletResponse response) throws IOException {
        String errorMessage = ex.getMessage();

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + errorMessage + "'); history.go(-1); </script> ");
        out.flush();
    }


}
