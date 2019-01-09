package lyx.miaosha.util;

import java.util.UUID;

/**
 * @Title UUIDutil
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\28 0028 18:03
 */
public class UUIDutil {
    public static String uuid(){
       return UUID.randomUUID().toString().replace("-","");
    }
}
