package com.ChangeBUG.service;

import com.ChangeBUG.model.system.SysDepartment;
import com.baomidou.mybatisplus.extension.service.IService;

public interface SysDepartmentService extends IService<SysDepartment> {

    /**
     * 通过 账号 查询 部门 权限
     */
    String Get_Name(String account);

}
