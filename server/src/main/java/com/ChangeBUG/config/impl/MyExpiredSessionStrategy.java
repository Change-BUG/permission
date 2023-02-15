package com.ChangeBUG.config.impl;

import com.ChangeBUG.utils.RespListUtils;
import com.alibaba.fastjson2.JSON;
import org.springframework.security.web.session.SessionInformationExpiredEvent;
import org.springframework.security.web.session.SessionInformationExpiredStrategy;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class MyExpiredSessionStrategy implements SessionInformationExpiredStrategy {

    @Override
    public void onExpiredSessionDetected(SessionInformationExpiredEvent event) throws IOException {
        event.getResponse().setContentType("application/json;charset=UTF-8");
        event.getResponse().getWriter().write(JSON.toJSONString(RespListUtils.error("账号异地登录在")));
    }

}
