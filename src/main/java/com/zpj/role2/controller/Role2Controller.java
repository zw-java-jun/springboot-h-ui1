package com.zpj.role2.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cxytiandi.encrypt.springboot.annotation.Encrypt;
import com.zpj.AES;
import com.zpj.role2.entity.Role2;
import com.zpj.role2.mapper.Role2Mapper;
import com.zpj.role2.service.impl.Role2ServiceImpl;
import com.zpj.rsaaes.RSAEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zpj
 * @since 2021-05-31
 */
@RestController
@RequestMapping("/role2/")
public class Role2Controller {
    @Autowired
    Role2ServiceImpl role2Service;
    @Autowired
    Role2Mapper role2Mapper;


    @RequestMapping(value = "add",method = RequestMethod.PUT)
    public Role2 add(@RequestBody Role2 role2, ServletRequest servletRequest) throws Exception{
//        System.out.println("这是原始数据"+role2);
//        String s = AES.aesEncrypt(JSON.toJSONString(role2));
//        System.out.println("这是加密后的"+s);
//        String s1 = AES.aesDecrypt(s);
//        System.out.println("这是解密后的"+s1);
//        JSONObject jsonObject = JSON.parseObject(s1);
//        System.out.println("这是转换成对象的"+jsonObject);
//        Role2 role22 = JSONObject.toJavaObject(JSONObject.parseObject(JSONObject.toJSONString(jsonObject)), Role2.class);
//        role2Mapper.insert(role22);
        System.out.println(role2);
        return role2;
//        HttpServletRequest request = (HttpServletRequest) servletRequest;
//        System.out.println(request.getHeader("key"));
//        System.out.println("这是role2："+role2);
//        String aesKey = request.getHeader("key");
//        String aesKey2 = RSAEncryptor.rsaEncry(aesKey);
//        //return Result.succ(aesKey2);
//        AES.key=aesKey2;
//        String s = AES.aesDecrypt(role2.getGroupname());
//        System.out.println("groupname====="+s);
//        String s1 = AES.aesDecrypt(role2.getGroupid());
//        System.out.println("groupid====="+s1);
//        JSONObject jsonObject = JSON.parseObject(s1);
//        System.out.println(jsonObject);
//        return role2;
//        Role2 role22 = JSONObject.toJavaObject(JSONObject.parseObject(JSONObject.toJSONString(jsonObject)), Role2.class);
//        int insert = role2Mapper.insert(role22);
//        if(insert==1){
//            return Result.succ("添加成功");
//        }else {
//            return Result.succ("添加失败");
//        }
//        Map<String, String> map = new HashMap<>();
//        map.put("参数信息", String.valueOf(role2));
//        map.put("aes秘钥",request.getHeader("key"));
//        return map;
       // return Result.succ("sss");
    }
    @RequestMapping(value = "delete/{id}" ,method = RequestMethod.DELETE)
    public boolean delRole(@PathVariable("id") Long id){
        boolean b = role2Service.removeById(id);
        return b;
    }
    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    public boolean updateRole(Role2 role2){
        boolean save = role2Service.saveOrUpdate(role2);
        return save;
    }
    @RequestMapping(value = "selectById/{id}" ,method = RequestMethod.GET)
    public Role2 selectById(@PathVariable("id") Long id){
        Role2 role2 = role2Service.getById(id);
        return role2;
    }
    @Encrypt
    @RequestMapping(value = "select" ,method = RequestMethod.GET)
    public List<Role2> select(){
        List<Role2> roles = role2Mapper.selectList(null);
        return roles;
    }

}

