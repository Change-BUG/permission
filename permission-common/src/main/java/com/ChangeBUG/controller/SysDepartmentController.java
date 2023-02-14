package com.ChangeBUG.controller;

import com.ChangeBUG.mapper.SysDepartmentMapper;
import com.ChangeBUG.model.system.SysDepartment;
import com.ChangeBUG.model.system.SysUser;
import com.ChangeBUG.model.utils.AllID;
import com.ChangeBUG.service.SysDepartmentService;
import com.ChangeBUG.utils.RespListUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Api(tags = "部门操作")
@RestController
@RequestMapping(value = "/api/v1/sysDepartment")
public class SysDepartmentController {

    @Autowired
    private SysDepartmentService Service;

    @ResponseBody
    @ApiOperation(value = "添加数据")
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RespListUtils add(@RequestBody SysDepartment data) {
        data.set_id(null);
        data.setAdd_time(new Date());
        data.setUpd_time(new Date());
        data.setStatus(1);
        return Service.save(data) ?  RespListUtils.success("添加成功") : RespListUtils.error("添加失败");
    }

    @ResponseBody
    @ApiOperation(value = "删除数据")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public RespListUtils del(@RequestBody AllID allID) {
        SysDepartment sysDepartment = new SysDepartment();
        sysDepartment.set_id(Integer.valueOf(allID.get_id()));
        sysDepartment.setStatus(3);
        return Service.updateById(sysDepartment) ? RespListUtils.success("修改成功")  :  RespListUtils.error("修改失败");
    }

    @ResponseBody
    @ApiOperation(value = "修改数据")
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    public RespListUtils upd(@RequestBody SysDepartment data) {
        data.setUpd_time(new Date());
        return Service.updateById(data) ? RespListUtils.success("修改成功")  :  RespListUtils.error("修改失败");
    }

    @ResponseBody
    @ApiOperation(value = "查询全部")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public RespListUtils list() {
        QueryWrapper<SysDepartment> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        return RespListUtils.success("查询成功", Service.list(queryWrapper));
    }

}