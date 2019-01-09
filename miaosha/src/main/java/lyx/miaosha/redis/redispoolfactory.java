package lyx.miaosha.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Title redispoolfactory
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2018\12\28 0028 9:15
 */

@Service
public class redispoolfactory {

    @Autowired
    private redisconfig redisconfig;

    @Bean
    public JedisPool pool(){
        JedisPoolConfig config=new JedisPoolConfig();
        config.setMaxIdle(redisconfig.getPoolMaxIdle());
        config.setMaxTotal(redisconfig.getPoolMaxTotal());
        config.setMaxWaitMillis(redisconfig.getPoolMaxWait()*1000);
        JedisPool pool=new JedisPool(config,redisconfig.getHost(),
                redisconfig.getPort(),redisconfig.getTimeout()*1000,
                redisconfig.getPassword(),0);
        return pool;
    }
}
