//package com.zpj.config.stpinterface;
//
//import cn.dev33.satoken.interceptor.SaRouteInterceptor;
//import cn.dev33.satoken.router.SaRouterUtil;
//import cn.dev33.satoken.stp.StpUtil;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//import java.util.Arrays;
//
//@Configuration
//public class SaTokenConfigure implements WebMvcConfigurer {
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        // 注册路由拦截器，自定义验证规则
//        registry.addInterceptor(new SaRouteInterceptor((request, response, handler)->{
//            // 登录验证 -- 拦截所有路由，并排除/user/doLogin 用于开放登录
//            SaRouterUtil.match(Arrays.asList("/**"), Arrays.asList("/user/login","/swagger-ui.html/**","/swagger-resources/**","/webjars/**","/role2/**"), () -> StpUtil.checkLogin());
//
//            // 角色认证 -- 拦截以 user 开头的路由，必须具备[admin]角色或者[noradmin]角色才可以通过认证
//            //SaRouterUtil.match("/user/**", "/user/login",() -> StpUtil.checkRoleOr("admin","noradmin"));
//
//            // 根据路由划分模块，不同模块不同鉴权
//            SaRouterUtil.match("/user/**", () -> StpUtil.checkPermission("user-add"));
//        })).addPathPatterns("/**").excludePathPatterns("/user/login");
//    }
//}