package com.ph.miaosha.vo;

import com.ph.miaosha.domain.Goods;
import com.ph.miaosha.domain.OrderInfo;
import org.springframework.core.annotation.Order;

public class GoodsOrderVo {
    private Goods goods;
    private OrderInfo orderInfo;

    public Goods getGoods() {
        return goods;
    }

    public void setGoods(Goods goods) {
        this.goods = goods;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }
}
