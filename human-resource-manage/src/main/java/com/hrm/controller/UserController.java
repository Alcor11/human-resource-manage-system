package com.hrm.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author guchun
 * @description TODO
 * @date 2022/4/28 20:44
 */

@RestController
@RequestMapping("test")
public class UserController {

    @GetMapping("/test")
    public String testController() {
        return "hello";
    }

}
