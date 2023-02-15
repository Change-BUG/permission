package com.ChangeBUG.service.impl;

import com.ChangeBUG.mapper.SysResourceMapper;
import com.ChangeBUG.model.system.SysResource;
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

    // 获取 接口的权限
    @Override
    public List<String> getRolesByUrl(String requestURI){
        QueryWrapper<SysResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("url", requestURI).last("limit 1");
        SysResource sysResource = sysResourceMapper.selectOne(queryWrapper);
        List<String> strings = new ArrayList<>();
        // 预防 接口 没有 录入 系统 出现的 错误
        try {
            strings.add(sysResource.getPermission());
        }catch (Exception e){
            throw new NullPointerException("权限异常");
        }
        return strings;
    }
}