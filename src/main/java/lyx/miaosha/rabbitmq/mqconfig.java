package lyx.miaosha.rabbitmq;


import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Title mqconfig
 * @Description
 * @Copyright: 版权所有 (c) 2018 - 2019
 * @Company: 电子商务中心
 * @Author lyx
 * @Version 1.0.0
 * @Create 2019\1\8 0008 14:48
 */

@Configuration
public class mqconfig {

    public static final String MIAOSHA_QUEUE = "miaosha.queue";
    public static final String QUEUE1 = "queue1";
    public static final String QUEUE2 = "queue2";
    public static final String TOPIC_QUEUE1 = "topic.queue1";
    public static final String TOPIC_QUEUE2 = "topic.queue2";
    public static final String HEADER_QUEUE = "header.queue";
    public static final String TOPIC_EXCHANGE = "topicExchage";
    public static final String FANOUT_EXCHANGE = "fanoutxchage";
    public static final String HEADERS_EXCHANGE = "headersExchage";

//    @Bean
    public Queue getmiaoshaqueue(){
//        durable  消息持久化
        return new Queue(MIAOSHA_QUEUE,true);
    }

//    Direct模式 交换机Exchange
    @Bean
    public Queue getqueue1(){
//        durable  消息持久化
        return new Queue(QUEUE1,true);
    }
    @Bean
    public Queue getqueue2(){
//        durable  消息持久化
        return new Queue(QUEUE2,true);
    }

//    Topic模式 交换机Exchange
    @Bean
    public Queue topicqueue1(){
        return new Queue(TOPIC_QUEUE1,true);
    }

    @Bean
    public Queue topicqueue2(){
        return new Queue(TOPIC_QUEUE2,true);
    }

    @Bean
    public TopicExchange topicExchange(){
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    @Bean
    public Binding topicbind1(){
        return BindingBuilder.bind(topicqueue1()).to(topicExchange()).with("topic.key");
    }

    @Bean
    public Binding topicbind2(){
        return BindingBuilder.bind(topicqueue2()).to(topicExchange()).with("topic.*");
    }

//    Fanout模式 交换机Exchange
    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUT_EXCHANGE);
    }
    @Bean
    public Binding fanout1(){
        return BindingBuilder.bind(getqueue1()).to(fanoutExchange());
    }
    @Bean
    public Binding fanout2(){
        return BindingBuilder.bind(getqueue2()).to(fanoutExchange());
    }



}
