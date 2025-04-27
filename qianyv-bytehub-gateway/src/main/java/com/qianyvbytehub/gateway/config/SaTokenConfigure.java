package com.qianyvbytehub.gateway.config;

import cn.dev33.satoken.context.SaHolder;
import cn.dev33.satoken.reactor.filter.SaReactorFilter;
import cn.dev33.satoken.router.SaRouter;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * [Sa-Token 权限认证] 配置类 
 * @author click33
 */
@Configuration
public class SaTokenConfigure {
    // 注册 Sa-Token全局过滤器 
    @Bean
    public SaReactorFilter getSaReactorFilter() {
        return new SaReactorFilter()
            // 拦截地址 
            .addInclude("/**")    /* 拦截全部path */
            // 开放地址 
            //.addExclude("/favicon.ico")
            // 鉴权方法：每次访问进入 
            .setAuth(obj -> {
                System.out.println("----------前端访问的path："+ SaHolder.getRequest().getRequestPath());
                // 登录校验 -- 拦截所有路由，并排除/user/doLogin 用于开放登录 
                SaRouter.match("/auth/**", "/auth/user/doLogin", r -> StpUtil.checkPermission("subject:add"));

                // 权限认证 -- 不同模块, 校验不同权限
                SaRouter.match("/oss/**",r -> StpUtil.checkLogin());
                SaRouter.match("/subject/subject/add",r -> StpUtil.checkPermission("subject:add"));
                SaRouter.match("/subject/**",r -> StpUtil.checkLogin());
                // 更多匹配 ...  */
            })
            // 异常处理方法：每次setAuth函数出现异常时进入 
            /*.setError(e -> {
                return SaResult.error(e.getMessage());
            })*/;
    }



}
