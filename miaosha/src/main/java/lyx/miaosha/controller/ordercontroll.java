package lyx.miaosha.controller;

import lyx.miaosha.pojo.goodsvo;
import lyx.miaosha.pojo.miaoshauser;
import lyx.miaosha.pojo.orderdetailvo;
import lyx.miaosha.pojo.orderinfo;
import lyx.miaosha.redis.redisservice;
import lyx.miaosha.result.codemsg;
import lyx.miaosha.result.result;
import lyx.miaosha.service.goodsservice;
import lyx.miaosha.service.miaoshauserservice;
import lyx.miaosha.service.orderservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Title ordercontroll
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2019\1\9 0009 15:03
 */

@Controller
public class ordercontroll {
    @Autowired
    miaoshauserservice userService;

    @Autowired
    redisservice redisService;

    @Autowired
    orderservice orderService;

    @Autowired
    goodsservice goodsService;

    @RequestMapping("/order/detail")
    @ResponseBody
    public result<orderdetailvo> info(Model model, miaoshauser user,
                                      @RequestParam("orderId") long orderId) {
        if(user == null) {
            return result.error(codemsg.SESSION_ERROR);
        }
        orderinfo order = orderService.getOrderById(orderId);
        if(order == null) {
            return result.error(codemsg.ORDER_NOT_EXIST);
        }
        long goodsId = order.getGoodsId();
        goodsvo goods = goodsService.getbyid(goodsId);
        orderdetailvo vo = new orderdetailvo();
        vo.setOrder(order);
        vo.setGoods(goods);
        return result.success(vo);
    }
}
