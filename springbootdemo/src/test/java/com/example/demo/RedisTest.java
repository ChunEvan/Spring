package com.example.demo;

import com.example.demo.pojo.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Set;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {

    @Autowired
    @Qualifier("redisTemplate")
    private RedisTemplate redisTemplate;

    @Test
    public void redisTest() throws JsonProcessingException {

//        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
//        connection.flushDb();
//        connection.flushAll();

        User user = new User();
        user.setName("evan");
        user.setAge(13);
        user.setSex(1);
        String jsonUser = new ObjectMapper().writeValueAsString(user);
        redisTemplate.boundValueOps("user").set(user);
        System.out.println(redisTemplate.boundValueOps("user").get());

        redisTemplate.boundValueOps("str").set("heima");
        System.out.println(redisTemplate.boundValueOps("str").get());

        redisTemplate.boundHashOps("h_key").put("name","evan");
        redisTemplate.boundHashOps("h_key").put("age","13");
        Set h_key = redisTemplate.boundHashOps("h_key").keys();
        List h_values = redisTemplate.boundHashOps("h_key").values();
        System.out.println(h_key);
        System.out.println(h_values);
        Object o = redisTemplate.boundHashOps("h_key").get("name");
        System.out.println(o);

        redisTemplate.boundListOps("l_key").leftPush("c");
        redisTemplate.boundListOps("l_key").leftPush("b");
        redisTemplate.boundListOps("l_key").leftPush("a");
        System.out.println( redisTemplate.boundListOps("l_key").range(0, -1));
        System.out.println(redisTemplate.boundListOps("l_key").leftPop());
        System.out.println(redisTemplate.boundListOps("l_key").leftPop());
        System.out.println(redisTemplate.boundListOps("l_key").leftPop());
        System.out.println(redisTemplate.boundListOps("l_key").range(0, -1));

        redisTemplate.boundListOps("r_key").rightPush("c");
        redisTemplate.boundListOps("r_key").rightPush("b");
        redisTemplate.boundListOps("r_key").rightPush("a");
        System.out.println(redisTemplate.boundListOps("r_key").range(0, -1));
        System.out.println(redisTemplate.boundListOps("r_key").rightPop());
        System.out.println(redisTemplate.boundListOps("r_key").rightPop());
        System.out.println(redisTemplate.boundListOps("r_key").rightPop());

        Long add = redisTemplate.boundSetOps("s_key").add("a", "b", "c");
        System.out.println(redisTemplate.boundSetOps("s_key").members());

        redisTemplate.boundZSetOps("z_key").add("a", 30);
        redisTemplate.boundZSetOps("z_key").add("b", 20);
        redisTemplate.boundZSetOps("z_key").add("c", 10);
        System.out.println(redisTemplate.boundZSetOps("z_key").range(0, -1));


    }

}
