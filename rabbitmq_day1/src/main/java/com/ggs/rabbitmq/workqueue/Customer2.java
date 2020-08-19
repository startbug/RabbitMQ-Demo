package com.ggs.rabbitmq.workqueue;

import com.ggs.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author Starbug
 * @Date 2020/8/18 19:35
 */
public class Customer2 {

    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        //创建通道
        Channel channel = connection.createChannel();

        //声明队列
        channel.queueDeclare("worka",true,false,false,null);

        channel.basicQos(1); //每一次确认一条消息

        //绑定队列
        channel.basicConsume("worka", false, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("队列中获取得消息:" + new String(body));

                //上面取消了自动确认,这里进行手动确认
                //参数1: 手动确认的表示(消息的id)  参数2: false,每次确认一个(是否确认多参数)
                channel.basicAck(envelope.getDeliveryTag(),false);
            }
        });

    }


}
