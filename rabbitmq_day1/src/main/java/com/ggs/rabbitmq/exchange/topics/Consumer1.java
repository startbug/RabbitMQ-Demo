package com.ggs.rabbitmq.exchange.topics;

import com.ggs.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author Starbug
 * @Date 2020/8/19 16:35
 */
public class Consumer1 {

    public static void main(String[] args) throws IOException {
        //创建链接对象和通道对象
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        String exchange = "logs_topics";
        String type = "topic";
        //声明交换机
        channel.exchangeDeclare(exchange, type);

        String routingKey = "lover.*";
        //获取临时队列
        String queueName = channel.queueDeclare().getQueue();
        channel.queueBind(queueName, exchange, routingKey);

        channel.basicConsume(queueName, true, new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });


    }

}
