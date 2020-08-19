package com.ggs.rabbitmq.exchange.direct;

import com.ggs.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @Author Starbug
 * @Date 2020/8/18 23:55
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        //创建连接
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        //声明交换机
        String exchange = "logs_direct";
        channel.exchangeDeclare(exchange, "direct");

        //发布消息
        String routingKey = "info";
        channel.basicPublish(exchange, routingKey, null, ("exchange direct message send,content:" + routingKey).getBytes());

        //关闭资源
        RabbitMQUtils.closeChannelAndConnection(channel, connection);
    }

}
