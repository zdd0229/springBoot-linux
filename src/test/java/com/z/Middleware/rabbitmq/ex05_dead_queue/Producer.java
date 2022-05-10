package com.z.Middleware.rabbitmq.ex05_dead_queue;

import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.BuiltinExchangeType;
import com.rabbitmq.client.Channel;
import com.z.Middleware.rabbitmq.RabbitMqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    private static final String NORMAL_EXCHANGE = "normal_exchange";

    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = RabbitMqUtils.getChannel();

        channel.exchangeDeclare(NORMAL_EXCHANGE, BuiltinExchangeType.DIRECT);

        final AMQP.BasicProperties basicProperties = new AMQP.BasicProperties().builder().expiration("10000").build();

        for (int i = 0; i < 11; i++) {
            String message = "info" + i;
            channel.basicPublish(NORMAL_EXCHANGE, "zhangsan", basicProperties, message.getBytes());
            System.out.println("发送消息：" + message);
        }
    }

}
