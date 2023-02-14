package com.ChangeBUG.config.permission;

import com.ChangeBUG.model.system.SysResource;
import com.ChangeBUG.service.SysResourceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.List;

/**
 * 通过此类，获取哪些角色可以访问该 url
 */
@Slf4j
@Component
public class MySecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    // 接口权限表
    @Autowired
    private SysResourceService service;

    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        // 获取 URL
        String requestURI = ((FilterInvocation) object).getRequest().getRequestURI();
        // 获取拥有url的角色集合
        List<String> roles = service.getRolesByUrl(requestURI);
        log.info("{} 对应的角色 {}", requestURI, roles);
        if (CollectionUtils.isEmpty(roles)) {
            return null;
        }
        // 自定义角色信息 --> Security的权限格式
        String[] attributes = roles.toArray(new String[0]);
        return SecurityConfig.createList(attributes);
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return FilterInvocation.class.isAssignableFrom(clazz);
    }
}