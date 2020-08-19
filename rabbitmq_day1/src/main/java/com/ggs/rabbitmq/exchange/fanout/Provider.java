package com.ggs.rabbitmq.exchange.fanout;

import com.ggs.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;

/**
 * @Author Starbug
 * @Date 2020/8/18 23:02
 */
public class Provider {

    public static void main(String[] args) throws IOException {
        //获得连接对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        /**
         * 将通道声明指定交换机
         * 参数1: 交换机名称
         * 参数2: 交换机类型 fanout 广播类型
         */
        channel.exchangeDeclare("logs", "fanout");

        //发送消息
        channel.basicPublish("logs", "", null, "fanout message啊啊啊".getBytes());

        //释放资源
        RabbitMQUtils.closeChannelAndConnection(channel, connection);

    }

}
