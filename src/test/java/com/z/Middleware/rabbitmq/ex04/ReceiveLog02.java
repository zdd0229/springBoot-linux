package com.z.Middleware.rabbitmq.ex04;

import com.rabbitmq.client.Channel;
import com.z.Middleware.rabbitmq.RabbitMqUtils;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;

import java.io.File;

/**
 * 日志处理
 * 将日志打印在控制台上
 */
public class ReceiveLog02 {

    public static final String EXCHANGE_NAME = "log_exchange";

    @SneakyThrows
    public static void main(String[] args) {

        Channel channel = RabbitMqUtils.getChannel();

        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        String queueName = channel.queueDeclare().getQueue();

        channel.queueBind(queueName, EXCHANGE_NAME, "");
        System.out.println("等待接收消息，将日志写入文件");

        channel.basicConsume(queueName, true,
                (consumerTag, message) -> {
                    String msg = new String(message.getBody(), "utf-8");
                    File file = new File("D:\\work\\rabbitmq_info.txt");
                    FileUtils.writeStringToFile(file, msg, "UTF-8");
                    System.out.println(" 数据写入文件成功");
                }, consumerTag -> {

                }
        );

    }

}
