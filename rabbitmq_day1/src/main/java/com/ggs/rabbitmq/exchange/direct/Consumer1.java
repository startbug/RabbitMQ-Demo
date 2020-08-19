package com.ggs.rabbitmq.exchange.direct;

import com.ggs.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author Starbug
 * @Date 2020/8/18 23:54
 */
public class Consumer1 {

    public static void main(String[] args) throws IOException {
        //获取连接对象
        Connection connection = RabbitMQUtils.getConnection();

        //获取通道对象
        Channel channel = connection.createChannel();


        //声明交换机
        String exchange = "logs_direct";
        channel.exchangeDeclare(exchange, "direct");

        //创建临时队列
        String queueName = channel.queueDeclare().getQueue();

        //基于route key绑定队列和交换机
        channel.queueBind(queueName, exchange, "info");

        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("Consumer1 consume a message-->" + new String(body));
            }
        });


    }

}
