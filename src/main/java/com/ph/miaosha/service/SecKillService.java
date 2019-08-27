package com.ph.miaosha.service;

import com.ph.miaosha.domain.Goods;
import com.ph.miaosha.domain.OrderInfo;
import com.ph.miaosha.domain.SecKillOrder;
import com.ph.miaosha.domain.User;
import com.ph.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SecKillService {
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Transactional
    public OrderInfo secKill(User user, GoodsVo goodsVo) {
        //减库存，下订单，写入秒杀订单
        goodsService.reduceStock(goodsVo);

        OrderInfo orderInfo=orderService.createOrder(user,goodsVo);
        return orderInfo;
    }
}
