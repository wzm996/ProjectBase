package com.projectbase.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.Topic;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

@Configuration
public class RedisListenerConfig {

    //redis连接工厂
    @Autowired
    private RedisConnectionFactory connectionFactory;

    //redis 消息监听器
    @Autowired
    private MessageListener redisMsgListener;
    //任务池
    private ThreadPoolTaskScheduler taskScheduler;


    /**
     * 创建任务池,运行线程等待处理redis消息
     * @return
     */
    @Bean
    public ThreadPoolTaskScheduler iniTaskScheduler(){
        if(taskScheduler != null){
            return taskScheduler;
        }
        taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(20);
        return taskScheduler;
    }

    /**
     * 定义Redis的监听容器
     * @return
     */
    @Bean
    public RedisMessageListenerContainer initRedisContainer(){
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        //redis 连接工厂
        container.setConnectionFactory(connectionFactory);
        //设置运行任务池
        container.setTaskExecutor(iniTaskScheduler());
        //定义监听渠道,名称为topic1
        Topic topic = new ChannelTopic("topic1");
        //定义监听器监听的Redis的消息
        container.addMessageListener(redisMsgListener,topic);
        return container;
    }

}
