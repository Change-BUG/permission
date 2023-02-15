package com.ChangeBUG.config.permission;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * 通过此类，判断用户时候拥有上述中的角色
 */
@Component
@Slf4j
public class MyAccessDecisionManager implements AccessDecisionManager {
 
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes)
            throws AccessDeniedException, InsufficientAuthenticationException {
        // 获取用户拥有的权限信息
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        // 这里判断用户拥有的角色和该url需要的角色是否有匹配
        for (ConfigAttribute configAttribute : configAttributes) {
            String attribute = configAttribute.getAttribute();
            for (GrantedAuthority authority : authorities) {
                if (attribute.equals(authority.getAuthority())) {
                    log.info("匹配成功.");
                    return;
                }
            }
        }
        // 没有匹配就抛出异常
        throw new AccessDeniedException("权限不足,无法访问");
    }
    // 此 AccessDecisionManager 实现是否可以处理传递的 ConfigAttribute
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }
    // 此 AccessDecisionManager 实现是否能够提供该对象类型的访问控制决策。
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}