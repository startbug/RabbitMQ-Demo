package com.ggs.rabbitmq.consumer.exchange.fanout;

import com.rabbitmq.client.BuiltinExchangeType;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author Starbug
 * @Date 2020/8/19 17:33
 */
@Component
public class FanoutConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue, //创建临时队列
                    exchange = @Exchange(name = "logs", type = "fanout") //绑定交换机
            )
    })
    public void fanoutMessage1(String message) {
        System.out.println("message1 = " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(name = "logs", type = "fanout")
            )
    })
    public void fanoutMessage2(String message) {
        System.out.println("message2 = " + message);
    }

}
