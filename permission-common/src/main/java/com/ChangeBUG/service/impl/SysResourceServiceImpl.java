package com.ChangeBUG.service.impl;

import com.ChangeBUG.mapper.SysResourceMapper;
import com.ChangeBUG.model.system.SysResource;
import com.ChangeBUG.model.system.SysUser;
import com.ChangeBUG.service.SysResourceService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements SysResourceService {

    @Autowired
    private SysResourceMapper sysResourceMapper;

    // 获取权限
    @Override
    public List<String> getRolesByUrl(String requestURI) {
        QueryWrapper<SysResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url", requestURI).last("limit 1");
        SysResource sysResource = sysResourceMapper.selectOne(queryWrapper);
        List<String> strings = new ArrayList<>();
        strings.add(sysResource.getPermission());
        return strings;
    }
}