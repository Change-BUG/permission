package com.ChangeBUG.service.impl;

import com.ChangeBUG.mapper.SysDepartmentMapper;
import com.ChangeBUG.model.system.SysDepartment;
import com.ChangeBUG.model.system.SysUser;
import com.ChangeBUG.service.SysDepartmentService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class SysDepartmentServiceImpl extends ServiceImpl<SysDepartmentMapper, SysDepartment> implements SysDepartmentService {

    @Autowired
    SysDepartmentMapper sysDepartmentMapper;

    @Override
    public String Get_Name(String account) {
        QueryWrapper<SysDepartment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", account).last("limit 1");
        SysDepartment sysDepartment = sysDepartmentMapper.selectOne(queryWrapper);
        return sysDepartment.getPermission();
    }

}