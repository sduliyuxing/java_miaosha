package lyx.miaosha.controller;

import lyx.miaosha.dao.goodsdao;
import lyx.miaosha.pojo.goodsdetailvo;
import lyx.miaosha.pojo.goodsvo;
import lyx.miaosha.pojo.miaoshauser;
import lyx.miaosha.result.result;
import lyx.miaosha.service.goodsservice;
import lyx.miaosha.service.miaoshauserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.Result;
import java.util.List;

/**
 * @Title goodscontroller
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\26 0026 17:51
 */

@Controller
@RequestMapping("/goods")
public class goodscontroller {

    @Autowired
    private miaoshauserservice miaoshauserservice;

    @Autowired
    private goodsservice goodsservice;

    @Autowired
    goodsdao goodsdao;

    @RequestMapping("/to_list")
    public String list(miaoshauser user, Model model){
        model.addAttribute("user",user);
        List<goodsvo> list=goodsservice.get();
        model.addAttribute("goodsList",list);
        return "goods_list";
    }


    @RequestMapping(value="/detail/{goodsId}")
    @ResponseBody
    public result<goodsdetailvo> detail(HttpServletRequest request, HttpServletResponse response, Model model, miaoshauser user,
                                        @PathVariable("goodsId")long goodsId) {
        goodsvo goods = goodsservice.getbyid(goodsId);
        long startAt = goods.getStartDate().getTime();
        long endAt = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int miaoshaStatus = 0;
        int remainSeconds = 0;
        if(now < startAt ) {//秒杀还没开始，倒计时
            miaoshaStatus = 0;
            remainSeconds = (int)((startAt - now )/1000);
        }else  if(now > endAt){//秒杀已经结束
            miaoshaStatus = 2;
            remainSeconds = -1;
        }else {//秒杀进行中
            miaoshaStatus = 1;
            remainSeconds = 0;
        }
        goodsdetailvo vo = new goodsdetailvo();
        vo.setGoods(goods);
        vo.setUser(user);
        vo.setRemainSeconds(remainSeconds);
        vo.setMiaoshaStatus(miaoshaStatus);
        return result.success(vo);
    }
}
