package com.ChangeBUG.service;

import com.ChangeBUG.model.system.SysDepartment;
import com.ChangeBUG.model.system.SysUser;
import com.ChangeBUG.model.utils.UserLogin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.sun.org.apache.xpath.internal.operations.Bool;

public interface SysUserService extends IService<SysUser> {

    /**
     * 登录
     */
    String Login(UserLogin userLogin);

    /**
     * 注册 账号
     */
    Boolean register(SysUser sysUser,Integer _id,Boolean WhetherToRegister);

    /**
     * 注销 账号
     */
    Boolean logout();

    /**
     * 修改 账号
     */
    Boolean revise(SysUser sysUser);

    /**
     * 通过 账号 查询
     */
    SysUser Get_Account(String account);

}
