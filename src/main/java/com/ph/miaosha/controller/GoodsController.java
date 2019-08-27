package com.ph.miaosha.controller;

import com.ph.miaosha.domain.User;
import com.ph.miaosha.redis.GoodsKey;
import com.ph.miaosha.redis.RedisService;
import com.ph.miaosha.result.Result;
import com.ph.miaosha.service.GoodsService;
import com.ph.miaosha.service.UserService;
import com.ph.miaosha.vo.GoodsDetailVo;
import com.ph.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.context.IWebContext;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.context.webflux.SpringWebFluxContext;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RequestMapping("/goods")
@Controller
public class GoodsController {
    @Autowired
    UserService userService;
    @Autowired
    RedisService redisService;
    @Autowired
    GoodsService goodsService;
    @RequestMapping(value = "/to_list")
    public String toList(Model model, User user){
        model.addAttribute("user",user);
        List<GoodsVo> goodsList=goodsService.listGoods();
        model.addAttribute("goodsList",goodsList);
        return "goods_list";
    }
    @RequestMapping(value = "/to_detail/{goodsId}")
    public String detail(Model model, User user, @PathVariable("goodsId") long goodsId){
        model.addAttribute("user",user);
        GoodsVo goods=goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods",goods);
        long startAt=goods.getStartDate().getTime();
        long endAt=goods.getEndDate().getTime();
        long now=System.currentTimeMillis();
        int status=0;//秒杀商品当前状态
        int remainSeconds=0;//秒杀剩余时间
        if (now<startAt){//秒杀还没有开始
            status=0;
            remainSeconds=(int)(startAt-now)/1000;
        }else if(now>endAt){//秒杀已经结束
            status=2;
            remainSeconds=-1;
        }else{//秒杀进行中
            status=1;
            remainSeconds=0;
        }
        model.addAttribute("status",status);
        model.addAttribute("remainSeconds",remainSeconds);
        return "goods_detail";
    }
}
