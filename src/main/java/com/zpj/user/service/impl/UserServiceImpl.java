package com.zpj.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.zpj.user.entity.User;
import com.zpj.user.mapper.UserMapper;
import com.zpj.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zpj
 * @since 2021-05-27
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
