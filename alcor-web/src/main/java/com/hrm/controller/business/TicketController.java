package com.hrm.controller.business;

import com.hrm.aspectlj.config.TokenVerify;
import com.hrm.common.BaseResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author guchun
 * @description TODO
 * @date 2022/5/22 19:17
 */
@RestController
@RequestMapping("/api/ticket")
public class TicketController {

    @TokenVerify
    @PostMapping("/generate")
    public BaseResponse generateTicket() {
        return null;
    }
}
