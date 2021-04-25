package com.example.consumer.client.fallback;

import com.example.consumer.client.UserClient;
import com.example.consumer.pojo.User;
import org.springframework.stereotype.Component;

@Component
public class UserClientFallback implements UserClient {
    @Override
    public User queryById(Long id) {
        return new User().setId(id).setName("用户异常");
    }
}
