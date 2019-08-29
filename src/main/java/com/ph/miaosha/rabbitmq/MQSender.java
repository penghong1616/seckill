package com.ph.miaosha.rabbitmq;

import com.ph.miaosha.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MQSender {
    private static Logger logger= LoggerFactory.getLogger(MQSender.class);
    @Autowired
    AmqpTemplate amqpTemplate;

    public void sendSecKillMessage(SecKillMessage secKillMessage) {
        String msg=RedisService.beanToString(secKillMessage);
        logger.info("send message:"+msg);
        amqpTemplate.convertAndSend(MQConfig.SEC_KILL_QUEUE,msg);
    }
//    public void send(Object object){
//        String msg= RedisService.beanToString(object);
//        amqpTemplate.convertAndSend(MQConfig.QUEUE,msg);
//        logger.info("send message:"+msg);
//    }
//    public void sendTopic(Object object){
//        String msg= RedisService.beanToString(object);
//        amqpTemplate.convertAndSend(MQConfig.TOP_EXCHANGE,MQConfig.ROUTING_KEY,msg+1);
//        amqpTemplate.convertAndSend(MQConfig.TOP_EXCHANGE,MQConfig.ROUTING_KEY2,msg+2);
//        logger.info("send Topic message:"+msg);
//    }
//    public void sendFanout(Object object){
//        String msg= RedisService.beanToString(object);
//        amqpTemplate.convertAndSend(MQConfig.FANOUT_EXCHANGE,"",msg+1);
//        logger.info("send Topic message:"+msg);
//    }
//    public void sendHeader(Object object){
//        String msg= RedisService.beanToString(object);
//        MessageProperties properties=new MessageProperties();
//        properties.setHeader("name","ph");
//        Message obj=new Message(msg.getBytes(),properties);
//        amqpTemplate.convertAndSend(MQConfig.HEADER_EXCHANGE,"",obj);
//        logger.info("send Topic message:"+msg);
//    }


}
