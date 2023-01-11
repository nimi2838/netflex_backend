package com.mysite.nexfilx.config.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component(value = "authenticationFailureHandler")
public class LoginFailureHandler implements AuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException authenticationException) throws IOException, ServletException {
        // 실패로직 핸들링
        authenticationException.printStackTrace();
        writePrintErrorResponse(response, authenticationException);
    }

    private void writePrintErrorResponse(HttpServletResponse response, AuthenticationException authenticationException) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Object> responseMap = new HashMap<>();
            String message = getExceptionMessage(authenticationException);
            responseMap.put("status", "401");
            responseMap.put("message", message);
            response.getOutputStream().println(objectMapper.writeValueAsString(responseMap));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String getExceptionMessage(AuthenticationException authenticationException) {
        if (authenticationException instanceof BadCredentialsException) {
            return "비밀번호 불일치";
        } else if (authenticationException instanceof UsernameNotFoundException) {
            return "계정 없음";
        } else if (authenticationException instanceof AccountExpiredException) {
            return "계정 만료";
        } else if (authenticationException instanceof CredentialsExpiredException) {
            return "비밀번호 만료";
        } else if (authenticationException instanceof DisabledException) {
            return "계정 비활성화";
        } else if (authenticationException instanceof LockedException) {
            return "계정 잠김";
        } else {
            return "확인된 에러가 없습니다.";
        }

    }



}
