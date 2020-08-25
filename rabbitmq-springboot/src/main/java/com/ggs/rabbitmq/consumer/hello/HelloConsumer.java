package com.ggs.rabbitmq.consumer.hello;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author Starbug
 * @Date 2020/8/19 17:06
 */
@Component
//声明一个队列
//队列默认属性: 持久化、非独占、不是自动删除队列
@RabbitListener(queuesToDeclare = {@Queue(value = "helloaa")})
public class HelloConsumer {

    @RabbitHandler
    public void getMessage(String message) {
        System.out.println(message);
    }

}
