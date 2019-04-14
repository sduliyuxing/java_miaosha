package lyx.miaosha.rabbitmq;

import lyx.miaosha.pojo.goodsvo;
import lyx.miaosha.pojo.miaoshaorder;
import lyx.miaosha.pojo.miaoshauser;
import lyx.miaosha.redis.redisservice;
import lyx.miaosha.service.goodsservice;
import lyx.miaosha.service.miaoshaservice;
import lyx.miaosha.service.orderservice;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title mqreceiver
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2019\1\8 0008 15:37
 */

@Service
public class mqreceiver {

    @Autowired
    redisservice redisService;

    @Autowired
    goodsservice goodsService;

    @Autowired
    orderservice orderService;

    @Autowired
    miaoshaservice miaoshaService;

//    请求出队，生成订单，减少库存
    @RabbitListener(queues=mqconfig.MIAOSHA_QUEUE)
    public void receive(String message) {
        miaoshamessage mm  = redisservice.stringtobean(message, miaoshamessage.class);
        miaoshauser user = mm.getMiaoshauser();
        long goodsId = mm.getGoodsid();

        goodsvo goods = goodsService.getbyid(goodsId);
        int stock = goods.getStockCount();
        if(stock <= 0) {
            return;
        }
        //判断是否已经秒杀到了
        miaoshaorder order = orderService.getMiaoshaOrderByUserIdGoodsId(user.getId(), goodsId);
        if(order != null) {
            return;
        }
//        减库存 下订单 写入秒杀订单
        miaoshaService.miaosha(user, goods);
    }

    @RabbitListener(queues = mqconfig.QUEUE1)
    public void receiver(String message){
        System.out.println(mqconfig.QUEUE1 +":"+message+"接受成功");
    }

    @RabbitListener(queues = mqconfig.TOPIC_QUEUE1)
    public void topic1receiver(String message){
        System.out.println(mqconfig.TOPIC_QUEUE1+message+"接受成功");
    }

    @RabbitListener(queues = mqconfig.TOPIC_QUEUE2)
    public void topic2receiver(String message){
        System.out.println(mqconfig.TOPIC_QUEUE2+message+"接受成功");
    }

    @RabbitListener(queues = mqconfig.QUEUE1)
    public void fanoutreceiver1(String message){
        System.out.println(mqconfig.QUEUE1 +message+"接受成功");
    }
    @RabbitListener(queues = mqconfig.QUEUE2)
    public void fanoutreceiver2(String message){
        System.out.println(mqconfig.QUEUE2+message+"接受成功");
    }
}
