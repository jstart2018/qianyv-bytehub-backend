package com.qianyvbytehub.auth.application.controller.controller;


import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.google.common.base.Preconditions;
import com.qianyvbytehub.auth.application.controller.convert.AuthUserDTOConverter;
import com.qianyvbytehub.auth.application.controller.entity.AuthUserDTO;
import com.qianyvbytehub.auth.common.entity.Result;
import com.qianyvbytehub.auth.common.enums.UseStateEnum;
import com.qianyvbytehub.auth.domain.entity.AuthUserBo;
import com.qianyvbytehub.auth.domain.service.AuthUserDomainService;
import com.qianyvbytehub.auth.domain.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/auth/user")
public class UserController {

    @Resource
    private AuthUserDomainService authUserDomainService;

    @Autowired
    private RedisUtil redisUtil;


    /**
     * 新增用户
     * @param authUserDTO
     * @return
     */
    @PostMapping("/register")
    public Result register(@RequestBody AuthUserDTO authUserDTO) {

        try {
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()),"账号不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getNickName()),"昵称不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getPassword()),"密码不能为空");
            Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getEmail()),"邮箱不能为空");

            AuthUserBo authUserBo = AuthUserDTOConverter
                    .INSTANCE.ConvertUserDtoToBo(authUserDTO);
            Boolean flag = authUserDomainService.regist(authUserBo);
            if (flag) {
                return Result.ok("注册成功");
            }else{
                throw new RuntimeException("service层及以下导致注册失败");
            }
        } catch (Exception e) {
            log.error(e.getMessage(),e);
            return Result.fail("注册失败");
        }
    }

    /**
     * 更新用户
     * @param authUserDTO
     * @return
     */
    @DeleteMapping("/update")
    public Result update(@RequestBody AuthUserDTO authUserDTO) {
        try {
            Preconditions.checkNotNull(authUserDTO.getId(),"主键不能为空");
            Preconditions.checkArgument
                    (!StringUtils.isBlank(authUserDTO.getUserName()),"账号不能为空");
            AuthUserBo authUserBo = AuthUserDTOConverter
                    .INSTANCE.ConvertUserDtoToBo(authUserDTO);
            Boolean flag = authUserDomainService.update(authUserBo);
            if (flag) {
                return Result.ok("更新成功");
            }else{
                throw new RuntimeException("service层及以下导致注册失败");
            }
        } catch (RuntimeException e) {
            log.error(e.getMessage(),e);
            return Result.fail("更新用户数据失败");
        }


    }

    /**
     * 删除用户
     * @param authUserDTO
     * @return
     */
    @DeleteMapping("/delete")
    public Result delete(@RequestBody AuthUserDTO authUserDTO) {
        try {
            Preconditions.checkNotNull(authUserDTO.getId(), "主键不能为空");
            Preconditions.checkArgument
                    (!StringUtils.isBlank(authUserDTO.getUserName()), "账号不能为空");
            authUserDTO.setIsDeleted(1);
            AuthUserBo authUserBo = AuthUserDTOConverter
                    .INSTANCE.ConvertUserDtoToBo(authUserDTO);
            Boolean flag = authUserDomainService.update(authUserBo);
            if (flag) {
                return Result.ok("删除成功");
            } else {
                throw new RuntimeException("service层及以下导致注册失败");
            }
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            return Result.fail("删除 失败");
        }
    }

    @PostMapping("/updateStatus")
    public Result updateStatus(@RequestBody AuthUserDTO authUserDTO) {
        try {
            Preconditions.checkNotNull(authUserDTO.getId(), "主键不能为空");
            Preconditions.checkArgument
                    (!StringUtils.isBlank(authUserDTO.getUserName()), "账号不能为空");
            Preconditions.checkNotNull(authUserDTO.getStatus(), "用户状态不能为空");
            if (authUserDTO.getStatus() != UseStateEnum.ENABLE.getCode()
                    && authUserDTO.getStatus() != UseStateEnum.DISABLE.getCode()) {
                return Result.fail("用户状态参数错误");
            }
            AuthUserBo authUserBo = AuthUserDTOConverter
                    .INSTANCE.ConvertUserDtoToBo(authUserDTO);
            Boolean flag = authUserDomainService.update(authUserBo);
            if (flag) {
                return Result.ok("更新用户状态成功");
            } else {
                throw new RuntimeException("service层及以下导致注册失败");
            }
        } catch (RuntimeException e) {
            log.error(e.getMessage(), e);
            return Result.fail("删除 失败");
        }
    }

    /**
     * 查看用户个人信息
     * @param
     * @return
     */
    @PostMapping("/getUserInfo")
    public Result<AuthUserDTO> getUserInfo(@RequestBody AuthUserDTO authUserDTO) {

        if (log.isInfoEnabled()){
            log.info("auth.user.getUserInfo用户参数：{}", JSON.toJSONString(authUserDTO));
        }

        Preconditions.checkArgument(!StringUtils.isBlank(authUserDTO.getUserName()),
                "用户userName不能为空");

        AuthUserBo authUserBo = AuthUserDTOConverter
                .INSTANCE.ConvertUserDtoToBo(authUserDTO);
        AuthUserBo authUserBoResult = authUserDomainService.getUserInfo(authUserBo);

        if (authUserBoResult != null) {
            AuthUserDTO authUserDTOResult = AuthUserDTOConverter
                    .INSTANCE.ConvertUserBoToDto(authUserBoResult);
            return Result.ok(authUserDTOResult);

        }
        log.error("查询异常");
        return Result.fail();

    }


    // 测试登录，浏览器访问： http://localhost:8081/user/doLogin
    @GetMapping("/doLogin")
    public Result doLogin(@RequestParam("code") String code) {

        try {
            //根据验证码从redis中获取用户openId
            Object o = redisUtil.get(code);
            if (o == null) {
                return Result.fail("验证码错误或已过期");
            }
            String openId = o.toString();

            Boolean result = authUserDomainService.dologin(openId);
            if (result) {
                StpUtil.login(openId);
                SaTokenInfo tokenInfo = StpUtil.getTokenInfo();
                if (tokenInfo.getTokenValue() != null) {
                    return Result.ok(tokenInfo);
                }
            }
            return Result.fail("登录失败");
        } catch (Exception e) {
            log.error("登录异常:{}",e.getMessage(),e);
            return Result.fail("登录异常，请稍后再试");
        }
    }

    @PostMapping("/logOut")
    public Result logOut(@RequestBody AuthUserDTO authUserDTO) {

        try {
            String userName = authUserDTO.getUserName();

            Preconditions.checkArgument(!StringUtils.isBlank(userName),
                    "用户userName不能为空");

            StpUtil.logout(userName);
            return Result.ok();
        } catch (Exception e) {
            log.error("登出失败",e.getMessage(),e);
            return Result.fail("登出异常");
        }
    }

    // 查询登录状态，浏览器访问： http://localhost:3011/user/isLogin
    @GetMapping("/isLogin")
    public String isLogin() {
        StpUtil.checkLogin();
        boolean login = StpUtil.isLogin();
        return "当前会话是否登录：" + login;
    }
    
}
