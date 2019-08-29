package com.ph.miaosha.controller;

import com.ph.miaosha.rabbitmq.MQReceiver;
import com.ph.miaosha.rabbitmq.MQSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class DemoController {
//    @Autowired
//    MQSender mqSender;
//    @RequestMapping("/amqp")
//    @ResponseBody
//    public String amqp(){
//        mqSender.send("Hello");
//        return "OK";
//    }
//    @RequestMapping("/amqp/top")
//    @ResponseBody
//    public String amqpTopic(){
//        mqSender.sendTopic("top");
//        return "OK";
//    }
//    @RequestMapping("/amqp/fanout")
//    @ResponseBody
//    public String amqpFanout(){
//        mqSender.sendFanout("fanout");
//        return "OK";
//    }
//    @RequestMapping("/amqp/header")
//    @ResponseBody
//    public String amqpheader(){
//        mqSender.sendHeader("header");
//        return "OK";
//    }

}
