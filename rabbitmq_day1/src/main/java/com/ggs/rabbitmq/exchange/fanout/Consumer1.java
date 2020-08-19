package com.ggs.rabbitmq.exchange.fanout;

import com.ggs.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.*;

import java.io.IOException;

/**
 * @Author Starbug
 * @Date 2020/8/18 23:15
 */
public class Consumer1 {

    public static void main(String[] args) throws IOException {
        //获取连接
        Connection connection = RabbitMQUtils.getConnection();
        Channel channel = connection.createChannel();

        /**
         * 将通道声明指定交换机
         * 参数1: 交换机名字
         * 参数2: 交换机类型,fanout广播类型
         */
        channel.exchangeDeclare("logs","fanout");

        //临时队列
        String queueName = channel.queueDeclare().getQueue();

        //绑定交换机和队列
        channel.queueBind(queueName,"logs","");

        //消费消息
        channel.basicConsume(queueName,true,new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println(new String(body));
            }
        });




    }

}
