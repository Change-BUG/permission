package com.ChangeBUG.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * token_类
 */
@Component
public class JwtUtil {

    private static final String CLAIM_KEY_USERNAME = "sub";

    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    String secret; //加密密钥

    @Value("${jwt.expiration}")
    long expiration; //失效时间


    /**
     * 从 Token 中获取 用户信息
     */
    public String get_Token_User(String token) {
        String username;

        try {
            Claims claims = getClaimsORToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }

        return username;
    }

    /**
     * 根据 用户信息 生成 Token
     */
    public String get_User_Token(String Username) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, Username);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return basis_load_generate_Token(claims);
    }

    /**
     * 判断 token 是否 失效
     */
    public boolean validate_token(String token, UserDetails userDetails) {
        String username = userDetails.getUsername();
        return username.equals(userDetails.getUsername()) && isTokenExpired(token);
    }

    /**
     * 刷新 token
     */
    public String refreshToken(String token) {
        Claims claimsORToken = getClaimsORToken(token);
        claimsORToken.put(CLAIM_KEY_CREATED, new Date());
        return basis_load_generate_Token(claimsORToken);
    }

    /**
     * 从 token 中获取 过期 时间
     */
    public Date getExpiredDateToken(String token) {
        Claims claimsORToken = getClaimsORToken(token);
        if (claimsORToken == null) {
            return new Date(new Date().getTime());
        }
        return claimsORToken.getExpiration();
    }

    /**
     * 判断 token 的时间是否失效
     */
    public boolean isTokenExpired(String token) {
        Date date = getExpiredDateToken(token);
        return !date.before(new Date());
    }

    /**
     * 从 Token 中 获取 荷载
     */
    public Claims getClaimsORToken(String token) {
        Claims body = null;
        try {
            body = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            e.fillInStackTrace();
        }
        return body;
    }

    /**
     * 根据 荷载 生成 JWT Token
     */
    private String basis_load_generate_Token(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generate_Token_Data())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 生成 Token 失效时间
     */
    public Date generate_Token_Data() {
        return new Date(System.currentTimeMillis() + expiration * 1000); //  * 1000 等于一天
    }

}