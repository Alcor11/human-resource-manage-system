package com.hrm.controller.sys;

import com.hrm.kafka.KafkaSender;
import junit.framework.TestCase;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author guchun
 * @description TODO
 * @date 2022/5/19 19:10
 */
@SpringBootTest
public class AuthControllerTest extends TestCase {

    @Autowired
    KafkaSender kafkaSender;

    @Test
    public void test() {

        kafkaSender.sendMsg("top", "msg");
    }
}