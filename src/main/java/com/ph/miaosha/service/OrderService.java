package com.ph.miaosha.service;

import com.ph.miaosha.dao.OrderDao;
import com.ph.miaosha.domain.OrderInfo;
import com.ph.miaosha.domain.SecKillOrder;
import com.ph.miaosha.domain.User;
import com.ph.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class OrderService {
    @Autowired
    OrderDao orderDao;
    public SecKillOrder getSecKillOrderByUserIdGoodsId(Long userId, long goodsId) {
        return orderDao.getSecKillOrderByUserIdGoodsId(userId,goodsId);
    }
    @Transactional
    public OrderInfo createOrder(User user, GoodsVo goodsVo) {
        OrderInfo orderInfo=new OrderInfo();
        orderInfo.setCreateDate(new Date());
        orderInfo.setDeliveryAddrId(0L);
        orderInfo.setGoodsId(goodsVo.getId());
        orderInfo.setGoodsName(goodsVo.getGoodsName());
        orderInfo.setGoodsPrice(goodsVo.getSeckillPrice());
        orderInfo.setOrderChannel(1);
        orderInfo.setGoodsCount(1);
        orderInfo.setStatus(0);
        orderInfo.setUserId(user.getId());

        long orderId=orderDao.insert(orderInfo);
        SecKillOrder secKillOrder=new SecKillOrder();
        secKillOrder.setGoodsId(goodsVo.getId());
        secKillOrder.setOrderId(orderId);
        secKillOrder.setUserId(user.getId());
        orderDao.insertSecKillOrder(secKillOrder);
        return orderInfo;
    }

    public OrderInfo getOrderInfoByUserIdAndGoodsId(Long userId, Long goodsId) {
        OrderInfo orderInfo=orderDao.getOrderInfoByUserIdAndGoodsId(userId,goodsId);
        return orderInfo;
    }
}
