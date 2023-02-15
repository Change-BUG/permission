package com.ChangeBUG.config;

import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import com.ChangeBUG.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * JWT 登录 授权 过滤器
 */
@Slf4j
@Component
public class RequestConfig extends OncePerRequestFilter {

    // JWT 存储的请求头
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    // JWT 负载中拿到开头
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    // Token 工具
    @Autowired
    private JwtUtil jwtTokenUtils;

    // userDetailsService
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NotNull HttpServletResponse response,
            @NotNull FilterChain filterChain) throws ServletException, IOException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // 取 token ====================================================================================================
        String token = request.getHeader(tokenHeader);
        // token 是否 存在 ==============================================================================================
        if (!StringUtils.hasText(token)) {
            // 不存在 不给 进入
            filterChain.doFilter(request, response);
            return;
        }
        // 分割 token 得到 后缀
        String authToken = token.substring(tokenHead.length());
        // 从 token 获取 账号
        String username = jwtTokenUtils.get_Token_User(authToken);
        // 判断Token
        if(username == null){
            throw new JwtException("Token不合法");
        }else if (!jwtTokenUtils.isTokenExpired(authToken)){
            throw new JwtException("Token过期了");
        }
        // =====================================================================================
        log.info("账号 : " + username + " -- " + request.getServletPath());
        // =====================================================================================
        // 查询 用户
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        // 验证 token 是否 有效 , 重新 设置 用户 对象
        if (jwtTokenUtils.validate_token(authToken, userDetails)) {
            // 存入 用户 信息
            UsernamePasswordAuthenticationToken authenticationToken =
                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
            // 存入
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            // 存入 SecurityContextHolder
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        // 返回
        filterChain.doFilter(request, response);
    }
}