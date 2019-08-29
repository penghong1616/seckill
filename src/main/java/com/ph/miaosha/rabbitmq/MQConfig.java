package com.ph.miaosha.rabbitmq;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.handler.annotation.Header;

import java.util.HashMap;
import java.util.Map;


@Configuration
public class MQConfig {
    public static final String QUEUE="queue";
    public static final String SEC_KILL_QUEUE="seckillqueue";
    public static final String TOP_QUEUE="top.queue";
    public static final String HEADER_QUEUE="headerQueue";
    public static final String TOP_QUEUE2="top.queue2";
    public static final String TOP_EXCHANGE="topExchange";
    public static final String FANOUT_EXCHANGE="fanoutExchange";
    public static final String HEADER_EXCHANGE="headerExchange";
    public static final String ROUTING_KEY="top.key";
    public static final String ROUTING_KEY2="top.#";

    //Direct模式
    @Bean
    public Queue queue(){
        return new Queue(SEC_KILL_QUEUE,true);
    }
//    //Topic模式
//    @Bean
//    public Queue top_queue(){
//        return new Queue(TOP_QUEUE,true);
//    }
//    @Bean
//    public Queue top_queue2(){
//        return new Queue(TOP_QUEUE2,true);
//    }
//
//
//    @Bean
//    public TopicExchange topicExchange(){
//        return new TopicExchange(TOP_EXCHANGE);
//    }
//    @Bean
//    public Binding topBinding(){
//        return BindingBuilder.bind(top_queue()).to(topicExchange()).with(ROUTING_KEY);
//    }
//    @Bean
//    public Binding topBinding2(){
//        return BindingBuilder.bind(top_queue2()).to(topicExchange()).with(ROUTING_KEY2);
//    }
//    @Bean
//    public FanoutExchange fanoutExchange(){
//        return new FanoutExchange(FANOUT_EXCHANGE);
//    }
//    @Bean
//    public Binding fanoutBInding(){
//        return BindingBuilder.bind(top_queue()).to(fanoutExchange());
//    }
//    @Bean
//    public Binding fanoutBInding2(){
//        return BindingBuilder.bind(top_queue2()).to(fanoutExchange());
//    }
//    @Bean
//    public HeadersExchange headersExchange(){
//        return new HeadersExchange(HEADER_EXCHANGE);
//    }
//    @Bean
//    public Queue headerQueue(){
//        return new Queue(HEADER_QUEUE);
//    }
//    @Bean
//    public Binding headerBinding(){
//        Map<String,Object> map=new HashMap<>();
//        map.put("name","ph");
//        return BindingBuilder.bind(headerQueue()).to(headersExchange()).whereAll(map).match();
//    }
}
