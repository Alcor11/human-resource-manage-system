package com.hrm.controller.business;

import com.hrm.controller.BaseController;
import com.hrm.kafka.KafkaSender;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guchun
 * @description kafka controller
 * @date 2022/5/21 12:15
 */
@RestController
@RequestMapping("/kafka")
public class KafkaController extends BaseController {

    @Autowired
    KafkaSender kafkaSender;

    @Resource
    private KafkaListenerEndpointRegistry registry;

    @GetMapping("/send")
    public String kafkaSend(@RequestParam(value = "msg") String msg,
                            @RequestParam(value = "topic") String topic) {
        kafkaSender.sendMsg(topic, msg);
        return "ok";
    }

    @GetMapping("/start")
    public void startListen() {
        System.out.println("开启监听");
        //判断监听容器是否启动，未启动则将其启动
        if (!registry.getListenerContainer("myListener1").isRunning()) {
            registry.getListenerContainer("myListener1").start();
        }
        registry.getListenerContainer("myListener1").resume();
    }

    // 关闭监听
    @GetMapping("/stop")
    public void stop() {
        System.out.println("关闭监听");
        //判断监听容器是否启动，未启动则将其启动
        registry.getListenerContainer("myListener1").pause();
    }
}
