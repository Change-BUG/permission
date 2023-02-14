package com.ChangeBUG.config.UserDetails;

import com.ChangeBUG.exception.UserCountLockException;
import com.ChangeBUG.model.system.SysDepartment;
import com.ChangeBUG.model.system.SysUser;
import com.ChangeBUG.service.SysDepartmentService;
import com.ChangeBUG.service.SysUserService;
import com.ChangeBUG.utils.TestEmptyUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
@Slf4j
@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    // 用户查询
    @Autowired
    private SysUserService sys_userService;

    // 部门查询
    @Autowired
    private SysDepartmentService sysDepartmentService;

    // 对象 判断
    @Autowired
    private TestEmptyUtils testEmptyUtils;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 查询 用户 信息
        SysUser sys_user = sys_userService.Get_Account(username);
        // 如果为空，则返回true，否则返回false。
        if (testEmptyUtils.TestIfTheObjectIsEmpty(sys_user)) {
            throw new UsernameNotFoundException("用户[ " + username + " ]不存在!");
        }else if(sys_user.getStatus().equals(2)){
            throw new UserCountLockException("账号被封禁");
        }
        // 返回 数据
        return new User(sys_user.getAccount(), sys_user.getPassword(), GetUserAuthorities(sys_user.getDepartment()));
    }

    /**
     * 查询 权限
     */
    public List<GrantedAuthority> GetUserAuthorities(String Department) {
        String Permission = sysDepartmentService.Get_Name(Department);
        return AuthorityUtils.commaSeparatedStringToAuthorityList(Permission);
    }
}