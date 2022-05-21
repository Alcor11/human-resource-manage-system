package com.hrm.controller.business;

import com.hrm.common.BaseResponse;
import com.hrm.controller.BaseController;
import com.hrm.kafka.KafkaSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guchun
 * @description TODO
 * @date 2022/5/1 21:28
 */
@RestController
@RequestMapping("/bus")
public class BusinessController extends BaseController {



}
