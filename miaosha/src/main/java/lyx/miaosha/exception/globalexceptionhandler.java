package lyx.miaosha.exception;

import lyx.miaosha.result.codemsg;
import lyx.miaosha.result.result;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @Title globalexceptionhandler
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\28 0028 17:50
 */

@ControllerAdvice
@ResponseBody
public class globalexceptionhandler {

    @ExceptionHandler(value = Exception.class)
    public result exceptionhandler(HttpServletRequest request,Exception e){
        e.printStackTrace();
        if (e instanceof globalexception){
            globalexception globalexception= (globalexception) e;
            return result.error(globalexception.getCodemsg());
        }else return result.error(codemsg.SERVER_ERROR);
    }
}
