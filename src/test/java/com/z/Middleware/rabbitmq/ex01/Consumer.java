package com.z.Middleware.rabbitmq.ex01;

import com.rabbitmq.client.Channel;
import com.z.Middleware.rabbitmq.RabbitMqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Consumer {

    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = RabbitMqUtils.getChannel();

        channel.basicConsume(Producer.QUEUE_NAME, false,
                (consumerTag, delivery) -> {
                    String msg = new String(delivery.getBody());
                    try {
                        Thread.sleep(3 * 1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(msg);
                    long deliveryTag = delivery.getEnvelope().getDeliveryTag();
                    System.out.println("deliveryTag：" + deliveryTag);
                    channel.basicAck(deliveryTag, false);
                }, consumerTag -> {
                    System.out.println("消费被中断");
                });

        System.out.println("nihao");
    }

}
