//package com.zpj.config.stpinterface;
//
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.List;
//
//import cn.dev33.satoken.stp.StpUtil;
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.zpj.group_ass.entity.GroupAss;
//import com.zpj.group_ass.mapper.GroupAssMapper;
//
//import com.zpj.role.mapper.RoleMapper;
//import com.zpj.role2.entity.Role2;
//import com.zpj.role2.service.impl.Role2ServiceImpl;
//import com.zpj.user.service.impl.UserServiceImpl;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import cn.dev33.satoken.stp.StpInterface;
//
///**
// * 自定义权限验证接口扩展
// */
//@Component    // 保证此类被SpringBoot扫描，完成sa-token的自定义权限验证扩展
//public class StpInterfaceImpl implements StpInterface {
//    @Autowired
//    UserServiceImpl userService;
//    @Autowired
//    GroupAssMapper groupAssMapper;
//    @Autowired
//    Role2ServiceImpl role2Service;
//    @Autowired
//    RoleMapper roleMapper;
//    /**
//     * 返回一个账号所拥有的权限码集合
//     */
//    @Override
//    public List<String> getPermissionList(Object loginId, String loginKey) {
//        List<String> list2 = new ArrayList<String>();
//        QueryWrapper<GroupAss> wrapper = new QueryWrapper<>();
//        QueryWrapper<GroupAss> id = wrapper.eq("use_rid", StpUtil.getLoginId());
//        GroupAss groupAss = groupAssMapper.selectOne(id);
//        Role2 role2 = role2Service.getById(groupAss.getGroupId());
//        String str = role2.getGroupname();
//        List list = Arrays.asList(str.split(","));
//        long count = list.stream().count();
//        int i;
//        for (i = 1;i<=count;i++) {
//           list2.add(roleMapper.selectById(i).getName());
//        }
//        return list2;
//    }
//
//
//    /**
//     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
//     */
//    @Override
//    public List<String> getRoleList(Object loginId, String loginKey) {
//        List<String> list = new ArrayList<>();
//        QueryWrapper<GroupAss> wrapper = new QueryWrapper<>();
//        QueryWrapper<GroupAss> id = wrapper.eq("use_rid", StpUtil.getLoginId());
//        GroupAss groupAss = groupAssMapper.selectOne(id);
//        Role2 byId = role2Service.getById(groupAss.getGroupId());
//        list.add(byId.getGroupid());
//
//        return list;
//    }
//
//}