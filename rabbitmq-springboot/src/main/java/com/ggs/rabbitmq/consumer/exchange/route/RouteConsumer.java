package com.ggs.rabbitmq.consumer.exchange.route;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author Starbug
 * @Date 2020/8/19 17:45
 */
@Component
public class RouteConsumer {

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "logs_route", type = "direct"),
                    key = {"info", "error", "debug"}
            )
    })
    public void routeMessage1(String message) {
        System.out.println("message1 = " + message);
    }

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue,
                    exchange = @Exchange(value = "logs_route", type = "direct"),
                    key = {"debug"}
            )
    })
    public void routeMessage2(String message) {
        System.out.println("message2 = " + message);
    }


}
