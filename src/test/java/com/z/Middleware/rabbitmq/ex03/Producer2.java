package com.z.Middleware.rabbitmq.ex03;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.ConfirmCallback;
import com.z.Middleware.rabbitmq.RabbitMqUtils;

import java.io.IOException;
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.TimeoutException;

public class Producer2 {

    public static final String QUEUE_NAME = "confirm_queue";
    public static final int MESSAGE_COUNT = 1000;

    /**
     * 发送消息
     * 异步确认
     *
     * @param args
     */
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        try (Channel channel = RabbitMqUtils.getChannel()) {
            String queueName = QUEUE_NAME;
            channel.queueDeclare(queueName, false, false, false, null);
            // 开启发布确认
            channel.confirmSelect();
            ConcurrentSkipListMap<Long, String> outstandingConfirms = new ConcurrentSkipListMap<>();

            ConfirmCallback confirmCallback = (seq, multiple) -> {
                if (multiple) {
                    ConcurrentNavigableMap<Long, String> confirmed = outstandingConfirms.headMap(seq, true);
                    confirmed.clear();
                } else {
                    outstandingConfirms.remove(seq);
                }
            };

            ConfirmCallback nackCallback = (sequenceNumber, multiple) -> {
                String message = outstandingConfirms.get(sequenceNumber);
                System.out.println(" 发布的消息" + message + " 未被确认，序列号" + sequenceNumber);
            };

            channel.addConfirmListener(confirmCallback, null);

            long begins = System.currentTimeMillis();
            for (int i = 0; i < MESSAGE_COUNT; i++) {
                String message = " 消息" + i;
                /**
                 * channel.getNextPublishSeqNo() 获取下一个消息的序列号
                 * 通过序列号与消息体进行一个关联
                 * 全部都是未确认的消息体
                 */
                outstandingConfirms.put(channel.getNextPublishSeqNo(), message);
                channel.basicPublish("", queueName, null, message.getBytes());
            }

            long end = System.currentTimeMillis();
            System.out.println(" 发布" + MESSAGE_COUNT + " 个异步确认消息, 耗时" + (end - begins) + "ms");
        }
    }
}
