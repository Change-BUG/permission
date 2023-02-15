package com.ChangeBUG.utils;

import io.swagger.annotations.ApiModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.security.core.userdetails.User;

@Component
@ApiModel(value = "LocalDateTimeUtil_用户验证器-工具类", description = "")
public class UserVerifyUtils {

    @Autowired
    private JwtUtil jwtTokenUtils;
    @Autowired
    private TimeUtils dateTimeUtil;

    // JWT 负载中拿到开头
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    /**
     * 更新 Security 登录用户对象器
     */
    public UsernamePasswordAuthenticationToken renewSecurityUser(UserDetails userDetails) {
//        log.info("登录成功 正在存入登录信息");
        // 更新 security 登录用户对象
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        // 存入 SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        return authenticationToken;
    }

    /**
     * 生成 Token 和 个人信息
     */
    public String judgment_Token(UsernamePasswordAuthenticationToken authenticationToken, UserDetails userDetails) {

        // 获取 用户信息
        User user = (User) authenticationToken.getPrincipal();
        // 用 用户名 生成新的 token
        String token = tokenHead + " " + jwtTokenUtils.get_User_Token(user.getUsername());

        // Rides 印证
//        Token_List token_list = new Token_List();
//        token_list.setToken(token);
//        token_list.setDate(jwtTokenUtils.getExpiredDateToken(token));
//        token_list.setWhetherToLogOut(true);

        return token;
    }

}
