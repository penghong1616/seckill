package com.ph.miaosha.service;

import com.ph.miaosha.domain.Goods;
import com.ph.miaosha.domain.OrderInfo;
import com.ph.miaosha.domain.SecKillOrder;
import com.ph.miaosha.domain.User;
import com.ph.miaosha.redis.RedisService;
import com.ph.miaosha.redis.SecKillKey;
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
    @Autowired
    RedisService redisService;
    @Transactional
    public OrderInfo secKill(User user, GoodsVo goodsVo) {
        //减库存，下订单，写入秒杀订单
        boolean success=goodsService.reduceStock(goodsVo);
        if (success){
            return orderService.createOrder(user,goodsVo);

        }else{
            setGoodsOver(goodsVo.getId());
            return null;
        }
    }



    public Long  getResult(Long id, long goodsId) {
        OrderInfo orderInfo=orderService.getOrderInfoByUserIdAndGoodsId(id,goodsId);
        if (orderInfo!=null){
            return orderInfo.getId();
        }else{
           boolean isOver= getGoodsOver(goodsId);
           if (isOver){
               return -1L;
           }else{
               return 0L;
           }
        }
    }
    //设置该商品已经卖完
    private void setGoodsOver(Long id) {
        redisService.set(SecKillKey.isGoodsOver,""+id,true);
    }
    //该商品是否已经卖完
    private boolean getGoodsOver(long goodsId) {
        return redisService.exists(SecKillKey.isGoodsOver,goodsId+"");
    }

}
