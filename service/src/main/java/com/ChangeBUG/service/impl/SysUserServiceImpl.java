package com.ChangeBUG.service.impl;

import com.ChangeBUG.mapper.SysUserMapper;
import com.ChangeBUG.model.system.SysUser;
import com.ChangeBUG.model.utils.UserLogin;
import com.ChangeBUG.service.SysUserService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public String Login(UserLogin userLogin) {
        return null;
    }

    @Override
    public Boolean register(SysUser sysUser,Integer _id, Boolean WhetherToRegister) {

        Boolean zhi = null;
        if(WhetherToRegister){
            sysUser.setAvatar("123.png");
            sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
            sysUser.setIntroduction("你好用户！！！");
            sysUser.setDepartment("普通用户");
            sysUser.setStatus(1);
            sysUser.setAdd_time(new Date());
            sysUser.setUpd_time(new Date());
            zhi = sysUserMapper.insert(sysUser) == 1;
        }else{
            sysUser.set_id(_id);
            sysUser.setAvatar("456.png");
            sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
            sysUser.setIntroduction("你好用户！！！");
            sysUser.setDepartment("普通用户");
            sysUser.setStatus(1);
            sysUser.setAdd_time(new Date());
            sysUser.setUpd_time(new Date());
            zhi = sysUserMapper.updateById(sysUser) == 1;
        }
        return zhi;
    }

    @Override
    public Boolean logout() {
        return null;
    }

    @Override
    public Boolean revise(SysUser sysUser) {
        return sysUserMapper.updateById(sysUser) == 1;
    }

    @Override
    public SysUser Get_Account(String account) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("account", account).last("limit 1");
        return sysUserMapper.selectOne(queryWrapper);
    }
}