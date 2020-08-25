package com.ggs.rabbitmq.consumer.exchange.topics;

import com.rabbitmq.client.BuiltinExchangeType;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author Starbug
 * @Date 2020/8/19 17:56
 * <p>
 * 符号 * 表示匹配一个单词
 * 符号 # 表示匹配0个或多个单词
 */
@Component
public class TopicsConsumer {

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue, //绑定一个临时队列
                    exchange = @Exchange(value = "logs_topics", type = "topic"), //绑定交换机
                    key = {"user.*", "order.*"} //指定匹配的路由键
            )
    )
    public void topicMessage1(String message) {
        System.out.println("message1 = " + message);
    }

    @RabbitListener(
            bindings = @QueueBinding(
                    value = @Queue, //绑定一个临时队列
                    exchange = @Exchange(value = "logs_topics", type = "topic"),
                    key = {"school.*", "order.#"}
            )
    )
    public void topicMessage2(String message) {
        System.out.println("message2 = " + message);
    }

}
