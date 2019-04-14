package lyx.miaosha.controller;

import lyx.miaosha.pojo.miaoshauser;
import lyx.miaosha.pojo.user;
import lyx.miaosha.rabbitmq.miaoshamessage;
import lyx.miaosha.rabbitmq.mqsender;
import lyx.miaosha.redis.redisservice;
import lyx.miaosha.redis.userkey;
import lyx.miaosha.result.codemsg;
import lyx.miaosha.result.result;
import lyx.miaosha.service.miaoshauserservice;
import lyx.miaosha.service.userservice;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Title democontroller
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\26 0026 16:09
 */

@Controller
public class democontroller {

    @Autowired
    private userservice userservice;

    @Autowired
    private redisservice redisservice;

    @Autowired
    private miaoshauserservice miaoshauserservice;

    @Autowired
    private mqsender mqsender;

    @RequestMapping("/mq")
    @ResponseBody
    public result mq(){
//        mqsender.send("消息");
//        mqsender.topicsend("消息");
//        mqsender.fanoutsend("消息");
        miaoshamessage m=new miaoshamessage();
        m.setGoodsid(1L);
        mqsender.miaoshasend(m);
        return result.success("ok");
    }

    @ResponseBody
    @RequestMapping("/demo")
    public String home(){
        return "hello world";
    }

    @RequestMapping("/demo/success")
    @ResponseBody
    public result<String> hello() {
        return result.success("hello success");
    }

    @RequestMapping("/demo/error")
    @ResponseBody
    public result<String> helloError() {
        return result.error(codemsg.SERVER_ERROR);
    }

    @RequestMapping("/demo/thymeleaf")
    public String  thymeleaf(Model model) {
        model.addAttribute("name", "miaosha");
        return "hello";
    }

    @RequestMapping("/demo/db/get")
    @ResponseBody
    public result dbget(@Param("id")long id){
        miaoshauser user = miaoshauserservice.getbyid(id);
        return result.success(user);
    }


    @RequestMapping("/demo/db/tx")
    @ResponseBody
    public result dbtx(){
        boolean b = userservice.tx();
        return result.success(b);
    }

    @RequestMapping("/demo/redis/get")
    @ResponseBody
    public result get(){
        user user = (user) redisservice.get(userkey.getid, 1 + "", user.class);
        System.out.println(user);
        return result.success(user);
    }

    @RequestMapping("/demo/redis/set")
    @ResponseBody
    public result set(){
        user user=new user();
        user.setId(1);
        user.setName("miaosha");
        redisservice.set(userkey.getid,1+"",user);
        System.out.println(user);
        return result.success(user);
    }

}
