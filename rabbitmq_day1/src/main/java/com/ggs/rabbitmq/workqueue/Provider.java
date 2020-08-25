package com.ggs.rabbitmq.workqueue;

import com.ggs.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @Author Starbug
 * @Date 2020/8/18 19:36
 * <p>
 * 能者多劳的用法
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        //创建连接对象
        Connection connection = RabbitMQUtils.getConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //通过通道声明队列
        channel.queueDeclare("worka", true, false, false, null);

        for (int i = 0; i < 20; i++) {
            //发布消息
            channel.basicPublish("", "worka", null, (i + "测试工作队列").getBytes());
        }

        RabbitMQUtils.closeChannelAndConnection(channel, connection);
    }

}
