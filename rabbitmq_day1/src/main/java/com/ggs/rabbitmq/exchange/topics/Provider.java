package com.ggs.rabbitmq.exchange.topics;

import com.ggs.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @Author Starbug
 * @Date 2020/8/19 16:03
 */
public class Provider {
    public static void main(String[] args) throws IOException {
        //获取连接对象和通道对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        String exchange = "logs_topics";
        String type = "topic";
        //声明交换机
        channel.exchangeDeclare(exchange, type);

        String routingKey = "lover.lyz.lxy";
        channel.basicPublish(exchange, routingKey, null, ("Topics message 已经发送,内容为:" + routingKey).getBytes());

        RabbitMQUtils.closeChannelAndConnection(channel, connection);

    }

}
