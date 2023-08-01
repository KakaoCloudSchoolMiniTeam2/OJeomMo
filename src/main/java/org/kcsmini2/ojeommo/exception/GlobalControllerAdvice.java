package org.kcsmini2.ojeommo.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.io.IOException;
import java.io.PrintWriter;

@ControllerAdvice
@RequiredArgsConstructor
public class GlobalControllerAdvice {

    @ExceptionHandler
    public void commonExHandler(ApplicationException ex, HttpServletResponse response) throws IOException {
        String errorMessage = ex.getMessage();

        response.setCharacterEncoding("utf-8");
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println("<script>alert('" + errorMessage + "'); history.go(-1); </script> ");
        out.flush();
    }
}
