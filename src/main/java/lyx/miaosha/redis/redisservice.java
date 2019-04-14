package lyx.miaosha.redis;

import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

/**
 * @Title redisservice
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\28 0028 9:29
 */

@Service
public class redisservice {

    @Autowired
    private redispoolfactory redispoolfactory;

    /*
      取值
     */

    public Object get(keyprefix keyprefix,String key,Class clazz){

        Jedis jedis=redispoolfactory.pool().getResource();
        String realkey=keyprefix.getkeyprefix()+key;
        String val=jedis.get(realkey);
        Object t=stringtobean(val,clazz);
        jedis.close();
        return t;
    }

    /*
    设值
     */
    public boolean set(keyprefix keyprefix,String key,Object val){
        Jedis jedis=redispoolfactory.pool().getResource();
        String realkey=keyprefix.getkeyprefix()+key;
        String value=objecttostring(val);
        if (value==null||value.length()<=0){
            return false;
        }
        Integer second=keyprefix.expiresecond();
        if (second<=0){
            jedis.set(realkey, value);
        }else jedis.setex(realkey,second,value);
        jedis.close();
        return true;
    }

    /*
      判断是否存在
     */

    public boolean isexist(keyprefix keyprefix,String key){
        String realkey=keyprefix.getkeyprefix()+key;
        Jedis jedis=redispoolfactory.pool().getResource();
        Boolean exists = jedis.exists(realkey);
        jedis.close();
        return exists;
    }

    /*
    增加
     */
    public long incr(keyprefix keyprefix,String key){
        String realkey=keyprefix.getkeyprefix()+key;
        Jedis jedis=redispoolfactory.pool().getResource();
        Long incr = jedis.incr(realkey);
        jedis.close();
        return incr;
    }
    /*
      减少
     */
    public long decr(keyprefix keyprefix,String key){
        String realkey=keyprefix.getkeyprefix()+key;
        Jedis jedis=redispoolfactory.pool().getResource();
        Long decr = jedis.decr(realkey);
        jedis.close();
        return decr;
    }

    public static String objecttostring(Object val) {
        if (val==null){
            return null;
        }
        Object clazz = val.getClass();
        if (clazz==int.class||clazz==Integer.class||clazz==long.class||clazz==Long.class){
            return val+"";
        }else if (clazz==String.class){
            return (String) val;
        }else return JSON.toJSONString(val);
    }

    @SuppressWarnings("unchecked")
    public static <T> T stringtobean(String val,Class clazz) {
        if (val==null||val.length()<=0||clazz==null){
            return null;
        }else if (clazz==int.class||clazz==Integer.class){
            return (T)Integer.valueOf(val);
        }else if (clazz==long.class||clazz==Long.class){
            return (T)Long.valueOf(val);
        }else if(clazz==String.class){
            return (T)val;
        }else return (T)JSON.toJavaObject(JSON.parseObject(val), clazz);
    }

}
