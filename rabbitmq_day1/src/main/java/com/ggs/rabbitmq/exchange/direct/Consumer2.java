package com.ggs.rabbitmq.exchange.direct;

import com.ggs.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author Starbug
 * @Date 2020/8/19 0:03
 */
public class Consumer2 {

    public static void main(String[] args) throws IOException {
        //创建连接
        Connection connection = RabbitMQUtils.getConnection();
        //创建通道对象
        Channel channel = connection.createChannel();

        //声明交换机
        String exchange = "logs_direct";
        channel.exchangeDeclare(exchange, "direct");

        //创建临时队列
        String queueName = channel.queueDeclare().getQueue();

        //绑定交换机和队列
        channel.queueBind(queueName, exchange, "info");
        channel.queueBind(queueName, exchange, "error");
        channel.queueBind(queueName, exchange, "warning");

        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("消费消息-->" + new String(body));
            }
        });


    }

}
