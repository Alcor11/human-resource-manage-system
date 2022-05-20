package com.hrm.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * @author guchun
 * @description TODO
 * @date 2022/5/19 19:22
 */
@Component
public class KafkaListener {

    @org.springframework.kafka.annotation.KafkaListener(id = "myListener1", topics = {"test"})
    @SendTo("topic2")
    public void listenKafka(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            System.out.println("消息接收成功");
            Object message = kafkaMessage.get();
            System.out.println("record =" + record);
            System.out.println("message =" + message);
        }
    }

    @org.springframework.kafka.annotation.KafkaListener(topics = {"topic2"})
    public void listenTopics(ConsumerRecord<?, ?> record) {
        Optional<?> kafkaMessage = Optional.ofNullable(record.value());
        if (kafkaMessage.isPresent()) {
            System.out.println("消息topic2接收成功");
            Object message = kafkaMessage.get();
            System.out.println("record =" + record);
            System.out.println("message =" + message);
        }
    }
}
