package com.ChangeBUG.config.impl;

import com.ChangeBUG.utils.RespListUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mchange.v2.cmdline.BadCommandLineException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Component
public class LoginFailureHandlerImpl implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception)
            throws IOException, ServletException {

        String massage = exception.getMessage();
        if (exception instanceof BadCredentialsException){
            massage = "登录失败";
        }

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        RespListUtils respBean = RespListUtils.error(massage);
        respBean.setCode(200);
        out.write(new ObjectMapper().writeValueAsString(respBean));
        out.flush();
        out.close();

    }
}
