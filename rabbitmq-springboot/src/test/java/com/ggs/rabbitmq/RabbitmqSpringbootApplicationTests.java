package com.ggs.rabbitmq;

import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 提供者只提供消息,不会创建队列或交换机
 * SpringBoot会根据消费者指定的配置自动去创建交换机和队列
 * 所以,需要先创建消费者,再执行代码
 * <p>
 * BuiltinExchangeType是rabbitmq模型的枚举类
 */
@SpringBootTest
class RabbitmqSpringbootApplicationTests {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    //Work Queues模型,一对多模型
    @Test
    public void work() {
        for (int i = 0; i < 20; i++) {
            rabbitTemplate.convertAndSend("works", "aaaaaa" + i);
        }
    }

    //Hello World模型,点对点
    @Test
    public void helloworld() {
        rabbitTemplate.convertAndSend("helloaa", "啊啊啊啊啊啊,rabbitmq和springboot整合");
    }


    //Fanout模型 --> 广播模型(fanout) --> 配合FanoutConsumer类
    @Test
    public void testFanout() {
        rabbitTemplate.convertAndSend("logs", "", "Fanout模型广播的信息");
    }

    //Route模型 --> 路由模型(direct) --> 配合RouteConsumer类
    @Test
    public void testRoute() {
        String routingKey = "error";
        rabbitTemplate.convertAndSend("logs_route", routingKey, "这是" + routingKey + "级别的日志信息");
    }

    //Topics模型 --> 动态路由模型(topic) --> 配合TopicsConsumer类
    @Test
    public void testTopics() {
        String routingKey = "order.lover.ppp";
        rabbitTemplate.convertAndSend("logs_topics", routingKey, "发送消息啦啦啦-->" + routingKey);
    }
}
