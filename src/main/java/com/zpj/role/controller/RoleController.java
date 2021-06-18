package com.zpj.role.controller;


import com.zpj.role.entity.Role;
import com.zpj.role.mapper.RoleMapper;
import com.zpj.role.service.impl.RoleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/role/")
public class RoleController {
    @Autowired
    RoleServiceImpl roleService;
    @Autowired
    RoleMapper roleMapper;

    @RequestMapping(value = "add" ,method = RequestMethod.PUT)
    public boolean addRole(Role role){
        boolean save = roleService.save(role);
        return save;
    }
    @RequestMapping(value = "delete/{id}" ,method = RequestMethod.DELETE)
    public boolean delRole(@PathVariable("id") Long id){
        boolean b = roleService.removeById(id);
        return b;
    }
    @RequestMapping(value = "update" ,method = RequestMethod.POST)
    public boolean updateRole(Role role){
        boolean save = roleService.saveOrUpdate(role);
        return save;
    }
    @RequestMapping(value = "selectById/{id}" ,method = RequestMethod.GET)
    public Role selectById(@PathVariable("id") Long id){
        Role role = roleService.getById(id);
        return role;
    }
    @RequestMapping(value = "select" ,method = RequestMethod.GET)
    public List<Role> select(){
        List<Role> roles = roleMapper.selectList(null);
        return roles;
    }
}

