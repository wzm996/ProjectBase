package com.projectbase.common.listener;


import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class MyMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        //1、获取道channel信息
        String channel = new String(message.getChannel());
        //2、获取到消息信息
        String info = new String(message.getBody());
        System.out.println("来自：" + channel + "," + "的消息：" + info);
    }

}
