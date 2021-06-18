package com.zpj;


import cn.dev33.satoken.secure.SaSecureUtil;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zpj.config.redisconfig.RedisOperator;
import com.zpj.group_ass.entity.GroupAss;
import com.zpj.group_ass.mapper.GroupAssMapper;
import com.zpj.group_ass.service.GroupAssService;
import com.zpj.group_ass.service.impl.GroupAssServiceImpl;

import com.zpj.role.entity.Role;
import com.zpj.role.mapper.RoleMapper;
import com.zpj.role2.entity.Role2;
import com.zpj.role2.mapper.Role2Mapper;
import com.zpj.role2.service.impl.Role2ServiceImpl;

import com.zpj.rsaaes.RSAEncryptor;
import com.zpj.user.entity.Gift;
import com.zpj.user.entity.User;
import com.zpj.user.mapper.GiftMapper;
import com.zpj.user.mapper.UserMapper;
import com.zpj.user.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    String key = "123456";// 秘钥 aes
    String text = "zpj";
    String str = "VCpOK/nIghIp0Rn2RKQ2bati7xJequ72VN0RDm5q6MwZrjGZEY/HLyZfcA9T/aMz0tLfbb+OMlrG9NBncZCrUUo7Z+ZhKTJ8egfbYzI47KPDbQ0oazXl2+TETelcoztP57eBs0e9Q60mf4cm3Hpnn15bCT01XkRfF7xUht6hoB0=";
    @Autowired
    UserServiceImpl userService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    GroupAssMapper groupAssMapper;
    @Autowired
    GroupAssServiceImpl groupAssService;
    @Autowired
    Role2Mapper role2Mapper;
    @Autowired
    Role2ServiceImpl role2Service;
    @Autowired
    RoleMapper roleMapper;
    @Autowired
    RedisOperator redisOperator;
    @Autowired
    GiftMapper giftMapper;

    @Test
    void rsaTest(){
        String s = RSAEncryptor.rsaEncry(str);
        System.out.println(s);
    }

    @Test
    void contextLoads() {
        String aestext = SaSecureUtil.aesEncrypt(key, text);
        System.out.println("AES加密后：" + aestext); // 4AuHuK0WivLtKI5hDYkrHA==
    }
    @Test
    void test(){
        Gift gift = giftMapper.selectById(1);
        System.out.println(gift);
    }
    @Test
    void test7(){
        User byId = userService.getById(1);
        System.out.println(byId);
    }
    @Test
    void test2(){
        QueryWrapper<GroupAss> wrapper = new QueryWrapper<>();
        QueryWrapper<GroupAss> user_id = wrapper.eq("use_rid", 1);
        GroupAss groupAss = groupAssService.getOne(user_id);
        System.out.println(groupAss);
    }
    @Test
    void test3(){
        Role2 role2 = role2Service.getById(1);
        System.out.println(role2);
    }
    @Test
    void test4(){
        List<String> list = new ArrayList<>();
        QueryWrapper<GroupAss> wrapper = new QueryWrapper<>();
        QueryWrapper<GroupAss> id = wrapper.eq("use_rid", 2);
        GroupAss groupAss = groupAssMapper.selectOne(id);
        Role2 byId = role2Service.getById(groupAss.getGroupId());
        list.add(byId.getGroupid());
        System.out.println(list);

    }
    @Test
    void saveCodeRedisTest(){
        // 拿到手机号
        String phone = "15123010000";
        // 生成key
        StringBuilder builder = new StringBuilder("phone:code:").append(phone);
        //System.out.println(builder.toString());
        // 获取验证码
        String code="1234";
        // 存入redis
        redisOperator.set(builder.toString(),code);
        redisOperator.expire(builder.toString(),60);
    }
    @Test
    void checkCodeRedisTest(){
        String phone = "15123010000";
        String code="1234";
        // 生成key
        StringBuilder builder = new StringBuilder("phone:code:").append(phone);
        String s = redisOperator.get(builder.toString());
        if(code.equals(s)){
            System.out.println("通过验证-------------------------------------------");
        }

    }


}
