package com.ggs.rabbitmq.consumer.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author Starbug
 * @Date 2020/8/19 17:25
 */
@Component
public class WorkConsumer {

    @RabbitListener(queuesToDeclare = {@Queue(value = "works")})
    public void workQueue1(String message) {
        System.out.println("worker1:" + message);
    }

    @RabbitListener(queuesToDeclare = {@Queue(value = "works")})
    public void workQueue2(String message) {
        System.out.println("worker2:" + message);
    }

}
