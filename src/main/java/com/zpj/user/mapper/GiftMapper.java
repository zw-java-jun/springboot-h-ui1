package com.zpj.user.mapper;

import com.zpj.user.entity.Gift;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zpj
 * @since 2021-06-17
 */
@Repository
@CacheConfig(cacheNames = "giftDao")
public interface GiftMapper extends BaseMapper<Gift> {

}
