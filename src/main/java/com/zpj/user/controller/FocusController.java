package com.zpj.user.controller;

import com.zpj.Result;
import com.zpj.config.redisconfig.RedisOperator;
import com.zpj.rsaaes.RSAEncryptor;
import com.zpj.user.entity.User;
import com.zpj.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/focus/")
@CacheConfig(cacheNames = "focus")
public class FocusController {
    @Autowired
    RedisOperator redisOperator;
    @Autowired
    UserMapper userMapper;


    // 添加关注人到自己关注列表
    @GetMapping("woman/{id}/{status}")
    public Result focus(@PathVariable("id") Integer id,@PathVariable("status") Integer status){
        if (status==0){
            Long aLong = redisOperator.setAdd("focus:1", id);// id=被关注者id 1=当前登陆者id
            redisOperator.setAdd("fans:bid",1);// id=当前登陆的id  bid=被关注者id
            System.out.println(aLong);
            return Result.succ("关注成功");
        }else {
            Long remove = redisOperator.removeSet("focus:1", id);
            return Result.succ("删除成功",remove);
        }

    }

    // 查看我关注了哪些人
    // 只有登陆状态下才可以查看
    @GetMapping("usFocus")
    public Result usFocus(){
        Set members = redisOperator.querySet("focus:1");
        System.out.println(members);
        return Result.succ("成功",members);
    }
    // 我被谁关注了
    @GetMapping("beFocused")
    public Result beFocused(){
        Set set = redisOperator.querySet("fans:1");
        System.out.println(set);
        return Result.succ("关注我的人有："+set);
    }

    // 返回所有用户
    @GetMapping("allWoman")
    public Result allWoman(){
        List<User> users = userMapper.selectList(null);
        return Result.succ("查询全部成功",users);
    }

    @PostMapping("iosDump")
    public String iosDump(@RequestBody String  rep, ServletRequest servletRequest){
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        String key = request.getHeader("key");
        String s = RSAEncryptor.rsaEncry(key);
        System.out.println(s);
        System.out.println(rep);
        return rep;
    }
    @GetMapping("getById/{id}")
    @Cacheable(key = "'id:'+#id")
    public User getById(@PathVariable("id") Integer id){
        User user = userMapper.selectById(id);
        return user;
    }
    @GetMapping("edit")
    @CacheEvict(key = "'id:'+#id")
    public int editById(User user){
        int i = userMapper.updateById(user);
        return i;
    }

}
