package com.ChangeBUG.config;

import com.ChangeBUG.config.impl.*;
import com.ChangeBUG.config.permission.MyAccessDecisionManager;
import com.ChangeBUG.config.permission.MySecurityMetadataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@Configuration
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private LoginSuccessHandlerImpl loginSuccessHandler;

    @Autowired
    private LoginFailureHandlerImpl loginFailureHandler;

    @Autowired
    private MySecurityMetadataSource mySecurityMetadataSource;

    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;

    /**
     * JWT 登录 授权
     */
    @Bean
    public RequestConfig JwtTokenConfig() {
        return new RequestConfig();
    }

    /**
     * 不拦截的 地址
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("/api/v1/sysUser/login")            // 登录
                .antMatchers("/api/v1/sysUser/register")         // 注册
                .antMatchers("/api/v1/verificationCode"); // 验证码

        // swagger相关接口
        web.ignoring().antMatchers(
                "/doc.html",
                "/swagger-ui.html",
                "/swagger-resources/**",
                "/webjars/**","/*/api-docs"
        );
    }

    /**
     * 过滤器
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 基于 token , 不需要 session
        http.sessionManagement()
                //最大登陆人数为1
                .maximumSessions(1)
                // 设置false允许多点登录但是，如果超出最大人数之前的登录会被踢掉
                .maxSessionsPreventsLogin(false)
                // 配置  自定义 处理器
                .expiredSessionStrategy(new MyExpiredSessionStrategy());

        // 所有请求都需要认证
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        // 获取哪些角色可以访问该 url
                        o.setSecurityMetadataSource(mySecurityMetadataSource);
                        // 判断用户时候拥有上述中的角色
                        o.setAccessDecisionManager(myAccessDecisionManager);
                        return o;
                    }
                })
                .anyRequest().authenticated()
                // 使用 JWT,不需要 csrf
                .and().csrf().disable();

        // 禁用缓存
        http.headers().cacheControl();

        // 登录成功 登录失败
        http.formLogin().successHandler(loginSuccessHandler).failureHandler(loginFailureHandler);

        // 配置 未登录时的处理器
        http.exceptionHandling().authenticationEntryPoint(new AuthenticationEntryPointImpl());

        // 配置 无权限时的处理器
        http.exceptionHandling().accessDeniedHandler(new AccessDeniedHandlerImpl());

        // 配置退出成功的处理器
        http.logout().logoutUrl("api/v1/sysUser/logout").logoutSuccessHandler(new LogoutSuccessHandlerImpl()).deleteCookies("Admin-Token");

        // 配置 前置 添加 JWT 登录 授权 过滤器
        http.addFilterBefore(JwtTokenConfig(), UsernamePasswordAuthenticationFilter.class);
    }

}