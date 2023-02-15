package com.ChangeBUG.service;

import com.ChangeBUG.model.system.SysResource;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

public interface SysResourceService extends IService<SysResource> {

    List<String> getRolesByUrl(String requestURI);

}