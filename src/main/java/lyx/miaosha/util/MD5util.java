package lyx.miaosha.util;


import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

/**
 * @Title MD5util
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\28 0028 15:21
 */

public class MD5util {

    public static String md5(String msg){
        return DigestUtils.md5Hex(msg);
    }

    private static final String str="1a2b3c4d";

    public static String one(String msg){
       String passward=""+str.charAt(0)+str.charAt(2)+msg+str.charAt(4)+str.charAt(6);
        return md5(passward);
    }

    public static String two(String msg,String str){
        String passward= ""+ str.charAt(0)+str.charAt(2)+msg+str.charAt(4)+str.charAt(6);
        return md5(passward);
    }

    public static String dbpassward(String msg,String str){
        String passward=one(msg);
        passward=two(passward,str);
        return passward;
    }

    @Test
    public void testmd5(){
        String ll="135792468";
        System.out.println(dbpassward("123456",ll));
    }

}
