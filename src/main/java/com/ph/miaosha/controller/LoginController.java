package com.ph.miaosha.controller;
import com.ph.miaosha.result.Result;
import com.ph.miaosha.service.UserService;
import com.ph.miaosha.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;


@Controller
@RequestMapping("/login")
public class LoginController {

    private static Logger log=LoggerFactory.getLogger(LoginController.class);
    @Autowired
    UserService userService;
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> do_login(HttpServletResponse response, @Valid LoginVo loginVo){
       userService.login(response,loginVo);
        return Result.success("成功");

    }

    @ResponseBody
    public Result<Boolean> error(){
        return null;
    }
}
