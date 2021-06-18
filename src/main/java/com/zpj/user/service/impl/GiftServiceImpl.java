package com.zpj.user.service.impl;

import com.zpj.user.entity.Gift;
import com.zpj.user.mapper.GiftMapper;
import com.zpj.user.service.GiftService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zpj
 * @since 2021-06-17
 */
@Service
//@CacheConfig(cacheNames = "gift")
public class GiftServiceImpl extends ServiceImpl<GiftMapper, Gift> implements GiftService {
//    @Autowired
//    GiftMapper giftMapper;
//    @Cacheable(key = "'id:'+#id")
//    public Gift getId(Serializable id) {
//        return giftMapper.selectById(id);
//    }
}
