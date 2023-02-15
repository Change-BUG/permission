package com.ChangeBUG.controller;

import com.ChangeBUG.model.system.SysUser;
import com.ChangeBUG.model.utils.AllID;
import com.ChangeBUG.model.utils.UserLogin;
import com.ChangeBUG.service.SysUserService;
import com.ChangeBUG.utils.JwtUtil;
import com.ChangeBUG.utils.RespListUtils;
import com.ChangeBUG.utils.TestEmptyUtils;
import com.ChangeBUG.utils.UserVerifyUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Api(tags = "用户操作")
@RestController
@RequestMapping(value = "/api/v1/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    // JWT 工具
    @Autowired
    private UserVerifyUtils allUserVerify;

    // Token 工具
    @Autowired
    private JwtUtil jwtTokenUtils;

    // 对象 判断
    @Autowired
    private TestEmptyUtils testEmptyUtils;

    // JWT 存储的请求头
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;

    // JWT 负载中拿到开头
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    private UserDetailsService userDetailsService;

    @ResponseBody
    @ApiOperation(value = "用户登录")
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public RespListUtils login(@RequestBody UserLogin userLogin, HttpServletRequest request, HttpServletResponse response) {

        // 验证 账号 是否 为空
        if (userLogin.getAccount().equals("") && userLogin.getPassword().equals("")) {
            return RespListUtils.error("请输入完整");
        }
        // 验证 账号 是否 正确
        UserDetails userDetails = userDetailsService.loadUserByUsername(userLogin.getAccount());
        if (userDetails == null) {
            return RespListUtils.error("账号不正确");
        }
        if (!new BCryptPasswordEncoder().matches(userLogin.getPassword(), userDetails.getPassword())) {
            return RespListUtils.error("密码不正确");
        }

        // 更新 Security 内部容器
        UsernamePasswordAuthenticationToken authenticationToken = allUserVerify.renewSecurityUser(userDetails);
        // 制定 Token
        String token = allUserVerify.judgment_Token(authenticationToken, userDetails);
        // 获取用户数据
        // SysUser sysUser = sysUserService.Get_Account(userLogin.getAccount());
        return RespListUtils.success("登录成功", token);
    }

    @ResponseBody
    @ApiOperation(value = "用户注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RespListUtils register(@RequestBody SysUser sysUser, HttpServletRequest request, HttpServletResponse response) {
        SysUser sysUser1 = sysUserService.Get_Account(sysUser.getAccount());
        // 判断 账号是否 存在
        if (!testEmptyUtils.TestIfTheObjectIsEmpty(sysUser1) && sysUser1.getStatus() != 3) {
            return RespListUtils.success("账号存在");
        }
        // 注册 存入 用户 数据
        if (!sysUserService.register(sysUser, sysUser1.get_id(),testEmptyUtils.TestIfTheObjectIsEmpty(sysUser1))) {
            return RespListUtils.success("注册失败");
        }
        // 验证 账号 是否 正确
        UserDetails userDetails = userDetailsService.loadUserByUsername(sysUser.getAccount());
        // 更新 Security 内部容器
        UsernamePasswordAuthenticationToken authenticationToken = allUserVerify.renewSecurityUser(userDetails);
        // 制定 Token
        String token = allUserVerify.judgment_Token(authenticationToken, userDetails);
        // 获取用户数据
        // SysUser sysUser = sysUserService.Get_Account(userLogin.getAccount());
        return RespListUtils.success("注册成功", token);
    }

    @ResponseBody
    @ApiOperation(value = "用户退出")
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public RespListUtils logout(HttpServletRequest request, HttpServletResponse response) {
        return RespListUtils.success("退出成功");
    }

    @ResponseBody
    @ApiOperation(value = "用户信息")
    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public RespListUtils getUser(HttpServletRequest request, HttpServletResponse response) {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        // 取 token ====================================================================================================
        String token = request.getHeader(tokenHeader);
        // 分割 token 得到 后缀
        String authToken = token.substring(tokenHead.length());
        // 从 token 获取 账号
        String username = jwtTokenUtils.get_Token_User(authToken);
        // 查询 信息
        SysUser sysUser = sysUserService.Get_Account(username);
        // 返回数据
        return RespListUtils.success("查询成功", sysUser);
    }

    @ResponseBody
    @ApiOperation(value = "用户注销")
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    public RespListUtils del(@RequestBody AllID allID) {
        SysUser sysUser = new SysUser();
        sysUser.set_id(Integer.valueOf(allID.get_id()));
        sysUser.setStatus(3);
        return sysUserService.updateById(sysUser) ? RespListUtils.success("注销成功") : RespListUtils.error("注销失败");
    }

    @ResponseBody
    @ApiOperation(value = "查询全部")
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public RespListUtils list() {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("status", 1);
        return RespListUtils.success("查询成功", sysUserService.list(queryWrapper));
    }

    @ResponseBody
    @ApiOperation(value = "用户修改")
    @RequestMapping(value = "/upd", method = RequestMethod.POST)
    public RespListUtils upd(@RequestBody SysUser sysUser) {
        sysUser.setUpd_time(new Date());
        return sysUserService.updateById(sysUser)? RespListUtils.success("修改成功") :  RespListUtils.error("修改失败");
    }
}