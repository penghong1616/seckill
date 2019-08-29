package com.ph.miaosha.rabbitmq;

import com.ph.miaosha.domain.Goods;
import com.ph.miaosha.domain.OrderInfo;
import com.ph.miaosha.domain.User;
import com.ph.miaosha.redis.RedisService;
import com.ph.miaosha.service.GoodsService;
import com.ph.miaosha.service.OrderService;
import com.ph.miaosha.service.SecKillService;
import com.ph.miaosha.vo.GoodsVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQReceiver {
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Autowired
    SecKillService secKillService;
    private static Logger logger= LoggerFactory.getLogger(MQReceiver.class);
    @RabbitListener(queues = MQConfig.SEC_KILL_QUEUE)
    public void receive(String message){
        logger.info("receive msg:"+message);
        SecKillMessage secKillMessage= RedisService.stringToBean(message,SecKillMessage.class);
        User user=secKillMessage.getUser();
        Long goodsId=secKillMessage.getGoodsId();
        GoodsVo goods= goodsService.getGoodsVoByGoodsId(goodsId);
        int stock=goods.getGoodsStock();
        if (stock<=0){
            return ;
        }
        //判断是否重复秒杀
        OrderInfo orderInfo=orderService.getOrderInfoByUserIdAndGoodsId(user.getId(),goodsId);
        if (orderInfo!=null)
            return ;
        //生成秒杀订单
        secKillService.secKill(user,goods);
    }
//    @RabbitListener(queues = MQConfig.TOP_QUEUE)
//    public void receiveTop(String message){
//        logger.info("topic queue msg:"+message);
//    }
//    @RabbitListener(queues = MQConfig.TOP_QUEUE2)
//    public void receiveTop2(String message){
//        logger.info("topic queue2 msg:"+message);
//    }
//    @RabbitListener(queues = MQConfig.HEADER_QUEUE)
//    public void receiveHeader(byte[] message){
//        logger.info("header queue msg:"+new String(message));
//    }
}
