package com.z.Middleware.rabbitmq.ex02;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.MessageProperties;
import com.z.Middleware.rabbitmq.RabbitMqUtils;

import java.io.IOException;
import java.util.Scanner;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static final String QUEUE_NAME = "durable_queue";

    /**
     * 发送消息
     *
     * @param args
     */
    public static void main(String[] args) throws IOException, TimeoutException {

        Channel channel = RabbitMqUtils.getChannel();

        channel.queueDeclare(QUEUE_NAME, true, false, false, null);
        // 从控制台当中接受信息
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String message = scanner.next();
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN, message.getBytes());
            System.out.println(" 发送消息完成:" + message);
        }

    }

}
