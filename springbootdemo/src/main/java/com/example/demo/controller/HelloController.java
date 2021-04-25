package com.example.demo.controller;

import com.example.demo.pojo.User;
import com.example.demo.service.AsyncService;
import com.example.demo.service.ScheduledService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {

    @Autowired
    private AsyncService asyncService;

    @RequestMapping
    @ResponseBody
    public String hello(){
        asyncService.hello();
        return "hello";
    }

    @Autowired
    private UserService userService;


    @GetMapping("/user/{id}")
    public User queryById(@PathVariable Integer id){
        User user = userService.queryById(id);
        System.out.println(user);
        return user;
    }
}
