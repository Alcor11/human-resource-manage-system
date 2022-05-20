package com.hrm.kafka;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * @author guchun
 * @description TODO
 * @date 2022/5/19 19:01
 */
@Component
public class KafkaSender {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    public void sendMsg(String topic, String msg) {
        kafkaTemplate.send(topic, msg);
    }

}
