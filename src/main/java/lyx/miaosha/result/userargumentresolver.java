package lyx.miaosha.result;

import lyx.miaosha.pojo.miaoshauser;
import lyx.miaosha.service.miaoshauserservice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Title userargumentresolver
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\29 0029 16:01
 */

@Service
public class userargumentresolver implements HandlerMethodArgumentResolver {

    @Autowired
    private miaoshauserservice service;

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果参数类型是这个实体类返回 true,才能执行下面的那个函数
        Class clazz=parameter.getParameterType();
        return clazz==miaoshauser.class;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
             ModelAndViewContainer mavContainer, NativeWebRequest webRequest,
              WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request=webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse response=webRequest.getNativeResponse(HttpServletResponse.class);

        String paramtokon=request.getParameter(miaoshauserservice.TOKEN);
        String cookietoken=getcookievalue(request,miaoshauserservice.TOKEN);
        if (StringUtils.isEmpty(paramtokon)&&StringUtils.isEmpty(cookietoken)){
            return null;
        }
        String token=StringUtils.isEmpty(paramtokon)?cookietoken:paramtokon;
        return service.getbytoken(token,response);
    }

    public String getcookievalue(HttpServletRequest request, String cookiename) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies){
            if (cookie.getName().equals(cookiename)){
                return cookie.getValue();
            }
        }
        return null;
    }
}
