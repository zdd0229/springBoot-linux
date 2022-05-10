package com.z.Middleware.rabbitmq.ex03;

import com.rabbitmq.client.Channel;
import com.z.Middleware.rabbitmq.RabbitMqUtils;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Producer {

    public static final String QUEUE_NAME = "confirm_queue";
    public static final int MESSAGE_COUNT = 1000;

    /**
     * 发送消息
     * 发布确认模式
     * 批量确认
     *
     * @param args
     */
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        try (Channel channel = RabbitMqUtils.getChannel()) {
            String queueName = QUEUE_NAME;
            channel.queueDeclare(queueName, false, false, false, null);
            // 开启发布确认
            channel.confirmSelect();
            // 批量确认消息大小
            int batchSize = 100;
            // 未确认消息个数
            int outstandingMessageCount = 0;

            long begin = System.currentTimeMillis();
            for (int i = 0; i < MESSAGE_COUNT; i++) {
                String message = i + "";
                channel.basicPublish("", queueName, null, message.getBytes());
                outstandingMessageCount++;
                if (outstandingMessageCount == batchSize) {
                    channel.waitForConfirms();
                    outstandingMessageCount = 0;
                }
            }
            // 为了确保还有剩余没有确认消息 再次确认
            if (outstandingMessageCount > 0) {
                channel.waitForConfirms();
            }
            long end = System.currentTimeMillis();
            System.out.println(" 发布" + MESSAGE_COUNT + " 个批量确认消息, 耗时" + (end - begin) +
                    "ms");
        }
    }
}
