package com.example.consumer.controller;

import com.example.consumer.client.UserClient;
import com.example.consumer.pojo.User;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/consumer")
//@DefaultProperties(defaultFallback = "defaultFallback")
public class ConsumerController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserClient client;

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/user/{id}")
//    @HystrixCommand(fallbackMethod = "queryByIdFallback")
    @HystrixCommand
    public User queryById(@PathVariable Long id){
        if(id == 8){
            throw new RuntimeException("太忙了");
        }

        String url = "http://localhost:9091/user/"+id;
//        List<ServiceInstance> instances = discoveryClient.getInstances("user-service");
//        ServiceInstance serviceInstance = instances.get(0);
//        String host = serviceInstance.getHost();
//        int port = serviceInstance.getPort();
//        url = "http://"+host+":"+port+"/user/"+id;

        url = "http://user-service/user/"+id;
        return restTemplate.getForObject(url, User.class);
    }

    @GetMapping("/user/{id}")
    public User queryById2(@PathVariable Long id){

        return client.queryById(id);
    }

    public String queryByIdFallback(Long id){
      System.out.println("查询用户信息失败。id:"+ id);
        return "对不起，网络太拥挤了!";
    }

    public String defaultFallback(){
        return "默认提示:对不起，网络太拥挤了!";
    }
}
