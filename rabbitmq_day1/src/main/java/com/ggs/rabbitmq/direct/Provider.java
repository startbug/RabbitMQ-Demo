package com.ggs.rabbitmq.direct;

import com.ggs.rabbitmq.utils.RabbitMQUtils;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.MessageProperties;
import org.junit.Test;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author Starbug
 * @Date 2020/8/18 17:51
 */
public class Provider {

    //生产消息
    @Test
    public void testSendMessage() throws IOException, TimeoutException {

/*        //1.创建连接mq的连接工厂对象
        ConnectionFactory connectionFactory = new ConnectionFactory();

        //2.设置连接rabbitmq主机
        connectionFactory.setHost("192.168.50.138");
        //3.设置端口号
        connectionFactory.setPort(5672);
        //4.设置连接哪一个虚拟主机
        connectionFactory.setVirtualHost("/ems");
        //5.设置访问虚拟主机的用户名和密码
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123");

        //6.获取连接对象
        Connection connection = connectionFactory.newConnection();*/

        Connection connection = RabbitMQUtils.getConnection();

        //7.获取连接中的通道
        Channel channel = connection.createChannel();

        /**
         * 8.通道绑定对应的消息队列
         * 参数1(queue): 队列名称,如果队列不存在则自动创建
         * 参数2(durable): 用来定义队列特性,是否要持久化, true:持久化 false:不持久化
         * 参数3(exclusive): 是否独占独立, true:独占队列 false:不独占(独占:表示只有当前通道能操作该队列)
         * 参数4(autoDelete): 是否在消费完成后自动删除队列, true自动删除  false不自动删除
         * 参数5(arguments): 额外附加参数
         */
        channel.queueDeclare("bb", true, false, true, null);

        /**
         * 9.发布消息(这里使用直连模式,直接发给队列,所以不设置交换机)
         * 参数1(exchange): 交换机名称
         * 参数2(routingKey): 队列名称(也称为路由键)
         * 参数3(props): 传递消息额外设置
         * 参数4(body):消息的具体内容
         */
        channel.basicPublish("", "bb", MessageProperties.PERSISTENT_TEXT_PLAIN, "Ready Player One".getBytes());

/*        //10.关闭连接
        channel.close();
        connection.close();*/
        RabbitMQUtils.closeChannelAndConnection(channel, connection);


    }

}
