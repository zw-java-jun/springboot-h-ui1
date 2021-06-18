package com.zpj.user.controller;

import com.zpj.Result;
import com.zpj.config.redisconfig.RedisOperator;
import com.zpj.user.entity.User;
import com.zpj.user.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

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
            Long aLong = redisOperator.setAdd("1", id);
            System.out.println(aLong);
            return Result.succ("关注成功");
        }else {
            Long remove = redisOperator.removeSet("1",id);
            return Result.succ("删除成功",remove);
        }

    }
    // 查看我关注了哪些人
    @GetMapping("usFocus/{key}")
    public Result usFocus(@PathVariable("key") String key){
        Set members = redisOperator.querySet(key);
        System.out.println(members);
        return Result.succ("成功",members);
    }

    // 返回所有用户
    @GetMapping("allWoman")
    public Result allWoman(){
        List<User> users = userMapper.selectList(null);
        return Result.succ("查询全部成功",users);
    }

    @PostMapping("iosDump")
    public String iosDump(@RequestBody String  rep){
        System.out.println(rep);
        return rep;
    }
    @GetMapping("getById/{id}")
    @Cacheable(key = "'id:'+#id")
    public User getById(@PathVariable("id") Integer id){
        User user = userMapper.selectById(id);
        return user;
    }

}
