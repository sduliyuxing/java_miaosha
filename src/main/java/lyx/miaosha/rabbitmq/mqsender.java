package lyx.miaosha.rabbitmq;

import lyx.miaosha.redis.redisservice;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Title mqsender
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2019\1\8 0008 15:26
 */

@Service
public class mqsender {

    @Autowired
    private AmqpTemplate amqpTemplate;

    public void miaoshasend(miaoshamessage message){
        String msg=redisservice.objecttostring(message);
        amqpTemplate.convertAndSend(mqconfig.MIAOSHA_QUEUE,msg);
   }


    public void send(Object message){
        int i=0;
////        while(true){
            String msg = i+":"+redisservice.objecttostring(message);
            amqpTemplate.convertAndSend(mqconfig.QUEUE1, msg);
            System.out.println(msg + "发送成功");
//            i++;
        }
//    }

    public void topicsend(Object message){
        String msg = redisservice.objecttostring(message);
        amqpTemplate.convertAndSend(mqconfig.TOPIC_EXCHANGE,"topic.key",message+"1");
        System.out.println(message+"1 发送成功");
        amqpTemplate.convertAndSend(mqconfig.TOPIC_EXCHANGE,"topic.keys",message+"2");
        System.out.println(message+"2 发送成功");
    }

    public void fanoutsend(Object message){
        String msg=redisservice.objecttostring(message);
        amqpTemplate.convertAndSend(mqconfig.FANOUT_EXCHANGE,"",msg);
        System.out.println(mqconfig.FANOUT_EXCHANGE+msg+"发送成功");
    }
}
