package com.ph.miaosha.controller;

import com.ph.miaosha.domain.OrderInfo;
import com.ph.miaosha.domain.SecKillOrder;
import com.ph.miaosha.domain.User;
import com.ph.miaosha.rabbitmq.MQSender;
import com.ph.miaosha.rabbitmq.SecKillMessage;
import com.ph.miaosha.redis.GoodsKey;
import com.ph.miaosha.redis.RedisService;
import com.ph.miaosha.result.CodeMsg;
import com.ph.miaosha.result.Result;
import com.ph.miaosha.service.GoodsService;
import com.ph.miaosha.service.OrderService;
import com.ph.miaosha.service.SecKillService;
import com.ph.miaosha.vo.GoodsVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@RequestMapping("/secKill")
@Controller
public class SecKillController implements InitializingBean {
    @Autowired
    GoodsService goodsService;
    @Autowired
    OrderService orderService;
    @Autowired
    SecKillService secKillService;
    @Autowired
    RedisService redisService;
    @Autowired
    MQSender mqSender;
    @RequestMapping(value = "/secKill",method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> secKill(Model model, User user, @RequestParam("goodsId")long goodsId) {
        model.addAttribute("user", user);
        if (user == null) {
            return Result.error(CodeMsg.SERVER_ERROR);
        }
        //预减库存
        long stock = redisService.decr(GoodsKey.getSecKillGoodsStock, "" + goodsId);
        if (stock < 0) {
            return Result.error(CodeMsg.SRC_KILL_OVER);
        }
        //是否秒杀到
        SecKillOrder order = orderService.getSecKillOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            //秒杀到了
           // model.addAttribute("errormsg", CodeMsg.SUCCESS_SERCKILL.getMsg());
            return Result.error(CodeMsg.SUCCESS_SERCKILL);
        }
        //入队
        SecKillMessage secKillMessage = new SecKillMessage();
        secKillMessage.setUser(user);
        secKillMessage.setGoodsId(goodsId);
        mqSender.sendSecKillMessage(secKillMessage);
        return Result.success(0);//排队中
    }

    /**
     * orderID:秒杀成功
     * -1：秒杀失败
     * 0：排队中
     * @param model
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping(value = "/result",method = RequestMethod.GET)
    @ResponseBody
    public Result<Long> secKillResult(Model model,User user,@RequestParam("goodsId") long goodsId){
        if (user==null){
            return Result.error(CodeMsg.SESSION_ERROR);
        }
       long result= secKillService.getResult(user.getId(),goodsId);
        return Result.success(result);
    }
    /*
      *系统初始化调用该方法
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo>goodsList=goodsService.listGoods();
        if (goodsList==null){
            return ;
        }
        for (GoodsVo goodsVo:goodsList){
            redisService.set(GoodsKey.getSecKillGoodsStock,""+goodsVo.getId(),goodsVo.getStockCount());
        }
    }
}
