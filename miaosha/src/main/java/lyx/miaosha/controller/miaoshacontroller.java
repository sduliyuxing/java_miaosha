package lyx.miaosha.controller;

import lyx.miaosha.pojo.goodsvo;
import lyx.miaosha.pojo.miaoshaorder;
import lyx.miaosha.pojo.miaoshauser;
import lyx.miaosha.rabbitmq.miaoshamessage;
import lyx.miaosha.rabbitmq.mqsender;
import lyx.miaosha.redis.goodskey;
import lyx.miaosha.redis.redisservice;
import lyx.miaosha.result.codemsg;
import lyx.miaosha.result.result;
import lyx.miaosha.service.goodsservice;
import lyx.miaosha.service.miaoshaservice;
import lyx.miaosha.service.miaoshauserservice;
import lyx.miaosha.service.orderservice;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * @Title miaoshacontroller
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2019\1\3 0003 11:02
 *
 * 1.系统初始化，把商品库存数量加载到Redis
 *
 * 2.收到请求，Redis预减库存，库存不足，直接返回，否则3
 *
 * 3.请求入队，立即返回排队中
 *
 * 4.请求出队，生成订单，减少库存
 *
 * 5.客户端轮询，是否秒杀成功
 */
@Controller
@RequestMapping("/miaosha")
public class miaoshacontroller implements InitializingBean {

    @Autowired
    miaoshauserservice userService;

    @Autowired
    redisservice redisService;

    @Autowired
    goodsservice goodsService;

    @Autowired
    orderservice orderService;

    @Autowired
    miaoshaservice miaoshaService;

    @Autowired
    private mqsender mqsender;

//    @RequestMapping("/do_miaosha")
//    public String list(Model model, miaoshauser user,
//                       @RequestParam("goodsId")long goodsId) {
//        model.addAttribute("user", user);
//        if(user == null) {
//            return "login";
//        }
//        //判断库存
//        goodsvo goods = goodsService.getbyid(goodsId);
//        int stock = goods.getStockCount();
//        if(stock <= 0) {
//            model.addAttribute("errmsg", codemsg.MIAO_SHA_OVER.getMsg());
//            return "miaosha_fail";
//        }
//        //判断是否已经秒杀到了
//        miaoshaorder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
//        if(order != null) {
//            model.addAttribute("errmsg", codemsg.REPEATE_MIAOSHA.getMsg());
//            return "miaosha_fail";
//        }
//        //减库存 下订单 写入秒杀订单
//        orderinfo orderInfo = miaoshaService.miaosha(user,goods);
//        model.addAttribute("orderInfo", orderInfo);
//        model.addAttribute("goods", goods);
//        return "order_detail";
//    }


    private HashMap<Long, Boolean> hashMap = new HashMap<Long, Boolean>();

    //  系统初始化，把商品库存数量加载到Redis
    @Override
    public void afterPropertiesSet() throws Exception {
        List<goodsvo> list = goodsService.get();
        if (list == null) {
            return;
        }
        for (goodsvo goodsvo : list) {
            redisService.set(goodskey.getMiaoshaGoodsStock, "" + goodsvo.getId(), goodsvo.getStockCount());
            hashMap.put(goodsvo.getId(), false);
        }
    }


//    收到请求，Redis预减库存，库存不足，直接返回，否则3 请求入队，立即返回排队中
    @RequestMapping(value = "/do_miaosha", method = RequestMethod.POST)
    @ResponseBody
    public result<Integer> miaosha(Model model, miaoshauser user,
                                   @RequestParam("goodsId") long goodsId) {
        model.addAttribute("user", user);
        if (user == null) {
            return result.error(codemsg.SESSION_ERROR);
        }
        //内存标记，减少redis访问
        boolean over = hashMap.get(goodsId);
        if (over) {
            return result.error(codemsg.MIAO_SHA_OVER);
        }
        //预减库存
        long stock = redisService.decr(goodskey.getMiaoshaGoodsStock, "" + goodsId);//10
        if (stock < 0) {
            hashMap.put(goodsId, true);
            return result.error(codemsg.MIAO_SHA_OVER);
        }
        //判断是否已经秒杀到了
        miaoshaorder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            return result.error(codemsg.REPEATE_MIAOSHA);
        }
        //入队
        miaoshamessage message = new miaoshamessage();
        message.setMiaoshauser(user);
        message.setGoodsid(goodsId);
        mqsender.miaoshasend(message);
        return result.success(0);//排队中
    }

    //秒杀排队后轮询
    //秒杀成功返回订单id
    //秒杀失败返回 -1
    //排队中 返回 0
    @RequestMapping("/result")
    @ResponseBody
    public result miaoshResult(Model model, miaoshauser user, @RequestParam("goodsId") long goodsId) {
        //没登录就跳转到登录页面
        model.addAttribute("user", user);
        if (user == null) {
            return result.error(codemsg.SESSION_ERROR);
        }
        //查询下是否生成了订单
        long rsult = miaoshaService.getMiaoshaResult(user.getId(), goodsId);
        return result.success(rsult);
    }




}
