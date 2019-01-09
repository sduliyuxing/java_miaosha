package lyx.miaosha.service;

import lyx.miaosha.dao.miaoshauserdao;
import lyx.miaosha.exception.globalexception;
import lyx.miaosha.pojo.loginvo;
import lyx.miaosha.pojo.miaoshauser;
import lyx.miaosha.redis.miaoshauserkey;
import lyx.miaosha.redis.redisservice;
import lyx.miaosha.result.codemsg;
import lyx.miaosha.util.MD5util;
import lyx.miaosha.util.UUIDutil;
import org.apache.catalina.realm.UserDatabaseRealm;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title miaoshauserservice
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\26 0026 17:43
 */

@Service
public class miaoshauserservice {

    public static final String TOKEN="token";

    @Autowired
    private miaoshauserdao miaoshauserdao;

    @Autowired
    private redisservice redisservice;

    public miaoshauser getbyid(long id){
        return miaoshauserdao.getbyid(id);
    }

    public boolean login(HttpServletResponse response, loginvo loginvo){
         if (loginvo==null){
             throw new globalexception(codemsg.SERVER_ERROR);
         }
        String phone=loginvo.getPhone();
         String passward=loginvo.getPassword();
//         手机号为id
        miaoshauser miaoshauser=getbyid(Long.valueOf(phone));
         if (miaoshauser==null){
             throw new globalexception(codemsg.MOBILE_NOT_EXIST);
         }
         String passward1=miaoshauser.getPassword();
        String salt = miaoshauser.getSalt();
        String passward2=MD5util.two(passward,salt);
        if (!passward1.equals(passward2)){
            throw new globalexception(codemsg.PASSWORD_ERROR);
        }
        String token=UUIDutil.uuid();
        addcookie(token,miaoshauser,response);
        return true;
    }

    public void addcookie(String token,miaoshauser user,HttpServletResponse response){
        redisservice.set(miaoshauserkey.token,token,user);
        Cookie cookie=new Cookie(TOKEN,token);
        cookie.setMaxAge(miaoshauserkey.TOKEN_EXPIRE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public miaoshauser getbytoken(String token,HttpServletResponse response){
        if (StringUtils.isEmpty(token)){
            return null;
        }
        miaoshauser miaoshauser= (miaoshauser) redisservice.get(miaoshauserkey.token,token,miaoshauser.class);
        if (miaoshauser!=null){
            addcookie(token,miaoshauser,response);
        }
        return miaoshauser;

    }
}
