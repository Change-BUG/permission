package com.ChangeBUG.service;

import com.ChangeBUG.model.system.SysDepartment;
import com.ChangeBUG.model.system.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.security.core.GrantedAuthority;

public interface SysDepartmentService extends IService<SysDepartment> {

    /**
     * 通过 账号 查询 部门 权限
     */
    String Get_Name(String account);

}
