package com.ph.miaosha.controller;

import com.ph.miaosha.domain.User;
import com.ph.miaosha.result.Result;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController {
    @RequestMapping("/info")
    @ResponseBody
    public Result<User>  info(Model model,User user){
        model.addAttribute("user",user);
        return  Result.success(user);

    }
}
