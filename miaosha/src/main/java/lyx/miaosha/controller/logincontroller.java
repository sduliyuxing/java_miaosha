package lyx.miaosha.controller;

import lyx.miaosha.pojo.loginvo;
import lyx.miaosha.result.result;
import lyx.miaosha.service.miaoshauserservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

/**
 * @Title logincontroller
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\26 0026 17:48
 */

@Controller
public class logincontroller {

    @Autowired
    private miaoshauserservice miaoshauserservice;

    @RequestMapping("/login/to_login")
    public String toLogin() {
        return "login";
    }

    @ResponseBody
    @RequestMapping("/login/do_login")
    public result dologin(HttpServletResponse response,loginvo loginvo){
        miaoshauserservice.login(response,loginvo);
      return result.success(true);
    }
}
