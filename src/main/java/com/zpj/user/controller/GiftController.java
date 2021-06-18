package com.zpj.user.controller;


import com.zpj.Result;
import com.zpj.config.redisconfig.RedisOperator;
import com.zpj.user.entity.Gift;
import com.zpj.user.mapper.GiftMapper;
import com.zpj.user.service.impl.GiftServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author zpj
 * @since 2021-06-17
 */
@RestController
@RequestMapping("/gift/")
public class GiftController {

    @Autowired
    GiftMapper giftMapper;
    @Autowired
    RedisOperator redisOperator;
    @Autowired
    GiftServiceImpl giftService;
    // 送礼物消费豆豆
    @GetMapping("giveGift/{doug}")
    public Result giveGift(@PathVariable("doug") Integer doug){
        // 获取当前登陆账户
        // 送礼减豆豆
        Gift gift = giftMapper.selectById(1);
        Integer doug1 = gift.getDoug();
        int s=doug1-doug;
        gift.setDoug(s);
        giftMapper.updateById(gift);
        return Result.succ("送出了"+doug+"豆豆",gift);
    }




}

