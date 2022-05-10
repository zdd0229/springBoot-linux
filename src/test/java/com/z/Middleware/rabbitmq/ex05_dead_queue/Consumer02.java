package com.z.Middleware.rabbitmq.ex05_dead_queue;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.DeliverCallback;
import com.z.Middleware.rabbitmq.RabbitMqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer02 {


    public static void main(String[] args) throws IOException, TimeoutException {
        Channel channel = RabbitMqUtils.getChannel();
        String deadQueue = "dead-queue";
        channel.queueDeclare(deadQueue, false, false, false, null);

        System.out.println(" 等待接收死信队列消息 ........... ");
        DeliverCallback deliverCallback = (consumerTag, delivery) ->
        {
            String message = new String(delivery.getBody(), "UTF-8");
            System.out.println("Consumer02 接收死信队列的消息 " + message);
        };
        channel.basicConsume(deadQueue, true, deliverCallback, consumerTag -> {
        });
    }

}
