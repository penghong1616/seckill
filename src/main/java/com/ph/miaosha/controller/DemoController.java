package com.ph.miaosha.controller;

import com.ph.miaosha.domain.User;
import com.ph.miaosha.redis.RedisService;
import com.ph.miaosha.redis.UserKey;
import com.ph.miaosha.result.CodeMsg;
import com.ph.miaosha.result.Result;
import com.ph.miaosha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;



public class DemoController {
//    @Autowired
//    UserService userService;
//    @Autowired
//    RedisService redisService;
//    //1 rest JSON输出 2页面
//    public  String hello(){
//        return "Hello,SpringBoot" ;
//    }
//    @RequestMapping("/success")
//   public  Result<String> sayHello(){
//        return new Result<>("Hello,Ph");
//    }
//    @RequestMapping("/servererror")
//    public Result error(){
//        return new Result(CodeMsg.SERVER_ERROR);
//    }
//    @RequestMapping("/thymeleaf")
//    public String thymeleaf(){
//        ModelAndView modelAndView=new ModelAndView();
//        modelAndView.addObject("name","ph");
//
//        return "hello";
//    }
//    @RequestMapping("/user")
//    public Result<User> getUser(){
//        User user=userService.getUserById(1);
//        return Result.success(user);
//    }
//    @RequestMapping("/tx")
//    public Result<String> gettx(){
//        userService.tx();
//        return Result.success("123");
//    }
//    @RequestMapping("/redis/get")
//    public Result<User> getRedis(){
//        User v1= redisService.get(UserKey.getById,""+1,User.class);
//        return Result.success(v1);
//    }
//    @RequestMapping("/redis/set")
//    public Result<Boolean> setRedis(){
//        User user=new User();
//        user.setId(1);
//        user.setName("ph");
//        Boolean v1= redisService.set(UserKey.getById,user.getId()+"",user);
//        return Result.success(v1);
//    }
}
