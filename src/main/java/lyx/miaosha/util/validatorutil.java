package lyx.miaosha.util;


import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Title validatorutil
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\28 0028 16:15
 */
public class validatorutil {

    private static final Pattern pattern=Pattern.compile("1\\d{10}");

    public static boolean isphone(String msg){
      if (StringUtils.isEmpty(msg)){
          return false;
      }
        Matcher matcher=pattern.matcher(msg);
        return matcher.matches();
    }
}
