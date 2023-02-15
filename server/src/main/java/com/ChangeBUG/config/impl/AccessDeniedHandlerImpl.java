package com.ChangeBUG.config.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ChangeBUG.utils.RespListUtils;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 当访问没有权限的 接口 ,自定义 返回结果
 */
@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e)
            throws IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        RespListUtils respBean = RespListUtils.error("无法访问,权限不足");
        respBean.setCode(401);
        out.write(new ObjectMapper().writeValueAsString(respBean));
        out.flush();
        out.close();


    }

}
