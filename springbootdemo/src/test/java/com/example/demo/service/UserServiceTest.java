package com.example.demo.service;

import com.example.demo.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Test
    void queryById() {
        User user = userService.queryById(2);
        System.out.println(user);
    }

    @Test
    void saveUser() {
        User user = new User();
        user.setName("Evan2");
        user.setAge(21);
        user.setSex(1);
        userService.saveUser(user);
    }
}