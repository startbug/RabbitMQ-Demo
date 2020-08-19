package com.ggs.rabbitmq.direct;

import com.ggs.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.*;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author Starbug
 * @Date 2020/8/18 18:24
 */
public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {
/*        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setPort(5672);
        connectionFactory.setHost("192.168.50.138");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
        connectionFactory.setVirtualHost("/ems");

        //2.创建连接参数
        Connection connection = connectionFactory.newConnection();*/

        Connection connection = RabbitMQUtils.getConnection();

        //3.创建通道
        Channel channel = connection.createChannel();

        //4.通道绑定队列
        channel.queueDeclare("bb", true, false, true, null);

        /**
         * 5.消费消息
         * 参数1(queue): 队列名称(要消费的队列)
         * 参数2(autoAck): 开启消息的自动确认机制
         * 参数3(consumer): 消费时的回调接口
         */
        channel.basicConsume("bb", true, new DefaultConsumer(channel) {
            /**
             * @param consumerTag
             * @param envelope
             * @param properties
             * @param body  消息队列中取出的消息
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(body) = " + new String(body));
            }
        });

        //6.关闭连接,不关闭,则会一直监听队列
//        channel.close();
//        connection.close();
    }

    @Test
    public void testConsumerMessage() throws IOException, TimeoutException, InterruptedException {
        //1.创建连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setPort(5672);
        connectionFactory.setHost("192.168.50.138");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");
        connectionFactory.setVirtualHost("/ems");

        //2.创建连接参数
        Connection connection = connectionFactory.newConnection();

        //3.创建通道
        Channel channel = connection.createChannel();

        //4.通道绑定队列
        channel.queueDeclare("hello", false, false, true, null);

        /**
         * 5.消费消息
         * 参数1(queue): 队列名称(要消费的队列)
         * 参数2(autoAck): 开启消息的自动确认机制
         * 参数3(consumer): 消费时的回调接口
         */
        channel.basicConsume("hello", true, new DefaultConsumer(channel) {
            /**
             * @param consumerTag
             * @param envelope
             * @param properties
             * @param body  消息队列中取出的消息
             * @throws IOException
             */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                System.out.println("new String(body) = " + new String(body));
            }
        });

        //测试用例不会等多线程执行结束才结束,当前测试用例的代码执行完就会自动关闭,回调函数还没来得及执行,就已经结束方法了,所以使用sleep方法来延迟测试用例的结束
//        TimeUnit.SECONDS.sleep(3);
        //6.关闭连接
//        channel.close();
//        connection.close();

    }


}
