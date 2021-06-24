package com.zpj.user.controller;

import cn.dev33.satoken.session.SaSession;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zpj.Result;
import com.zpj.config.redisconfig.RedisOperator;
//import com.zpj.config.stpinterface.StpInterfaceImpl;
import com.zpj.user.entity.User;
import com.zpj.user.service.impl.UserServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.print.attribute.standard.JobOriginatingUserName;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Api
@RestController
//@SaCheckLogin
@RequestMapping("/user")
public class UserController {
    String rsaKey = "GteNFUBr5HXtm/ZE5B91U+bUyWeoAdUTiYOIYQxjK9Yzvt6+k0gPRglBEgsqOUj7f0EQTrVuN5cUq9UK75lkQFyyaS7oEyIgwreSInTCLlWugGlV8UJwwh9jWjiPjShxPSJJ/UNq6R/lNECEkQj6cw1xK3Pkc5+gklpRFMZNcK0=";
    String KEY = "1234567887654321";
    @Autowired
    UserServiceImpl userService;
    @Autowired
    RedisOperator redisOperator;
//    @Autowired
//    StpInterfaceImpl stpInterface;


    @PostMapping("/setRedisCode")
    @ApiOperation("请求验证码")
    public String setRedisCode(String phone){
        // 生成key
        StringBuilder redisKey = new StringBuilder("phone:code:").append(phone);
        // 生成验证码
        String str = "1234567890";
        String strId = new String();
        for (int i = 0; i < 4; i++) {
            char c = str.charAt(new Random().nextInt(str.length()));
            strId += c;
        }
        // 存入redis
        redisOperator.set(redisKey.toString(),strId,60);
        return "验证码发送成功"+strId;
    }
    @PostMapping("/checkCode")
    @ApiOperation("验证验证码")
    public String checkCode(String phone,String code){
        // 生成key
        StringBuilder builder = new StringBuilder("phone:code:").append(phone);
        // 获取key查询
        String s = redisOperator.get(builder.toString());
        if(code.equals(s)){
            return "验证通过";
        }else{
            return "验证不通过";
        }
    }
    @PostMapping("/login")
    @ApiOperation("登陆")
    public Result login(@RequestBody Map<String,String> usermes) throws Exception {

        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("username",usermes.get("username"))
                .eq("password",usermes.get("password"));
        User user = userService.getOne(wrapper);
        System.out.println("这是wrapper"+wrapper);
        if(user != null){
            StpUtil.setLoginId(user.getId());
            String token = StpUtil.getTokenValue();
            redisOperator.set("token",token);
            return Result.succ("登陆成功",user);
        }else{
            return Result.fail("账号密码错误");
        }

    }
    @GetMapping("/getToken")
    @ApiOperation("查看key过期时间")
    public Long gettoken(String token){
        long ttl = redisOperator.ttl(token);
        if(ttl<0){
            StpUtil.logoutByLoginId(StpUtil.getLoginId());
        }
        return ttl;
    }
    // 查看是否登陆
    @GetMapping("/redisToken")
    @ApiOperation("查看是否登陆")
    public String isLogin(String token){
        String token2 = redisOperator.get(token);
        if(token2 != null){
            return "当前是否登陆"+StpUtil.isLogin();
        }else {
            return "token过期,请重新登陆";
        }

    }
    // 查看登录的id
    @ApiOperation("查看登陆的id")
    @PostMapping("/getloginid")
    public String getLogin(){
        return "当前登陆的用户id："+StpUtil.getLoginId();
    }
    // 注销登陆的id
    @ApiOperation("注销指定id")
    @GetMapping("/dielogin")
    public String dieLogin(Long id){
        StpUtil.logoutByLoginId(StpUtil.getLoginId(id));
        return "注销成功";
    }
    // 查看是否拥有权限
    @ApiOperation("查看是否拥有权限")
    @GetMapping("/useredit")
    public boolean useredit(){
        return StpUtil.hasPermission("add-build");
    }
    // 查看session
    @ApiOperation("查看session")
    @GetMapping("/getseeion")
    public SaSession getSession(){
        SaSession session = StpUtil.getSessionByLoginId(StpUtil.getLoginId());
        return session;
    }
    // 查看登陆的token的专属session
    @ApiOperation("查看登陆的token的专属session")
    @GetMapping("/gettokensession")
    public SaSession getSessionToken(){
        SaSession tokenSessionByToken = StpUtil.getTokenSessionByToken(StpUtil.getTokenValue());
        return tokenSessionByToken;
    }
    // 踢人下线 该用户再访问时会报异常
    @ApiOperation("踢人下线")
    @GetMapping("/loginoutbyid/{id}")
    public void loginOutById(@PathVariable("id") Long id){
        StpUtil.logoutByLoginId(id);
    }
    // 账号封禁
    @ApiOperation("账号封禁")
    @GetMapping("/disable/{id}")
    public boolean disableById(@PathVariable("id") Long id){
        StpUtil.disable(id,60);
        return StpUtil.isDisable(id);
    }
    // 注解校验权限
    @ApiOperation("校验是否拥有user中的权限")
    //@SaCheckPermission(value = {"user-add","user-update","user-delete"},mode = SaMode.OR)
    @GetMapping("/select/{id}")
    public boolean selectById(){
       return StpUtil.hasRole("adminsad");
    }
}
